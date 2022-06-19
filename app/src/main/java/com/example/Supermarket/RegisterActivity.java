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
    DatabaseHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText etEmail = findViewById(R.id.email);
        EditText etUsername = findViewById(R.id.username);
        EditText etPassword = findViewById(R.id.password);
        EditText etPhoneNumber = findViewById(R.id.phoneNumber);
        Button login = findViewById(R.id.login);
        Button register = findViewById(R.id.register);
        dbhelper = new DatabaseHelper(this);

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
                String phoneNumber = etPhoneNumber.getText().toString();

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

                if(phoneNumber.isEmpty()){
                    error = true;
                    etPhoneNumber.setError("Phone number couldn't be empty");
                }

                if (dbhelper.checkIfUsernameExists(username) || dbhelper.checkIfEmailAddressExists(email)) {
                    if (dbhelper.checkIfUsernameExists(username)) {
                        error = true;
                        etUsername.setError("Username already exists. Please choose another one.");
                    } else if (dbhelper.checkIfEmailAddressExists(email)) {
                        error = true;
                        etEmail.setError("Email Address already exists. Please choose another one.");
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
                    dbhelper.insertUser(email, username, phoneNumber, password);
                    Toast.makeText(getApplicationContext(), "Account created. Please sign in", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
    }
}