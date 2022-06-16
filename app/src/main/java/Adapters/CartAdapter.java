package Adapters;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Supermarket.R;

import java.text.BreakIterator;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import Models.CartItem;
import Services.UserServices;
import Store.Store;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<CartItem> cartItems;

//    private ArrayList<Product> products;
//    private OnNoteListener OnNoteViewListener;

    private Drawable image;
    public static class ViewHolder extends RecyclerView.ViewHolder{
//        public static tv_title;
        private final TextView tv_title, tv_price, tv_quantity, tv_totalPrice;
        private final ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            tv_title = (TextView) view.findViewById(R.id.tv_item_title);
            tv_price = (TextView) view.findViewById(R.id.tv_item_price);
            tv_quantity = (TextView) view.findViewById(R.id.tv_item_quantity);
            tv_totalPrice = (TextView) view.findViewById(R.id.tv_totalPrice);
            imageView = (ImageView) view.findViewById(R.id.iv_image);


        }

        public TextView getTextView() {
            return null;
        }

    }

    public CartAdapter() {
        this.cartItems = Store.cartItems.stream().filter(i -> i.getUserID() == UserServices.currentUser.getUserId()).collect(Collectors.toList());
        System.out.println(UserServices.currentUser.getUserId());
        System.out.println(cartItems);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_cart, viewGroup, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.imageView.setImageResource(R.drawable.noimage);
        viewHolder.tv_title.setText(cartItems.get(position).getItem().getTitle());
        viewHolder.tv_price.setText(Integer.toString(cartItems.get(position).getItem().getPrice()));
        viewHolder.tv_quantity.setText(Integer.toString(cartItems.get(position).getCount()));
        viewHolder.tv_totalPrice.setText(Integer.toString(cartItems.get(position).getItem().getPrice() * cartItems.get(position).getCount()));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }
}
