package Services;

import java.io.ObjectStreamException;
import java.util.ArrayList;

import Models.Item;

public class ItemService {
    public static ArrayList<Item> itemList = new ArrayList<Item>();
    public static ArrayList<Item.Category> category = new ArrayList<Item.Category>();
    public static ArrayList<Item> selectedCategoryItems = new ArrayList<Item>();
    public static Item selectedItem = null;
    public static void generateItemData(){
        category.add(new Item.Category(0, "Sayur dan Buah"));
        category.add(new Item.Category(1, "Daging dan Seafood"));
        category.add(new Item.Category(2, "Dairy"));
        category.add(new Item.Category(3, "Bumbu Dapur"));

        itemList.add(new Item(itemList.size(), "wortel", "900", 10000, (Item.Category) category.get(0)));
        itemList.add(new Item(itemList.size(), "tomat", "70", 5000, (Item.Category) category.get(0)));
        itemList.add(new Item(itemList.size(), "sawi", "900", 10000, (Item.Category) category.get(0)));
        itemList.add(new Item(itemList.size(), "buncis", "70", 7000, (Item.Category) category.get(0)));

        itemList.add(new Item(itemList.size(), "tenderloin", "100", 100000, (Item.Category) category.get(1)));
        itemList.add(new Item(itemList.size(), "tenderloin1", "100", 100000, (Item.Category) category.get(1)));
        itemList.add(new Item(itemList.size(), "tenderloin2", "100", 100000, (Item.Category) category.get(1)));

        itemList.add(new Item(itemList.size(), "susu", "100", 100000, (Item.Category) category.get(2)));
        itemList.add(new Item(itemList.size(), "keju", "100", 100000, (Item.Category) category.get(2)));
        itemList.add(new Item(itemList.size(), "yogurt", "100", 100000, (Item.Category) category.get(2)));

        itemList.add(new Item(itemList.size(), "garam", "100", 100000, (Item.Category) category.get(3)));
        itemList.add(new Item(itemList.size(), "gula", "100", 100000, (Item.Category) category.get(3)));
        itemList.add(new Item(itemList.size(), "lada", "100", 100000, (Item.Category) category.get(3)));
    }

    public static void getSelectedCategoryItems(int categoryID){
        selectedCategoryItems.clear();
        for (Item i:itemList) {
            if(i.getCategory().getId() == categoryID){
                selectedCategoryItems.add(i);
            }
        }
    }
}
