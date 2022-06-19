package com.example.Supermarket;

import static com.example.Supermarket.CartActivity.userCartList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Adapters.CartAdapter;
import Models.CartItem;
import Services.UserServices;
import Store.Store;

public class PaymentActivity extends AppCompatActivity {
    Button btn_checkout;
    TextView tv_totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        btn_checkout = (Button) findViewById(R.id.btn_checkout_payment);
        tv_totalPrice = (TextView) findViewById(R.id.tv_totalPrice);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewpayment);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CartAdapter());

//        List<CartItem> filteredList = Store.cartItems.stream().filter(i -> i.getUserID() == UserServices.currentUser.getUserId()).collect(Collectors.toList());
        int totalPrice = 0;
        for (int i = 0; i<userCartList.size(); i++) {
           totalPrice =   totalPrice + (userCartList.get(i).getItem().getPrice() * userCartList.get(i).getCount());
//            Log.wtf("test", Integer.toString(totalPrice));
        }
        tv_totalPrice.setText("Total Price: " + "Rp." + Integer.toString(totalPrice));
        btn_checkout.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, MainActivity.class);
            Toast toast = Toast.makeText(getApplicationContext(), "checkout successful", Toast.LENGTH_SHORT);
            toast.show();
            startActivity(intent);

        });
    }
}