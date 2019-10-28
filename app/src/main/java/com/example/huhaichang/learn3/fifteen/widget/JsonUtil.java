package com.example.huhaichang.learn3.fifteen.widget;




import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Created by huhaichang on 2019/9/1.
 */

public class JsonUtil {
    public static String handleWeatherResponse(String response){
        String sb = "[";
        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for(int i = 0;i<(jsonArray.length());i++){
                if(i<jsonArray.length()-1) {
                    sb = sb + jsonArray.getJSONObject(i).toString() + ",";
                }else {
                    sb= sb+jsonArray.getJSONObject(i).toString()+"]";
                }
            }

            return sb;
            //return new Gson().fromJson(userInfoContent,new TypeToken<List<UserId>>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
