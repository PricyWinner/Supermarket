package com.example.Supermarket;

import static Services.ItemService.getSelectedCategoryItems;
import static Services.ItemService.selectedItem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.Supermarket.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Locale;

import Adapters.GridAdapter;
import Models.Item;
import Services.ItemService;

public class DetailCategory extends AppCompatActivity {

//    ActivityMainBinding binding;
    private ArrayList<Item.Category> categories = ItemService.category;
    private ArrayList<Item> items = ItemService.selectedCategoryItems;
    int selected_category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_category);
        Bundle extras = getIntent().getExtras();

//        final ActionBar actionBar = getActionBar();

        selected_category = extras.getInt("category");
        getSupportActionBar().setTitle(categories.get(selected_category).getCategory_name());


        getSelectedCategoryItems(selected_category);
        Log.wtf("category", categories.get(selected_category).getCategory_name());
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
        String[] category = {"sayur", "buah", "bumbu"};

        GridView gridView = (GridView) findViewById(R.id.gridView);
        GridAdapter gridAdapter = new GridAdapter(DetailCategory.this, category);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = items.get(position);
                Log.wtf("selected item", selectedItem.toString());
                Intent intent = new Intent(getApplicationContext(), DetailItem.class);
                Toast.makeText(DetailCategory.this, "clicked"+ items.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}