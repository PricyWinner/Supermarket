package com.example.Supermarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import Models.User;
import Services.UserServices;

public class ProfileActivity extends AppCompatActivity {
//    private List<User> listUser = UserServices.listUser;
    private User currentUser = UserServices.currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        int userId = getIntent().getIntExtra("userId", 0);
//        UserServices datas = (UserServices) getApplicationContext();
//        User user = datas.getUser(userId);
//        List<User> listUser = getListUser();

        TextView tvEmail = findViewById(R.id.email);
        TextView tvUsername = findViewById(R.id.tvUsername);
        TextView txtUsername = findViewById(R.id.txtUsername);

        Button logout = findViewById(R.id.logout);

        tvEmail.setText(currentUser.getUserEmailAddress());
        tvUsername.setText(currentUser.getUserName());


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserServices.currentUser = null;
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}