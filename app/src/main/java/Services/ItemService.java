package Services;

import android.graphics.drawable.Drawable;

import com.example.Supermarket.DatabaseHelper;

import java.io.InputStream;
import java.io.ObjectStreamException;
import java.net.URL;
import java.util.ArrayList;

import Models.CartItem;
import Models.Item;

public class ItemService {
    public static ArrayList<Item> itemList = new ArrayList<Item>();
    public static ArrayList<Item.Category> category = new ArrayList<Item.Category>();
    public static ArrayList<CartItem> userTransactions = new ArrayList<>();
    public static Item selectedItem = null;

    public static void generateItemData(){
        category.add(new Item.Category(0, "Sayur dan Buah"));
        category.add(new Item.Category(1, "Daging dan Seafood"));
        category.add(new Item.Category(2, "Dairy"));
        category.add(new Item.Category(3, "Bumbu Dapur"));
    }

public static Drawable LoadImageFromURL(String url) {
    try {
        InputStream inputStream = (InputStream) new URL(url).getContent();
        Drawable image = Drawable.createFromStream(inputStream, "src name");
        return image;
    } catch (Exception e) {
        return null;
    }
}
}
