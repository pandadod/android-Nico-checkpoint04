package com.example.checkpoint04.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.checkpoint04.R;
import com.example.checkpoint04.globalMethods.UserSingleton;
import com.example.checkpoint04.models.User;

public class DetailsActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        final String urlRecipe = intent.getStringExtra("URL_RECIPE");
        webView = findViewById(R.id.wvRecipe);
        webView.setWebViewClient(new MyBrowser());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(urlRecipe);
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
                Intent goToFavActivity = new Intent(DetailsActivity.this, FavActivity.class);
                startActivity(goToFavActivity);
                return true;
            case R.id.itemMenuLogout:
                Intent goToMainActivity = new Intent(DetailsActivity.this, MainActivity.class);
                User user = new User();
                UserSingleton.getInstance().setUser(user);
                startActivity(goToMainActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
