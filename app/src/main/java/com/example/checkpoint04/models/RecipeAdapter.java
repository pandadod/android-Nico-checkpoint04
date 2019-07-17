package com.example.checkpoint04.models;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.util.Consumer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.example.checkpoint04.R;
import com.example.checkpoint04.activities.DetailsActivity;
import com.example.checkpoint04.globalMethods.SingletonVolley;
import com.example.checkpoint04.globalMethods.UserSingleton;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    List<RecipeFav> recipeList;

    public RecipeAdapter(List<RecipeFav> recipes) {
        recipeList = recipes;
    }

    @NonNull
    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recipe_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final RecipeFav recipe = recipeList.get(i);
        Glide.with(viewHolder.view).load(recipe.getImageUrl()).into(viewHolder.ivImageRecipe);
        viewHolder.btAddFav.setChecked(false);
        viewHolder.recipeName.setText(recipe.getName());
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToDetails = new Intent(v.getContext(), DetailsActivity.class);
                goToDetails.putExtra("URL_RECIPE", recipe.getSourceUrl());
                v.getContext().startActivity(goToDetails);
            }
        });
        final User user = UserSingleton.getInstance().getUser();
        checkFavorites(user, recipe, viewHolder);
        viewHolder.btAddFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null && viewHolder.btAddFav.isChecked()) {
                    recipe.getUsers().add(user);
                    SingletonVolley.getInstance(viewHolder.view.getContext()).addRecipe(user.getId(), recipe, new Consumer<RecipeFav>() {
                        @Override
                        public void accept(RecipeFav recipeFav) {
                            viewHolder.btAddFav.setChecked(true);
                            Toast.makeText(viewHolder.view.getContext(), R.string.add_to_favorites, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                if (user != null && !(viewHolder.btAddFav.isChecked())) {
                    SingletonVolley.getInstance(viewHolder.view.getContext()).deleteRecipe(user.getId(), recipe.getId(), new Consumer<RecipeFav>() {
                        @Override
                        public void accept(RecipeFav recipeFav) {
                            viewHolder.btAddFav.setChecked(false);
                            Toast.makeText(viewHolder.view.getContext(), R.string.delete_favorites, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    private void checkFavorites(User user, RecipeFav recipe, ViewHolder viewHolder) {
        List<RecipeFav> recipeFavList = user.getRecipeFavs();
        for (int i = 0; i < recipeFavList.size(); i++) {
            if (recipe.getName().equals(recipeFavList.get(i).getName())) {
                recipe.setId(recipeFavList.get(i).getId());
                viewHolder.btAddFav.setChecked(true);
            }
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView recipeName;
        public ToggleButton btAddFav;
        public ImageView ivImageRecipe;
        public View view;

        public ViewHolder(View v) {
            super(v);
            recipeName = v.findViewById(R.id.tvRecipeName);
            btAddFav = v.findViewById(R.id.btAddFav);
            ivImageRecipe = v.findViewById(R.id.ivImageRecipe);
            view = v;
        }
    }
}
