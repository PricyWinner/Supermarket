package com.example.Supermarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Models.CartItem;
import Models.Item;
import Services.ItemService;
import Store.Store;

public class DetailItem extends AppCompatActivity {

    private Item selectedItems = ItemService.selectedItem;
    private ImageView imageView;
    private TextView tv_title, tv_desc, tv_price;
    private Button btn_minus, btn_plus;
    private EditText et_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);

        imageView = (ImageView) findViewById(R.id.iv_image);
        tv_title = (TextView) findViewById(R.id.tv_item_title);
        tv_desc = (TextView) findViewById(R.id.tv_item_desc);
        tv_price = (TextView) findViewById(R.id.tv_item_price);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        et_count = (EditText) findViewById(R.id.et_count);
        imageView.setImageResource(R.drawable.noimage);
        tv_title.setText(selectedItems.getTitle());
        tv_desc.setText(selectedItems.getDescription());
        tv_price.setText(Integer.toString(selectedItems.getPrice()));
        btn_plus.setOnClickListener((View v) -> {
            plus();
        });
        btn_minus.setOnClickListener((View v) -> {
            minus();
        });
        et_count.setText("0");

    }

    public void plus() {
        if (Store.cartItems.stream().filter(i -> i.getItem().getId() == selectedItems.getId()).count() > 0) {
            List<CartItem> filteredList = Store.cartItems.stream().filter(i -> i.getItem().getId() == selectedItems.getId()).collect(Collectors.toList());
            filteredList.get(0).setCount(filteredList.get(0).getCount() + 1);

            et_count.setText(Integer.toString(filteredList.get(0).getCount()));
            System.out.println(filteredList.get(0).getCount());

        } else {
            Store.cartItems.add(new CartItem(selectedItems, 1));
//            System.out.println("Adding item to cart");
            et_count.setText("1");
        }
//        System.out.println("plus");
    }

    public void minus() {
        if (Store.cartItems.stream().filter(i -> i.getItem().getId() == selectedItems.getId()).count() > 0) {
            List<CartItem> filteredList = Store.cartItems.stream().filter(i -> i.getItem().getId() == selectedItems.getId()).collect(Collectors.toList());


            if (filteredList.get(0).getCount() > 1) {
                filteredList.get(0).setCount(filteredList.get(0).getCount() - 1);
                et_count.setText(Integer.toString(filteredList.get(0).getCount()));
//                System.out.println("reduce item by 1");
            } else{
                Store.cartItems.remove(filteredList.get(0));
                et_count.setText("0");
//                System.out.println("removing item from list");
            }

            System.out.println(filteredList.get(0).getCount());
        } else {
            et_count.setText("0");
//            System.out.println("no item in cart");
        }
//        System.out.println("minus");
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.btn_minus:
//
//                break;
//            case R.id.btn_plus:
//
//                break;
//        }
//    }
}