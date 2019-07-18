package com.example.checkpoint04.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.checkpoint04.R;
import com.example.checkpoint04.globalMethods.HelperRecipe;
import com.example.checkpoint04.globalMethods.UserSingleton;
import com.example.checkpoint04.models.RecipeAdapter;
import com.example.checkpoint04.models.RecipeFav;
import com.example.checkpoint04.models.User;

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.itemMenuFav:
                Intent goToFavActivity = new Intent(RecipeListActivity.this, FavActivity.class);
                startActivity(goToFavActivity);
                return true;
            case R.id.itemMenuLogout:
                Intent goToMainActivity = new Intent(RecipeListActivity.this, MainActivity.class);
                User user = new User();
                UserSingleton.getInstance().setUser(user);
                startActivity(goToMainActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
