package com.example.Supermarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

import Adapters.TransactionAdapter;
import Models.Transaction;
import Services.UserServices;

public class TransactionActivity extends AppCompatActivity {

    private DatabaseHelper dbhelper;
    BottomNavigationView bottom_navigation;
    public static ArrayList<Transaction> transactionsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        dbhelper = new DatabaseHelper(this);

        bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setSelectedItemId(R.id.transaction);

        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.home){
                    Intent goToMain = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(goToMain);
                    overridePendingTransition(0,0);
                    return true;
                }else if(item.getItemId() == R.id.cart){
                    Intent goToCart = new Intent(getApplicationContext(), CartActivity.class);
                    startActivity(goToCart);
                    overridePendingTransition(0,0);
                    return true;
                }
                else if(item.getItemId() == R.id.profile){
                    Intent goToProfile = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(goToProfile);
                    overridePendingTransition(0,0);
                    return true;
                }else if(item.getItemId() == R.id.transaction){
                    return true;
                }
                return false;
            }
        });

        //data exist
        if(dbhelper.checkIfTransactionExist(UserServices.currentUser.getUserId())){
            Log.wtf("Transaction", "exist");
            transactionsList = dbhelper.getUserTransaction(UserServices.currentUser.getUserId());
            Log.wtf("TransactionData", transactionsList.toString());

            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new TransactionAdapter(getApplicationContext()));
        }else{ //data exist
            Log.wtf("Transaction", "not exist");
        }



    }
}