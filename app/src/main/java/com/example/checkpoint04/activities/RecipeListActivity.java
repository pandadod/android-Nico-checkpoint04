package com.example.checkpoint04.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.checkpoint04.R;
import com.example.checkpoint04.globalMethods.HelperRecipe;
import com.example.checkpoint04.models.RecipeAdapter;
import com.example.checkpoint04.models.RecipeFav;

import java.util.ArrayList;
import java.util.List;

public class RecipeListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        final List<RecipeFav> recipeList = new ArrayList<>();

        RecyclerView rvRecipe = findViewById(R.id.rvRecipe);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvRecipe.setLayoutManager(layoutManager);
        final RecipeAdapter adapter = new RecipeAdapter(recipeList);
        rvRecipe.setAdapter(adapter);

        HelperRecipe.extractRecipe(RecipeListActivity.this, new HelperRecipe.RecipeListener() {
            @Override
            public void onRecipesLoaded(List<RecipeFav> recipes) {
                recipeList.addAll(recipes);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
