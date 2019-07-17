package com.example.checkpoint04.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.checkpoint04.R;
import com.example.checkpoint04.globalMethods.SingletonVolley;
import com.example.checkpoint04.models.User;
import com.example.checkpoint04.globalMethods.UserSingleton;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btSignUp = findViewById(R.id.btSignUp);
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etEmail = findViewById(R.id.etEmail);
                String mail = etEmail.getText().toString();
                EditText etPassword1 = findViewById(R.id.etPassword1);
                String password1 = etPassword1.getText().toString();
                EditText etPassword2 = findViewById(R.id.etpassword2);
                String password2 = etPassword2.getText().toString();

                if (mail.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, R.string.error_filled, Toast.LENGTH_SHORT).show();
                } else if (password1.length() < 8) {
                    Toast.makeText(RegisterActivity.this, R.string.error_number_char, Toast.LENGTH_SHORT).show();
                } else if (!(password1.equals(password2))) {
                    Toast.makeText(RegisterActivity.this, R.string.error_difference_password, Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User();
                    user.setEmail(mail);
                    String hashedPassword = Hashing.sha256().hashString(password1, Charset.defaultCharset()).toString();
                    user.setPassword(hashedPassword);
                    SingletonVolley.getInstance(RegisterActivity.this).createAccount(user, new Consumer<User>() {
                        @Override
                        public void accept(User user) {
                            UserSingleton.getInstance().setUser(user);
                            Intent intent = new Intent(RegisterActivity.this, RecipeListActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }
}
