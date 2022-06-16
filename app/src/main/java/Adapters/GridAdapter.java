package Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Supermarket.R;

import java.util.ArrayList;

import Models.Item;
import Services.ItemService;

public class GridAdapter extends BaseAdapter {
    Context context;
    String[] category;
    private ArrayList<Item> items = ItemService.selectedCategoryItems;
    LayoutInflater inflater;
    public GridAdapter(Context context, String[] category) {
        this.context = context;
        this.category = category;
//        Log.wtf("test", category[0]);
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
//        Log.wtf("test", category[0]);
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
//        Log.wtf("test", category[position]);
        tv_title.setText(items.get(position).getTitle());
        tv_desc.setText(items.get(position).getDescription());
        iv_image.setImageResource(R.drawable.noimage);
        tv_price.setText(Integer.toString(items.get(position).getPrice()));

        return convertView;
    }
}
