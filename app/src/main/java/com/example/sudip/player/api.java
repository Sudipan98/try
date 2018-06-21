package com.example.sudip.player;


import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.google.android.gms.common.api.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CSE on 16-06-2018.
 */

public class api {
    public static final String BASE_PLAYER_URL = "http://192.168.43.223/player/" ;
    public static final String BASE_TASK_URL = "http://192.168.43.223/task/" ;

    private static final String BASE_URL = "http://192.168.43.223/player/";
    public static final String BASE_COACH_URL = "http://192.168.43.223/coach/" ;
    public static final String BASE_GOAL_URL = "http://192.168.43.223/goal/" ;
    //public  static String res;
    //private static AsyncHttpClient client = new AsyncHttpClient();

    public static void post(String url, Context context, final HashMap obj){
        final String[] res = {new String()};
        // String[] res = new String[1];
        final RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest sr = new StringRequest(Request.Method.POST, getAbsoluteUrl(url),
                new Response.Listener<String>() {

                    public void onResponse(String response) {
                        Log.e("HttpClient", "success! response: " + response.toString());
                        res[0] =response.toString();
                        Log.d(" return ",res[0]);


                    }
                },
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        Log.e("HttpClient", "error: " + error.toString());
                    }
                })
        {
            @Override
            protected Map<String,String> getParams(){

                Map<String,String> params = new HashMap<String, String>(obj);
                params = obj;
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        Log.d(" return ",res[0]);
        queue.add(sr);
        Log.d(" return ",res[0]);
        //return res;
    Log.d(" end "," End");
    }
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

}

