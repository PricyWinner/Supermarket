package com.example.Supermarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import Models.CartItem;
import Models.Item;
import Services.ItemService;
import Services.UserServices;
import Store.Store;

public class DetailItem extends AppCompatActivity {

    private Item selectedItems = ItemService.selectedItem;
    private ImageView imageView;
    private TextView tv_title, tv_desc, tv_price, tv_totalPrice;
    private Button btn_minus, btn_plus, btn_buyNow, btn_addToCart;
    private EditText et_count;
    Context context;
    private int count = 0;
    private DatabaseHelper dbhelper;
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);

        imageView = (ImageView) findViewById(R.id.iv_image);
        tv_title = (TextView) findViewById(R.id.tv_item_title);
        tv_desc = (TextView) findViewById(R.id.tv_item_desc);
        tv_price = (TextView) findViewById(R.id.tv_item_price);
        tv_totalPrice = (TextView) findViewById(R.id.tv_totalPrice);

        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        et_count = (EditText) findViewById(R.id.et_count);
//        imageView.setImageResource(R.drawable.noimage);

        dbhelper = new DatabaseHelper(this);
        btn_addToCart = (Button)findViewById(R.id.btn_addTOCart);
        btn_buyNow = (Button)findViewById(R.id.btn_BuyNow);


        if(dbhelper.checkIfCartExists(UserServices.currentUser.getUserId(), selectedItems.getId())){
            btn_addToCart.setText("Update cart");
//            List<CartItem> filteredList = Store.cartItems.stream().filter(i -> i.getItem().getId() == selectedItems.getId() && i.getUserID() == UserServices.currentUser.getUserId()).collect(Collectors.toList());
            count = dbhelper.getCartCount(UserServices.currentUser.getUserId(), selectedItems.getId());
//            tv_totalPrice.setText(Integer.toString(selectedItems.getPrice() * filteredList.get(0).getCount()));
            et_count.setText(Integer.toString(count));
//            System.out.println(Integer.toString(filteredList.get(0).getCount()));

        }
        else{
            et_count.setText("0");
            count = 0;
            btn_addToCart.setText("Add to cart");
        }

        int price = selectedItems.getPrice();
        String priceText = "Rp" + (NumberFormat.getNumberInstance(Locale.US).format(price));

        tv_title.setText(selectedItems.getTitle());
        tv_desc.setText(selectedItems.getDescription());
//        tv_price.setText(Integer.toString(selectedItems.getPrice()));
        tv_price.setText(priceText);
        Picasso.with(context).load(String.valueOf(selectedItems.getImage())).into(imageView);




        btn_plus.setOnClickListener((View v) -> {
            plus();
        });
        btn_minus.setOnClickListener((View v) -> {
            minus();
        });

        btn_addToCart.setOnClickListener((View v) -> {
            if(count != 0){
                if(dbhelper.checkIfCartExists(UserServices.currentUser.getUserId(), selectedItems.getId())){
//                    List<CartItem> filteredList = Store.cartItems.stream().filter(i -> i.getItem().getId() == selectedItems.getId() && i.getUserID() == UserServices.currentUser.getUserId()).collect(Collectors.toList());
//                    filteredList.get(0).setCount(count);
                    dbhelper.updateQuantity(UserServices.currentUser.getUserId(),selectedItems.getId() ,count);
                    Toast toast = Toast.makeText(getApplicationContext(), "cart updated", Toast.LENGTH_SHORT);
                    toast.show();
//                    System.out.println(Store.cartItems.toString());
                    finish();

                }else {
//                    Store.cartItems.add(new CartItem(UserServices.currentUser.getUserId(), selectedItems, count));
                    Toast toast = Toast.makeText(getApplicationContext(), "items added to cart", Toast.LENGTH_SHORT);
                    toast.show();
                    dbhelper.insertToCart(UserServices.currentUser.getUserId(), selectedItems.getId(),count);
                    System.out.println(Store.cartItems.toString());
                    finish();
                }
            }else{
                if(dbhelper.checkIfCartExists(UserServices.currentUser.getUserId(), selectedItems.getId())){
//                    List<CartItem> filteredList = Store.cartItems.stream().filter(i -> i.getItem().getId() == selectedItems.getId() && i.getUserID() == UserServices.currentUser.getUserId()).collect(Collectors.toList());
//                    Store.cartItems.remove(filteredList.get(0));
                    dbhelper.removeFromCart(UserServices.currentUser.getUserId(), selectedItems.getId());
                    Toast toast = Toast.makeText(getApplicationContext(), "cart deleted", Toast.LENGTH_SHORT);
                    toast.show();
                    finish();
                }else{
//                    et_count.setError("item count can't be empty");
                    Toast toast = Toast.makeText(getApplicationContext(), "Item count can't be empty", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }



        });
    }
    public void plus() {
        count = count + 1;
        int totalprice = selectedItems.getPrice() * count;
        String totalPriceText = "Total: Rp" + (NumberFormat.getNumberInstance(Locale.US).format(totalprice));
//        tv_totalPrice.setText(Integer.toString(selectedItems.getPrice() * count));
        tv_totalPrice.setText(totalPriceText);
        et_count.setText(Integer.toString(count));
//            System.out.println(selectedItems.getPrice() * count);

    }

    public void minus() {

        if(count == 0){
            count = 0;
            tv_totalPrice.setText("Total: Rp" + Integer.toString(selectedItems.getPrice() * count));
        }else {
            count = count - 1;
            tv_totalPrice.setText("Total: Rp" + Integer.toString(selectedItems.getPrice() * count));
            et_count.setText(Integer.toString(count));
        }



//        System.out.println("minus");
    }










//    public void plus() {
//        if (Store.cartItems.stream().filter(i -> i.getItem().getId() == selectedItems.getId()).count() > 0) {
//            List<CartItem> filteredList = Store.cartItems.stream().filter(i -> i.getItem().getId() == selectedItems.getId()).collect(Collectors.toList());
//            filteredList.get(0).setCount(filteredList.get(0).getCount() + 1);
//
//            et_count.setText(Integer.toString(filteredList.get(0).getCount()));
//            System.out.println(filteredList.get(0).getCount());
//
//        } else {
//            Store.cartItems.add(new CartItem(selectedItems, 1));
////            System.out.println("Adding item to cart");
//            et_count.setText("1");
//        }
////        System.out.println("plus");
//    }
//
//    public void minus() {
//        if (Store.cartItems.stream().filter(i -> i.getItem().getId() == selectedItems.getId()).count() > 0) {
//            List<CartItem> filteredList = Store.cartItems.stream().filter(i -> i.getItem().getId() == selectedItems.getId()).collect(Collectors.toList());
//
//
//            if (filteredList.get(0).getCount() > 1) {
//                filteredList.get(0).setCount(filteredList.get(0).getCount() - 1);
//                et_count.setText(Integer.toString(filteredList.get(0).getCount()));
////                System.out.println("reduce item by 1");
//            } else{
//                Store.cartItems.remove(filteredList.get(0));
//                et_count.setText("0");
////                System.out.println("removing item from list");
//            }
//
//            System.out.println(filteredList.get(0).getCount());
//        } else {
//            et_count.setText("0");
////            System.out.println("no item in cart");
//        }
////        System.out.println("minus");
//    }

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