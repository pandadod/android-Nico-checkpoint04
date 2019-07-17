package com.example.checkpoint04.globalMethods;

import android.content.Context;
import android.os.Build;
import android.support.v4.util.Consumer;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.checkpoint04.models.RecipeFav;
import com.example.checkpoint04.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class SingletonVolley {

    private final static String REQUEST_URL = "http://192.168.8.127:8080";
    private static SingletonVolley instance;
    private static Context ctx;
    private RequestQueue requestQueue;

    private SingletonVolley(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized SingletonVolley getInstance(Context context) {
        if (instance == null) {
            instance = new SingletonVolley(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public void connexion(final User user, final Consumer<User> userListener) {
        String url = REQUEST_URL + "/user/login";
        GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();
        final String requestBody = gson.toJson(user);
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Volley success", response.toString());
                        User user = gson.fromJson(response.toString(), User.class);
                        userListener.accept(user);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VOLLEY_ERROR", "onErrorResponse: " + error.getMessage());
                        userListener.accept(null);
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            public byte[] getBody() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    return requestBody == null ? null : requestBody.getBytes(StandardCharsets.UTF_8);
                }
                return null;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    public void createAccount(final User user, final Consumer<User> userListener) {
        String url = REQUEST_URL + "/user/create";
        GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();
        final String requestBody = gson.toJson(user);
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Volley success", response.toString());
                        User user = gson.fromJson(response.toString(), User.class);
                        userListener.accept(user);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VOLLEY_ERROR", "onErrorResponse: " + error.getMessage());
                        userListener.accept(null);
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            public byte[] getBody() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    return requestBody == null ? null : requestBody.getBytes(StandardCharsets.UTF_8);
                }
                return null;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    public void modifyUser(final User user, final Consumer<User> userListener) {
        String url = REQUEST_URL + "/user/" + user.getId();
        GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();
        final String requestBody = gson.toJson(user);
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Volley success", response.toString());
                        User user = gson.fromJson(response.toString(), User.class);
                        userListener.accept(user);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VOLLEY_ERROR", "onErrorResponse: " + error.getMessage());
                        userListener.accept(null);
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            public byte[] getBody() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    return requestBody == null ? null : requestBody.getBytes(StandardCharsets.UTF_8);
                }
                return null;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    public void addRecipe(Long idUser, final RecipeFav recipeFav, final Consumer<RecipeFav> recipeFavConsumer) {

        String url = REQUEST_URL + "/recipes/" + idUser;
        GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();
        final String requestBody = gson.toJson(recipeFav);
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Volley success", response.toString());
                        RecipeFav recipe = gson.fromJson(response.toString(), RecipeFav.class);
                        recipeFavConsumer.accept(recipe);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VOLLEY_ERROR", "onErrorResponse: " + error.getMessage());
                        recipeFavConsumer.accept(null);
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            public byte[] getBody() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    return requestBody == null ? null : requestBody.getBytes(StandardCharsets.UTF_8);
                }
                return null;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    public void deleteRecipe(Long idUser, Long idRecipe, final Consumer<RecipeFav> recipeFavConsumer) {
        String url = REQUEST_URL + "/recipes/" + idRecipe + "/" + idUser;
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VOLLEY_ERROR", "onErrorResponse: " + error.getMessage());
                        recipeFavConsumer.accept(null);
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            public byte[] getBody() {

                return null;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }
}
