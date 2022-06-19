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

import java.util.Date;

import Adapters.CartAdapter;
import Adapters.TransactionAdapter;
import Services.UserServices;

public class PaymentActivity extends AppCompatActivity {
    Button btn_checkout;
    TextView tv_totalPrice;
    private DatabaseHelper dbhelper;
    private int transactionID = 0;
    private Date currentDateTime = new Date();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        dbhelper = new DatabaseHelper(this);

        btn_checkout = (Button) findViewById(R.id.btn_checkout_payment);
        tv_totalPrice = (TextView) findViewById(R.id.tv_totalPrice);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewpayment);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CartAdapter(getApplicationContext()));

        int totalPrice = 0;
        for (int i = 0; i<userCartList.size(); i++) {
           totalPrice =   totalPrice + (userCartList.get(i).getItem().getPrice() * userCartList.get(i).getCount());

        }
        tv_totalPrice.setText("Total Price: " + "Rp." + Integer.toString(totalPrice));
        btn_checkout.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, MainActivity.class);


            //data exist
            if(dbhelper.checkIfTransactionExist(UserServices.currentUser.getUserId())){
                Log.wtf("Transaction", "exist");
                transactionID = dbhelper.getLastTransactionID();

            }else{ //data exist
                Log.wtf("Transaction", "not exist");
                transactionID = 0;
            }

            for(int i = 0; i<userCartList.size();i++){
                dbhelper.insertToTransaction(transactionID+1, userCartList.get(i).getItem().getId(), userCartList.get(i).getCount(), userCartList.get(i).getUserID(), currentDateTime);
            }
            Log.wtf("beforeDelete", dbhelper.getUserCart(UserServices.currentUser.getUserId()).toString());

            dbhelper.removeCartAfterTransaction(UserServices.currentUser.getUserId());
            Log.wtf("afterdelete", dbhelper.getUserCart(UserServices.currentUser.getUserId()).toString());
            Toast toast = Toast.makeText(getApplicationContext(), "checkout successful", Toast.LENGTH_SHORT);
            toast.show();
            startActivity(intent);

        });
    }
}