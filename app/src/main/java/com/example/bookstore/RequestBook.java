package com.example.bookstore;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.Serializable;

public class RequestBook implements Serializable {
    static private Activity activity;

    public static void getRequest(final Context t, final Callback callback) {

        activity = (Activity) t;
        RequestQueue queue = Volley.newRequestQueue(t);

        String URL;

        URL = "http://androidstorepddm.000webhostapp.com/services/getbooks.php?category=scifi";
        queue.add(generateRequest(URL, callback));

        URL = "http://androidstorepddm.000webhostapp.com/services/getbooks.php?category=english";
        queue.add(generateRequest(URL, callback));

        URL = "http://androidstorepddm.000webhostapp.com/services/getbooks.php?category=comics";
        queue.add(generateRequest(URL, callback));

        URL = "http://androidstorepddm.000webhostapp.com/services/getbooks.php?category=art";
        queue.add(generateRequest(URL, callback));

        URL = "http://androidstorepddm.000webhostapp.com/services/getbooks.php?category=self";
        queue.add(generateRequest(URL, callback));

        URL = "http://androidstorepddm.000webhostapp.com/services/getbooks.php?category=sports";
        queue.add(generateRequest(URL, callback));
    }

    public static Request generateRequest(final String url, final Callback cb){
        StringRequest request = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response:", response);
                        cb.processJSON(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Problem: ", error.toString());
                cb.onError();
            }
        });
        return request;
    }

    public interface Callback {
        void processJSON(String response);
        void onError();
    }

}
