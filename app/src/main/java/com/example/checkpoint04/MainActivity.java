package com.example.checkpoint04;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.checkpoint04.globalMethode.SingletonVolley;
import com.example.checkpoint04.models.User;
import com.example.checkpoint04.globalMethode.UserSingleton;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btLogin = findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etMail = findViewById(R.id.etMail);
                String mail = etMail.getText().toString();
                EditText etPassword = findViewById(R.id.etPassword);
                String password = (Hashing.sha256().hashString(String.valueOf(etPassword.getText()), Charset.defaultCharset())).toString();
                User user = new User();
                user.setEmail(mail);
                user.setPassword(password);
                SingletonVolley.getInstance(MainActivity.this).connexion(user, new Consumer<User>() {
                    @Override
                    public void accept(User user) {
                        if (user != null) {
                            UserSingleton.getInstance().initiateUser(
                                    user.getId(), user.getEmail(),
                                    user.getPassword(),
                                    user.getRecipeFavs());
                            UserSingleton.getInstance().getUser();

                            Intent goToRecipeListActivity = new Intent(MainActivity.this, RecipeListActivity.class);
                            startActivity(goToRecipeListActivity);

                        } else {
                            Toast.makeText(MainActivity.this, getString(R.string.erreur), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        Button btCreateCount = findViewById(R.id.btCreateCount);
        btCreateCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRegisterActivity = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(goToRegisterActivity);
            }
        });
    }
}
