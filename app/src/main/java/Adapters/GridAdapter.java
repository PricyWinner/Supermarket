package Adapters;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Supermarket.DetailCategory;
import com.example.Supermarket.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import Models.Item;
import Services.ItemService;

public class GridAdapter extends BaseAdapter {
    Context context;
    String[] category;
    private ArrayList<Item> items = DetailCategory.items;
    LayoutInflater inflater;
    public GridAdapter(Context context, String[] category) {
        this.context = context;
        this.category = category;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.grid_item, null);
        }

        TextView tv_title = convertView.findViewById(R.id.tv_item_title);
        TextView tv_desc = convertView.findViewById(R.id.tv_item_desc);
        TextView tv_price = convertView.findViewById(R.id.tv_item_price);
        ImageView iv_image = convertView.findViewById(R.id.iv_image);

        tv_title.setText(items.get(position).getTitle());
        tv_desc.setText(items.get(position).getDescription());
        Picasso.with(context).load(items.get(position).getImage()).into(iv_image);

        int price = items.get(position).getPrice();
        String priceText = "Rp" + (NumberFormat.getNumberInstance(Locale.US).format(price));
        tv_price.setText(priceText);

        return convertView;
    }
}
