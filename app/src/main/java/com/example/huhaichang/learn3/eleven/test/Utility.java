package com.example.huhaichang.learn3.eleven.test;


import com.example.huhaichang.learn3.eleven.APP;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Created by huhaichang on 2019/8/16.
 */

public class Utility {
    //解析出XX省XX市XX县
/*    public static APP handleWeatherResponse(String response){
        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather5");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(response,APP.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }*/

    public static String handleWeatherResponse(String response){
        try{
          //  JSONObject jsonObject = new JSONObject(response);
           // JSONArray jsonArray = jsonObject.getJSONArray("HeWeather5");
           // String weatherContent = jsonArray.getJSONObject(0).toString();
            JSONArray jsonArray = new JSONArray(response);
            for(int i = 0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //每个节点的键获取值
                //  String id = jsonObject.getString("id");
                String weatherContent= jsonObject.getString("HeWeather5");
                return weatherContent;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
