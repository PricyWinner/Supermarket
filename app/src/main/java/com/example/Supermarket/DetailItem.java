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
    private Button btn_minus, btn_plus, btn_addToCart;
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

        dbhelper = new DatabaseHelper(this);
        btn_addToCart = (Button)findViewById(R.id.btn_addTOCart);


        if(dbhelper.checkIfCartExists(UserServices.currentUser.getUserId(), selectedItems.getId())){
            btn_addToCart.setText("Update cart");
            count = dbhelper.getCartCount(UserServices.currentUser.getUserId(), selectedItems.getId());
            et_count.setText(Integer.toString(count));

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
                    dbhelper.updateQuantity(UserServices.currentUser.getUserId(),selectedItems.getId() ,count);
                    Toast toast = Toast.makeText(getApplicationContext(), "cart updated", Toast.LENGTH_SHORT);
                    toast.show();
                    finish();

                }else {
                    Toast toast = Toast.makeText(getApplicationContext(), "items added to cart", Toast.LENGTH_SHORT);
                    toast.show();
                    dbhelper.insertToCart(UserServices.currentUser.getUserId(), selectedItems.getId(),count);
                    System.out.println(Store.cartItems.toString());
                    finish();
                }
            }else{
                if(dbhelper.checkIfCartExists(UserServices.currentUser.getUserId(), selectedItems.getId())){
                    dbhelper.removeFromCart(UserServices.currentUser.getUserId(), selectedItems.getId());
                    Toast toast = Toast.makeText(getApplicationContext(), "cart deleted", Toast.LENGTH_SHORT);
                    toast.show();
                    finish();
                }else{
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
        tv_totalPrice.setText(totalPriceText);
        et_count.setText(Integer.toString(count));

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
    }
}