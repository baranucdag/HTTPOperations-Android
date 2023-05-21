package com.deneme.resttest;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button getButton;
    private Button postButton;

    ArrayList<userModel> userList = new ArrayList<>();
    userModel user = new userModel(1,0,"newalbum","newtitle","newurl");
    String url = "https://jsonplaceholder.typicode.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getButton = findViewById(R.id.button_send_getRequest);
        postButton = findViewById(R.id.button_send_postRequest);
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendGetRequest();
            }
        });

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPostRequest();
            }
        });
    }

    private void sendGetRequest() {

        RequestQueue queue = Volley.newRequestQueue(this);

         StringRequest stringRequest = new StringRequest(Request.Method.GET, url+"photos",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i=0;i<array.length();i++) {
                                JSONObject object = array.getJSONObject(i);
                                userModel model = new userModel(
                                        object.getInt("albumId"),
                                        object.getInt("id"),
                                        object.getString("thumbnailUrl"),
                                        object.getString("url"),
                                        object.getString("title")
                                );
                                    userList.add(model);
                            }
                            Log.e("api","onResponse" + userList.size());
                        }
                        catch (JSONException e) {
                            Log.d("api", "Response: " + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error: " + error.toString());

            }
        });

        queue.add(stringRequest);
    }

    public void sendPostRequest(){
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("title", "baran");
            jsonBody.put("body", "ucdag");
            jsonBody.put("userId", 1);
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url+"posts", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}


/*
  private static final String TAG = "MainActivity";
    private static final String BASE_URL = "https://api.example.com/";

    private HttpClientExample httpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        httpClient = new HttpClientExample(Volley.newRequestQueue(this));

        // GET isteği örneği
        String getEndpoint = BASE_URL + "photos";
        httpClient.sendGetRequest(getEndpoint, new HttpClientExample.VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                // Yanıt alındığında burada işlemleri gerçekleştirin
                Log.d(TAG, "GET Response: " + response);
            }

            @Override
            public void onError(VolleyError error) {
                // İstek başarısız olduğunda burada hata işleme yapabilirsiniz
                Log.e(TAG, "GET Error: " + error.toString());
            }
        });

        // POST isteği örneği
        String postEndpoint = BASE_URL + "posts";
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("title", "baran");
            requestBody.put("body", "ucdag");
            requestBody.put("userId", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        httpClient.sendPostRequest(postEndpoint, requestBody, new HttpClientExample.VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                // Yanıt alındığında burada işlemleri gerçekleştirin
                Log.d(TAG, "POST Response: " + response);
            }

            @Override
            public void onError(VolleyError error) {
                // İstek başarısız olduğunda burada hata işleme yapabilirsiniz
                Log.e(TAG, "POST Error: " + error.toString());
            }
        });
 */