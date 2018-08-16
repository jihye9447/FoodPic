package com.example.sinjihye.foodpic.RecordPackage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.sinjihye.foodpic.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordActivity extends AppCompatActivity implements View.OnClickListener, MediaScannerConnection.MediaScannerConnectionClient {
    LinearLayout takePictureBt;
    ImageView picture;
    String mCurrentPhotoPath;
    MediaScannerConnection mediaScannerConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        initView();
    }
    private void initView() {
        takePictureBt  = findViewById(R.id.take_picture_bt);
        picture = findViewById(R.id.picture);

        takePictureBt.setOnClickListener(this);
    }


    private void intentCamera(){
        File photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException ex) {
        }
        if (photoFile != null) {
            Uri photoURI = FileProvider.getUriForFile(this,
                    "com.example.sinjihye.foodpic.fileprovider",
                    photoFile);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(intent,100);
        }
    }
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
    private void galleryAddPic() {
        mediaScannerConnection =new MediaScannerConnection(this,this);
        mediaScannerConnection.connect();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.take_picture_bt:
                if (checkPermission()){
                    intentCamera();
                }
                break;
        }
    }

    private boolean checkPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                &&ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                !=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},200);
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Glide.with(this).load(mCurrentPhotoPath).into(picture);
        RecordModel recordModel = new RecordModel(this);
        recordModel.googleVisionApi(mCurrentPhotoPath);
        takePictureBt.setVisibility(View.GONE);
        picture.setVisibility(View.VISIBLE);
       // galleryAddPic();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            intentCamera();

        }
    }

    @Override
    public void onMediaScannerConnected() {
        mediaScannerConnection.scanFile(mCurrentPhotoPath,null);
    }

    @Override
    public void onScanCompleted(String path, Uri uri) {
        Log.d("aaaa","start : "+path);
        RecordModel recordModel = new RecordModel(this);
        recordModel.googleVisionApi(path);
    }
}
