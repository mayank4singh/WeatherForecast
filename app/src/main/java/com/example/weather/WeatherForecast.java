package com.example.weather;

import android.app.VoiceInteractor;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class WeatherForecast {

    private static WeatherForecast Sinstance;
    private static RequestQueue requestQueue;
    private static Context cntxt;

        private WeatherForecast(Context context){
        cntxt = context;
        requestQueue = getRequestQueue();
    }
        public static RequestQueue getRequestQueue(){
            if(requestQueue == null){
                requestQueue = Volley.newRequestQueue(cntxt.getApplicationContext());
            }
            return requestQueue;
        }
        public static synchronized WeatherForecast getInstance(Context context){
            if(Sinstance ==null){
                Sinstance = new WeatherForecast(context);
            }
            return Sinstance;
        }
        public void addToRequest(Request request){
            requestQueue.add(request);
        }
}
