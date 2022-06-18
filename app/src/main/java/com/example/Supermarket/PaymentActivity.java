package com.example.Supermarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import Adapters.CartAdapter;

public class PaymentActivity extends AppCompatActivity {
    Button btn_checkout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        btn_checkout = (Button) findViewById(R.id.btn_checkout_payment);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewpayment);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CartAdapter());

        btn_checkout.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, MainActivity.class);
            Toast toast = Toast.makeText(getApplicationContext(), "checkout successful", Toast.LENGTH_SHORT);
            toast.show();
            startActivity(intent);

        });
    }
}