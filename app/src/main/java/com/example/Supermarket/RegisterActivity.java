package com.example.Supermarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import Models.User;
import Services.UserServices;

public class RegisterActivity extends AppCompatActivity {

    private List<User> listUser = UserServices.listUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText etEmail = findViewById(R.id.email);
        EditText etUsername = findViewById(R.id.username);
        EditText etPassword = findViewById(R.id.password);
        Button login = findViewById(R.id.login);
        Button register = findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

//                UserServices datas = (UserServices) getApplicationContext();
//                List<User> listUser = datas.getListUser();

                boolean error = false;

                if(email.isEmpty()){
                    error = true;
                    etEmail.setError("Email couldnt be empty");
                } else if(!email.endsWith(".com")){
                    error = true;
                    etEmail.setError("Invalid email format");
                }

                if(username.isEmpty()){
                    error = true;
                    etUsername.setError("Username couldnt be empty");
                } else if(username.length() < 3){
                    error = true;
                    etUsername.setError("Invalid username format");
                }

                for(int i = 0; i < listUser.size(); i++){
                    boolean exist = false;

                    if(listUser.get(i).getUserName().equalsIgnoreCase(username)){
                        error = true;
                        exist = true;
                        etUsername.setError("Username is already exist");
                    }

                    if (listUser.get(i).getUserEmailAddress().equalsIgnoreCase(email)){
                        error = true;
                        exist = true;
                        etEmail.setError("Email is already exist");
                    }

                    if(exist){
                        break;
                    }
                }

                String numRegex   = ".*[0-9].*";
                String alphaRegex = ".*[a-zA-Z].*";

                if(password.isEmpty()){
                    error = true;
                    etPassword.setError("Passwords couldn't be empty");
                }else if(!password.matches(numRegex) || !password.matches(alphaRegex)){
                    etPassword.setError("Passwords must contain at least a number and an alphabet");
                    error = true;
                }

                if(error){
                    Toast.makeText(RegisterActivity.this, "Please check your username and password inputs", Toast.LENGTH_SHORT).show();
                } else {
//                    int lastId = listUser.get(listUser.size()-1).getUserId();
                    listUser.add(new User(listUser.size(), email, username, password));
                    Toast.makeText(getApplicationContext(), "Account created. Please sign up", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
    }
}