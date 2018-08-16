package com.example.sinjihye.foodpic.RecordPackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.sinjihye.foodpic.Utils.ApiService;
import com.example.sinjihye.foodpic.Utils.LableDetectionTask;
import com.example.sinjihye.foodpic.Utils.OnVisionCallback;
import com.example.sinjihye.foodpic.Utils.PackageManagerUtils;
import com.example.sinjihye.foodpic.Utils.RetrofitCall;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequest;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sinjihye.foodpic.Utils.ApiService.BASE_URL;
import static com.example.sinjihye.foodpic.Utils.ApiService.TRANSLATE_URL;

public class RecordModel implements OnVisionCallback {

    Context context;
    private static final String CLOUD_VISION_API_KEY = "AIzaSyBTEaNlkzem8CJnm7x4-f21-nVdWJlA2C4";
    private static final String ANDROID_CERT_HEADER = "X-Android-Cert";
    private static final String ANDROID_PACKAGE_HEADER = "X-Android-Package";

    public RecordModel(Context context) {
        this.context = context;
    }

    public void googleVisionApi(String path){
        Glide.with(context).asBitmap().load(path).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                try {
                    AsyncTask<Object, Void, List<String>> labelDetectionTask = new LableDetectionTask(prepareAnnotationRequest(resource),RecordModel.this);
                    labelDetectionTask.execute();
                } catch (IOException e) {
                    Log.d("aaaaa", "failed to make API request because of other IOException " +
                            e.getMessage());
                }
            }
        });
    }

    private Vision.Images.Annotate prepareAnnotationRequest(final Bitmap bitmap) throws IOException {
        HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

        VisionRequestInitializer requestInitializer =
                new VisionRequestInitializer(CLOUD_VISION_API_KEY) {
                    /**
                     * We override this so we can inject important identifying fields into the HTTP
                     * headers. This enables use of a restricted cloud platform API key.
                     */
                    @Override
                    protected void initializeVisionRequest(VisionRequest<?> visionRequest)
                            throws IOException {
                        super.initializeVisionRequest(visionRequest);

                        String packageName = context.getPackageName();
                        visionRequest.getRequestHeaders().set(ANDROID_PACKAGE_HEADER, packageName);

                        String sig = PackageManagerUtils.getSignature(context.getPackageManager(), packageName);

                        visionRequest.getRequestHeaders().set(ANDROID_CERT_HEADER, sig);
                    }
                };

        Vision.Builder builder = new Vision.Builder(httpTransport, jsonFactory, null);
        builder.setVisionRequestInitializer(requestInitializer);

        Vision vision = builder.build();

        BatchAnnotateImagesRequest batchAnnotateImagesRequest =
                new BatchAnnotateImagesRequest();
        batchAnnotateImagesRequest.setRequests(new ArrayList<AnnotateImageRequest>() {{
            AnnotateImageRequest annotateImageRequest = new AnnotateImageRequest();
            // Add the image
            Image base64EncodedImage = new Image();
            // Convert the bitmap to a JPEG
            // Just in case it's a format that Android understands but Cloud Vision
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();

            // Base64 encode the JPEG
            base64EncodedImage.encodeContent(imageBytes);
            annotateImageRequest.setImage(base64EncodedImage);

            // add the features we want
            annotateImageRequest.setFeatures(new ArrayList<Feature>() {{
                Feature webDection = new Feature();
                webDection.setType("WEB_DETECTION");
                webDection.setMaxResults(10);
                add(webDection);
            }});

            // Add the list of one thing to the request
            add(annotateImageRequest);
        }});

        Vision.Images.Annotate annotateRequest =
                vision.images().annotate(batchAnnotateImagesRequest);
        // Due to a bug: requests to Vision API containing large images fail when GZipped.
        annotateRequest.setDisableGZipContent(true);
        Log.d("aaaaaa", "created Cloud Vision request object, sending request");

        return annotateRequest;
    }

    public void getNutrition(String foodName){
        Call<JsonObject> call = RetrofitCall.getRetrofit(BASE_URL).getNutrition("application/json",ApiService.SERVICE_KEY,foodName);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()){
                    JsonObject object = response.body();
                    JsonArray jsonArray = object.getAsJsonArray("records");
                    JsonObject result = (JsonObject) jsonArray.get(0);
                    float carb = result.get("NUTR_CONT2").getAsFloat();
                    float protein =result.get("NUTR_CONT3").getAsFloat();
                    float fat =result.get("NUTR_CONT4").getAsFloat();
                    float calorie = result.get("NUTR_CONT1").getAsFloat();
                    Log.d("aaaaa",result.toString()+",,");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("aaaa",t.getMessage()+",,");
            }
        });
    }



    @Override
    public void onDetect(List<String> descriptions) {
        getTranslate(descriptions);
    }

    private void getTranslate(List<String> description){
        Map<String,String> queryMap = new HashMap<>();
        for (String query : description){
            queryMap.put("q",query);
        }
        queryMap.put("key",ApiService.API_KEY);
        queryMap.put("target","ko");
        Call<JsonObject> translate = RetrofitCall.getRetrofit(TRANSLATE_URL).getTranselate(queryMap);
        translate.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()){
                    JsonObject object = response.body();
                    JsonObject data = object.get("data").getAsJsonObject();
                    JsonArray result = data.getAsJsonArray("translations");
                    JsonObject resultData= (JsonObject) result.get(0);
                    String aaaa = resultData.get("translatedText").getAsString();
                    Log.d("AAAA",aaaa);
                    getNutrition(resultData.get("translatedText").getAsString());
//                    for (JsonElement trans : result){
//                        resultData = (JsonObject) trans;
//                        Log.d("AAAA",resultData.get("translatedText").getAsString());
//
//                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

    }
}
