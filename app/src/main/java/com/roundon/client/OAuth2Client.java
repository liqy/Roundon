package com.roundon.client;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


/**
 * Created by liqy on 16/1/10.
 */
public class OAuth2Client {
    private static RequestQueue requestQueue;

    public OAuth2Client() {

    }

    public static void init(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }


    public static RequestQueue getRequestQueue() {
        if (requestQueue != null) {
            return requestQueue;
        } else {
            throw new IllegalStateException("RequestQueue not initialized");
        }
    }
}
