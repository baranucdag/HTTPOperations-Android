package com.deneme.resttest;
import com.android.volley.*;
import com.android.volley.toolbox.*;

import org.json.JSONException;
import org.json.JSONObject;

public class HttpClientActions{
    private RequestQueue requestQueue;

    public HttpClientActions(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    public interface VolleyCallback {
        void onSuccess(String response);
        void onError(VolleyError error);
    }

    public void sendGetRequest(String url, final VolleyCallback callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (callback != null) {
                            callback.onSuccess(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (callback != null) {
                            callback.onError(error);
                        }
                    }
                });

        requestQueue.add(stringRequest);
    }

    public void sendPostRequest(String url, JSONObject requestBody, final VolleyCallback callback) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (callback != null) {
                            callback.onSuccess(response.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (callback != null) {
                            callback.onError(error);
                        }
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }
}
