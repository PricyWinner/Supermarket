package com.example.Supermarket;

import static Services.UserServices.createUserData;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import Models.User;
import Services.UserServices;

public class LoginActivity extends AppCompatActivity {

    private List<User> listUser = UserServices.listUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText etEmail = findViewById(R.id.email);
        EditText etPassword = findViewById(R.id.password);
        Button login = findViewById(R.id.login);
        Button register = findViewById(R.id.register);
        createUserData();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                boolean error = false;

                if(email.isEmpty()){
                    error = true;
                    etEmail.setError("Email couldnt be empty");
                }

                if(password.isEmpty()){
                    error = true;
                    etPassword.setError("Password couldnt be empty");
                }

                if(error){
                    Toast.makeText(LoginActivity.this, "Please check your username and password inputs", Toast.LENGTH_SHORT).show();
                } else {


                    for(int i = 0; i < listUser.size(); i++){
                        if(listUser.get(i).getUserEmailAddress().equalsIgnoreCase(email)){
                            if(listUser.get(i).getUserPassword().equals(password)){
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                intent.putExtra("userId", listUser.get(i).getUserId());
                                UserServices.currentUser = listUser.get(i);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                            }

                            break;
                        } else {
                            if(i == listUser.size() - 1){
                                Toast.makeText(LoginActivity.this, "Email not found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}