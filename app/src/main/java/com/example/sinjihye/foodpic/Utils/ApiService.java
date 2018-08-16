package com.example.sinjihye.foodpic.Utils;

import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {
    static final String BASE_URL ="http://apis.data.go.kr/1470000/FoodNtrIrdntInfoService/";
    static final String TRANSLATE_URL = "https://translation.googleapis.com/language/";
    static final String API_KEY= "AIzaSyBTEaNlkzem8CJnm7x4-f21-nVdWJlA2C4";
    static final String SERVICE_KEY = "LOmFRL/EL+0+7rpa/AfSYduBOQ8K6sHJxlh0Pf7ySvR6vv8d1wcNWet2S7rh2d9J79CCT7FA1MRBGfMg/swywg==";

    @Headers({"Content-Type: application/json"})
    @GET("getFoodNtrItdntList")
    Call<JsonObject> getNutrition (@Query("serviceKey")String serviceKey, @Query("desc_kor")String foodName);

    @GET("translate/v2")
    Call<JsonObject> getTranselate(@QueryMap Map<String,String> queryMap);
}
