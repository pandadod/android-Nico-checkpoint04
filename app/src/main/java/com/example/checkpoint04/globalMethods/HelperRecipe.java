package com.example.checkpoint04.globalMethods;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.checkpoint04.models.RecipeFav;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HelperRecipe {


    public static void extractRecipe(Context context, final RecipeListener listener) {

        final RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = "https://www.food2fork.com/api/search?key=ee5895fd6815a8ca564ca5b7d4f6f74a&q=all";

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        List<RecipeFav> recipes = new ArrayList<>();
                        try {
                            JSONArray recipesArray = response.getJSONArray("recipes");
                            for (int i = 0; i < recipesArray.length(); i++) {
                                JSONObject recipe = (JSONObject) recipesArray.get(i);
                                String recipeName = recipe.getString("title");
                                String imageUrl = recipe.getString("image_url");
                                String sourceUrl = recipe.getString("source_url");
                                RecipeFav recipeFav = new RecipeFav(recipeName, sourceUrl, imageUrl);
                                recipes.add(recipeFav);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        listener.onRecipesLoaded(recipes);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("VOLLEY_ERROR", "onErrorResponse: " + error.getMessage());
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    public interface RecipeListener {
        void onRecipesLoaded(List<RecipeFav> recipes);
    }
}
