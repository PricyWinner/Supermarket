package com.example.Supermarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

import Models.User;
import Services.UserServices;

public class ProfileActivity extends AppCompatActivity {
//    private List<User> listUser = UserServices.listUser;
    private User currentUser = UserServices.currentUser;
    BottomNavigationView bottom_navigation;
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

        bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setSelectedItemId(R.id.profile);

        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    Intent goToMain = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(goToMain);
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.profile) {
                    return true;
                } else if (item.getItemId() == R.id.cart) {
                    Intent goToCart = new Intent(getApplicationContext(), CartActivity.class);
                    startActivity(goToCart);
                    overridePendingTransition(0, 0);
                    return true;
                }else if(item.getItemId() == R.id.transaction){
                    Intent goToTransaction = new Intent(getApplicationContext(), TransactionActivity.class);
                    startActivity(goToTransaction);
                    overridePendingTransition(0,0);
                    return true;
                }
                return false;
            }
        });
    }
}