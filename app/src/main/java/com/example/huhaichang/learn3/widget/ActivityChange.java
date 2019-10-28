package com.example.huhaichang.learn3.widget;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huhaichang on 2019/7/26.
 */

public class ActivityChange {
    public static List<Activity> activities = new ArrayList<>();
    public static void add(Activity activity1){
        activities.add(activity1);
    }
    public static void remove(Activity activity2){
        activities.remove(activity2);
    }
    public static void finishAll(){
       //把列表的每一个元素都弄一个activity对象；
        //就是从activities列表中取出一个activity(元素)赋值给左侧的activity对象
        for(Activity activity : activities){
            //activity.isFinishing()=false为活跃状态
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
        activities.clear();
    }
}
