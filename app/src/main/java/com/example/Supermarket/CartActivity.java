package com.example.Supermarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.stream.Collectors;

import Adapters.CartAdapter;
import Services.ItemService;
import Services.UserServices;
import Store.Store;

public class CartActivity extends AppCompatActivity {

    BottomNavigationView bottom_navigation;
    TextView empty;
    Button btn_checkout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Log.wtf("cart", Store.cartItems.toString());
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CartAdapter());
        empty = (TextView) findViewById(R.id.tv_empty);
        btn_checkout = (Button) findViewById(R.id.btn_checkout);

        if(Store.cartItems.stream().filter(i -> i.getUserID() == UserServices.currentUser.getUserId()).count()>0){
            empty.setVisibility(View.GONE);
            btn_checkout.setVisibility(View.VISIBLE);
        }else{
            empty.setVisibility(View.VISIBLE);
            btn_checkout.setVisibility(View.GONE);
        }

        bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setSelectedItemId(R.id.cart);

        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.home){
                    Intent goToMain = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(goToMain);
                    overridePendingTransition(0,0);
                    return true;
                }else if(item.getItemId() == R.id.cart){
                    return true;
                }
                else if(item.getItemId() == R.id.profile){
                    Intent goToProfile = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(goToProfile);
                    overridePendingTransition(0,0);
                    return true;
                }
                return false;
            }
        });

        btn_checkout.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, PaymentActivity.class);
            startActivity(intent);
        });;
    }
}