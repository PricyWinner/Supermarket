package com.example.Supermarket;

import static Services.UserServices.createUserData;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.Supermarket.databinding.ActivityMainBinding;

import java.util.ArrayList;

import Adapters.GridAdapter;
import Models.Item;
import Services.ItemService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int userId;
    private ArrayList<Item> listItem = ItemService.itemList;
    private ArrayList<Item.Category> category = ItemService.category;
//    ActivityMainBinding binding;
    SearchView searchView;
    Button btn_sayur, btn_daging, btn_dairy, btn_bumbu;
//    String[] category = {"sayur dan buah", "daging dan seafood", "dairy", "bumbu dapur"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        searchView = (SearchView) findViewById(R.id.searchbar);
        ItemService.generateItemData();

        Log.wtf("oncreate home", listItem.toString());
        btn_sayur = (Button) findViewById(R.id.btn_sayur);
        btn_daging = (Button) findViewById(R.id.btn_daging);
        btn_dairy = (Button) findViewById(R.id.btn_dairy);
        btn_bumbu = (Button) findViewById(R.id.btn_bumbu);

        btn_sayur.setOnClickListener(this);
        btn_daging.setOnClickListener(this);
        btn_dairy.setOnClickListener(this);
        btn_bumbu.setOnClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        if(item.getItemId() == R.id.profile){
            intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sayur:
                Intent intent = new Intent(this, DetailCategory.class);
                intent.putExtra("category", category.get(0).getId());
                Log.wtf("onclick", category.get(0).getCategory_name());
                startActivity(intent);
                break;
            case R.id.btn_daging:
                Intent intent2 = new Intent(this, DetailCategory.class);
                intent2.putExtra("category", category.get(1).getId());
                Log.wtf("onclick", category.get(0).getCategory_name());
                startActivity(intent2);
                break;
            case R.id.btn_dairy:
                Intent intent3 = new Intent(this, DetailCategory.class);
                intent3.putExtra("category", category.get(2).getId());
                Log.wtf("onclick", category.get(0).getCategory_name());
                startActivity(intent3);
                break;
            case R.id.btn_bumbu:
                Intent intent4 = new Intent(this, DetailCategory.class);
                intent4.putExtra("category", category.get(3).getId());
                Log.wtf("onclick", category.get(0).getCategory_name());
                startActivity(intent4);
                break;
        }
    }
}