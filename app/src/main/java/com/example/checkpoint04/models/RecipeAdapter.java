package com.example.checkpoint04.models;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.example.checkpoint04.R;
import com.example.checkpoint04.activities.DetailsActivity;

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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final RecipeFav recipe = recipeList.get(i);
        Glide.with(viewHolder.view).load(recipe.getImageUrl()) .into(viewHolder.ivImageRecipe);
        viewHolder.btAddFav.setChecked(false);
        viewHolder.recipeName.setText(recipe.getName());
        viewHolder.btSeeTheRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToDetails = new Intent(v.getContext(), DetailsActivity.class);
                goToDetails.putExtra("URL_RECIPE", recipe.getSourceUrl());
                v.getContext().startActivity(goToDetails);
            }
        });

        viewHolder.btAddFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : ajouter ou supprimer la recette dans la liste des favoris
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView recipeName;
        public Button btSeeTheRecipe;
        public ToggleButton btAddFav;
        public ImageView ivImageRecipe;
        public View view;

        public ViewHolder(View v) {
            super(v);
            recipeName = v.findViewById(R.id.tvRecipeName);
            btSeeTheRecipe = v.findViewById(R.id.btSeeRecipe);
            btAddFav = v.findViewById(R.id.btAddFav);
            ivImageRecipe = v.findViewById(R.id.ivImageRecipe);
            view = v;
        }
    }
}
