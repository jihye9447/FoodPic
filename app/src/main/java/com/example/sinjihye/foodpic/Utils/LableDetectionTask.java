package com.example.sinjihye.foodpic.Utils;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.WebEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LableDetectionTask extends AsyncTask<Object, Void, List<String>> {
    static String TAG ="AAAAA";
    private Vision.Images.Annotate mRequest;
    private OnVisionCallback onVisionCallback;

    public LableDetectionTask(Vision.Images.Annotate annotate,OnVisionCallback onVisionCallback) {
        mRequest = annotate;
        this.onVisionCallback = onVisionCallback;
    }

    @Override
    protected List<String> doInBackground(Object... params) {
        try {

            Log.d(TAG, "created Cloud Vision request object, sending request");
            BatchAnnotateImagesResponse response = mRequest.execute();
            return convertResponseToString(response);

        } catch (GoogleJsonResponseException e) {
            Log.d(TAG, "failed to make API request because " + e.getContent());
        } catch (IOException e) {
            Log.d(TAG, "failed to make API request because of other IOException " +
                    e.getMessage());
        }
        return null;
    }



    private List<String> convertResponseToString(BatchAnnotateImagesResponse response) {
        List<String> result = new ArrayList<>();
        List<WebEntity> labels = response.getResponses().get(0).getWebDetection().getWebEntities();
        if (labels != null) {
            for (WebEntity label : labels) {
               if (label.getScore()>0.85){
                   result.add(label.getDescription());
               }
            }
        }
        return result;
    }


    @Override
    protected void onPostExecute(List<String> result) {
        onVisionCallback.onDetect(result);
    }

}