package com.care.med.medcare;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * Created by jatin on 11/02/17.
 */

public class Lib {

    //method to make post request with given parameters
    public static void makePost(String url, final Map<String, String> params, Context context, Response.Listener<String> respListener, Response.ErrorListener respErrorListener){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, respListener, respErrorListener) {
            protected Map<String, String> getParams() {
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}
