package com.example.Supermarket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

import Models.CartItem;
import Models.Item;
import Models.Transaction;

public class DatabaseHelper extends SQLiteOpenHelper {
    private final static String database_name = "SupermarketDB";
    private final static SQLiteDatabase.CursorFactory factory = null;
    private final static int version = 1;

    public DatabaseHelper(Context ctx){
        super(ctx, database_name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE MsUser (UserID INTEGER PRIMARY KEY AUTOINCREMENT, emailAddress, username, phoneNumber, password)";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE MsItem (ItemID INTEGER PRIMARY KEY AUTOINCREMENT, ItemName, ItemDescription, ItemPrice, ItemCategory, ItemImage)";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE MsCart (CartID INTEGER PRIMARY KEY AUTOINCREMENT, UserID, ItemID, Quantity," +
                "FOREIGN KEY(UserID) REFERENCES MsUser(UserID) ON DELETE CASCADE ON UPDATE CASCADE," +
                "FOREIGN KEY(ItemID) REFERENCES MsItem(ItemID) ON DELETE CASCADE ON UPDATE CASCADE)";
        sqLiteDatabase.execSQL(query);

        query  = "CREATE TABLE MsTransaction(ID INTEGER PRIMARY KEY AUTOINCREMENT, TransactionID, ItemID, UserID, Quantity, TransactionDate," +
                "FOREIGN KEY(UserID) REFERENCES MsCart(UserID) ON DELETE CASCADE ON UPDATE CASCADE," +
                "FOREIGN KEY(ItemID) REFERENCES MsItem(ItemID) ON DELETE CASCADE ON UPDATE CASCADE)";
        sqLiteDatabase.execSQL(query);

        query = "INSERT INTO MsItem(ItemName, ItemDescription, ItemPrice, ItemCategory, ItemImage) VALUES " +
                "('Greenfields Fresh Milk', '1 liter', 30250, 'Dairy', 'https://cdn.shopify.com/s/files/1/0369/8321/0116/products/08_553x.jpg?v=1649744908')," +
                "('Brookfarm Soya Milk', '946ml', 40250, 'Dairy', 'https://sukandadjaya.com/wp-content/uploads/2016/06/BF-Soya-Depan.png')," +
                "('Cimory Fresh Milk Plain', '950ml', 28500, 'Dairy', 'https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//89/MTA-11238317/cimory_cimory-fresh-milk-plain-950ml_full01.jpg')," +
                "('Cimory Yogurt Drink Lf Blueberry', '250ml', 9000, 'Dairy', 'https://cf.shopee.co.id/file/892df5bd0927ab418e592dc72a1f8f89')," +
                "('Biokul Stir Yght Apricot', '1 liter', 88500, 'Dairy', 'https://sukandadjaya.com/wp-content/uploads/2016/08/152713-copy.png')," +
                "('Kraft Cheese Slice Plain', '5 slices', 12500, 'Dairy', 'https://images.tokopedia.net/img/cache/700/product-1/2020/5/13/66565449/66565449_aa57bba0-2ee8-4752-b1e5-5229360bc61f_600_600')," +
                "('Cumi Ring', '500gr', 66900, 'Daging dan Seafood', 'https://cdn.istyle.im/images/product/web/31/41/07/00/0/000000074131_01_800.jpg.webp')," +
                "('Teri Padang Pack', '150gr', 35900, 'Daging dan Seafood', 'https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//105/MTA-25885455/oem_teri-padang-pack-150-gr_full01.jpg')," +
                "('Jambal Roti Pack', '250gr', 66500, 'Daging dan Seafood', 'https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//101/MTA-13708585/oem_oem_full01.jpg')," +
                "('Ayam Broiler', '800gr', 35000, 'Daging dan Seafood', 'https://cf.shopee.co.id/file/c6d216046d8de3ed849cdc9e6c77802e')," +
                "('Ayam Kepala', '460gr', 14900, 'Daging dan Seafood', 'https://images.tokopedia.net/img/cache/500-square/VqbcmM/2022/1/13/0d06f73e-b151-40f7-8091-c98a0894e51b.jpg')," +
                "('Peda Merah Pack', '250gr', 36900, 'Daging dan Seafood', 'https://images.tokopedia.net/img/cache/200-square/hDjmkQ/2021/12/16/a6fa3edf-66e0-47b5-ad7e-c023d7564283.jpg')," +
                "('Edamame Pack', '1kg', 26500, 'Sayur dan Buah', 'https://cf.shopee.co.id/file/844afc53770d4387bf611637538b53b4')," +
                "('Brown Shimeji Mushroom Pack', '150gr', 16900, 'Sayur dan Buah', 'https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//102/MTA-36303887/buna_shimeji_brown_shimeji_mushroom_pack_150_gr_full02_ltn5ano9.jpg')," +
                "('Caisim Ikat', '150gr', 2500, 'Sayur dan Buah', 'https://cf.shopee.co.id/file/14b125f228a2e5a1d55b2ca75c417290')," +
                "('Sweet Pear', '430gr', 15370, 'Sayur dan Buah', 'https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//92/MTA-23742263/no-brand_sweet-pear-480-gr_full01.jpg')," +
                "('Pear Xiang Lie', '530gr', 30690, 'Sayur dan Buah', 'https://images.tokopedia.net/img/cache/500-square/hDjmkQ/2021/4/25/434ab895-a37a-469b-b2d4-ea02c8ef3639.jpg')," +
                "('Sunpride Pisang Cavendish', '1050gr', 23050, 'Sayur dan Buah', 'https://images.tokopedia.net/img/cache/700/product-1/2020/9/11/1170277483/1170277483_3443a92f-e017-40c0-bb43-112ccbe24c74_500_500.jpg')," +
                "('Abc Kecap Manis Refill', '700ml', 19900, 'Bumbu Dapur', 'https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//112/MTA-9786748/abc_abc_kecap_manis_700ml_full01_l0mm9csp.jpg')," +
                "('Delmonte Chili Xtra Hot Pouch', '1kg', 23690, 'Bumbu Dapur', 'https://images.tokopedia.net/img/cache/700/VqbcmM/2020/9/22/87d81396-f171-4d79-b18a-03ac629b919a.jpg')," +
                "('Mamasuka Bon Nori Original', '30gr', 16990, 'Bumbu Dapur', 'https://assets.klikindomaret.com/share/20102077_1.jpg')," +
                "('Heinz Korean Gourmet Chili', '325gr', 20190, 'Bumbu Dapur', 'https://cf.shopee.co.id/file/4f41be2a227f5410a9101458d44e156e')," +
                "('Kewpie Salad Dressing Caesar', '200ml', 36290, 'Bumbu Dapur', 'https://cf.shopee.co.id/file/8be48f04ece43254cbbc733c266c7dc5')," +
                "('Maestro Saus Tiram', '270ml', 16790, 'Bumbu Dapur', 'https://cf.shopee.co.id/file/3a3935c7e7f1eb4c42795c9b757e2fec')";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS MsUser";
        sqLiteDatabase.execSQL(query);
        query = "DROP TABLE IF EXISTS MsItem";
        sqLiteDatabase.execSQL(query);
        query = "DROP TABLE IF EXISTS MsCart";
        sqLiteDatabase.execSQL(query);
        query = "DROP TABLE IF EXISTS MsTransaction";
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    //Insert User
    public void insertUser(String emailAddress, String username, String phoneNumber, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("emailAddress", emailAddress);
        contentValues.put("username", username);
        contentValues.put("phoneNumber", phoneNumber);
        contentValues.put("password", password);
        sqLiteDatabase.insert("MsUser", null, contentValues);
        sqLiteDatabase.close();
    }

    //Check if username exists
    public Boolean checkIfUsernameExists(String username){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM MsUser WHERE username = ?", new String[]{username});
        int cur = cursor.getCount();
        cursor.close();
        return cur > 0;
    }

    //Check if email address exists
    public Boolean checkIfEmailAddressExists(String emailAddress){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM MsUser WHERE emailAddress = ?", new String[]{emailAddress});
        int cur = cursor.getCount();
        cursor.close();
        return cur > 0;
    }

    //Check if email address and password matches
    public Boolean validateLogin(String emailAddress, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM MsUser WHERE emailAddress = ? AND password = ?", new String[]{emailAddress, password});
        int cur = cursor.getCount();
        cursor.close();
        return cur > 0;
    }

    public Models.User copyUser(String emailAddress){
        Models.User user;
        int tempUserID;
        String tempEmailAddress, tempUsername, tempPhoneNumber, tempPassword;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM MsUser WHERE emailAddress = ?", new String[]{emailAddress});
        cursor.moveToFirst();
        tempUserID = cursor.getInt(cursor.getColumnIndexOrThrow("UserID"));
        tempEmailAddress = cursor.getString(cursor.getColumnIndexOrThrow("emailAddress"));
        tempUsername = cursor.getString(cursor.getColumnIndexOrThrow("username"));
        tempPhoneNumber = cursor.getString(cursor.getColumnIndexOrThrow("phoneNumber"));
        tempPassword = cursor.getString(cursor.getColumnIndexOrThrow("password"));

        user = new Models.User(tempUserID, tempEmailAddress, tempUsername, tempPhoneNumber, tempPassword);
        cursor.close();
        return user;
    }

    public ArrayList<Models.Item> getCategoryItems(String category){
        ArrayList<Models.Item> item = new ArrayList<>();
        Models.Item tempitem;
        String tempName, tempDescription, tempCategory, tempImage;
        int tempID, tempPrice;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM MsItem WHERE ItemCategory = ?", new String[]{category});
        cursor.moveToFirst();

        if(cursor.getCount() > 0){
            do {
                tempID = cursor.getInt(cursor.getColumnIndexOrThrow("ItemID"));
                tempName = cursor.getString(cursor.getColumnIndexOrThrow("ItemName"));
                tempDescription = cursor.getString(cursor.getColumnIndexOrThrow("ItemDescription"));
                tempPrice = cursor.getInt(cursor.getColumnIndexOrThrow("ItemPrice"));
                tempCategory = cursor.getString(cursor.getColumnIndexOrThrow("ItemCategory"));
                tempImage = cursor.getString(cursor.getColumnIndexOrThrow("ItemImage"));

                tempitem = new Models.Item(tempID, tempName, tempDescription, tempPrice, tempCategory, tempImage);
                item.add(tempitem);

                cursor.moveToNext();
            }while(!cursor.isAfterLast());
        }
        cursor.close();
        return item;
    }

//    public void insertToCart(Integer UserID, Integer ItemID, Integer Quantity){
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("UserID", UserID);
//        contentValues.put("ItemID", ItemID);
//        contentValues.put("Quantity", Quantity);
//        sqLiteDatabase.insert("MsCart", null, contentValues);
//        sqLiteDatabase.close();
//    }
    public void insertToCart(Integer UserID, Integer ItemID, Integer Quantity){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserID", UserID);
        contentValues.put("ItemID", ItemID);
        contentValues.put("Quantity", Quantity);
        sqLiteDatabase.insert("MsCart", null, contentValues);
        sqLiteDatabase.close();
    }

    public void updateQuantity(int UserID, int ItemID, int Quantity){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "UPDATE MsCart SET Quantity = " + Quantity + " WHERE UserID = " + UserID + " AND ItemID = " + ItemID;
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.close();
    }

    public void removeFromCart(int UserID, int ItemID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("MsCart", "UserID = " + UserID + " AND ItemID = " + ItemID + " ", null);
        sqLiteDatabase.close();
    }
    public int getCartCount(int UserID, int ItemID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM MsCart WHERE UserID = " + UserID + " AND ItemID = " + ItemID + " ", null);
        cursor.moveToFirst();
        int count = cursor.getInt(cursor.getColumnIndexOrThrow("Quantity"));
        Log.wtf("getcount", Integer.toString(count));
        cursor.close();
        return count;
    }
    public Boolean checkIfCartExists(int UserID, int ItemID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM MsCart WHERE UserID = " + UserID + " AND ItemID = " + ItemID, null);
        int cur = cursor.getCount();
        cursor.close();
        return cur > 0;
    }

    public ArrayList<CartItem> getUserCart(int UserID){
        ArrayList<CartItem> temp = new ArrayList<>();
        Item tempitem;

        String tempName, tempDescription, tempCategory, tempImage;
        int tempID, tempPrice, tempUserID, tempQuantity;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM MsItem mi JOIN MsCart mc ON mi.ItemID = mc.ItemID WHERE UserID = " + UserID, null);
        cursor.moveToFirst();

        if(cursor.getCount() > 0){
            do {
                tempID = cursor.getInt(cursor.getColumnIndexOrThrow("ItemID"));
                tempName = cursor.getString(cursor.getColumnIndexOrThrow("ItemName"));
                tempDescription = cursor.getString(cursor.getColumnIndexOrThrow("ItemDescription"));
                tempPrice = cursor.getInt(cursor.getColumnIndexOrThrow("ItemPrice"));
                tempCategory = cursor.getString(cursor.getColumnIndexOrThrow("ItemCategory"));
                tempImage = cursor.getString(cursor.getColumnIndexOrThrow("ItemImage"));
                tempUserID = cursor.getInt(cursor.getColumnIndexOrThrow("UserID"));
                tempQuantity = cursor.getInt(cursor.getColumnIndexOrThrow("Quantity"));

                tempitem = new Item(tempID, tempName, tempDescription, tempPrice, tempCategory, tempImage);
                temp.add(new CartItem(tempUserID, tempitem, tempQuantity));

                cursor.moveToNext();
            }while(!cursor.isAfterLast());
        }
        cursor.close();
        return temp;
    }

    public Boolean checkIfTransactionExist(int UserID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM MsTransaction WHERE UserID = " + UserID, null);
        int cur = cursor.getCount();
        cursor.close();
        return cur > 0;
    }
    public int getLastTransactionID(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM MsTransaction ORDER BY ID DESC LIMIT 1", null);
        cursor.moveToFirst();
        int count = cursor.getInt(cursor.getColumnIndexOrThrow("TransactionID"));
        cursor.close();
        return count;
    }
    public void insertToTransaction(int TransactionID, int ItemID, int Quantity, int UserID, Date TransactionDate){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TransactionID", TransactionID);
        contentValues.put("ItemID", ItemID);
        contentValues.put("Quantity", Quantity);
        contentValues.put("UserID", UserID);
        contentValues.put("TransactionDate", TransactionDate.toString());
        sqLiteDatabase.insert("MsTransaction", null, contentValues);
        sqLiteDatabase.close();
    }
    public ArrayList<Transaction> getUserTransaction(int UserID){
        ArrayList<Transaction> temp = new ArrayList<>();
        Item tempitem;

        String tempName, tempDescription, tempCategory, tempImage, tempDate;
        int tempID, tempPrice, tempUserID, tempQuantity, tempTransactionID, tempTotalPrice;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT mi.ItemID, mi.ItemName, mi.ItemDescription, mi.ItemPrice, mi.ItemCategory, mi.ItemImage, mt.UserID, Quantity, TransactionDate, mt.TransactionID, SUM(ItemPrice * Quantity) TotalPrice FROM MsTransaction mt JOIN MsItem mi ON mt.ItemID = mi.ItemID WHERE mt.UserID = " + UserID + " GROUP BY mt.TransactionID", null);
        cursor.moveToFirst();

        if(cursor.getCount() > 0){
            do {

                tempID = cursor.getInt(cursor.getColumnIndexOrThrow("ItemID"));
                tempName = cursor.getString(cursor.getColumnIndexOrThrow("ItemName"));
                tempDescription = cursor.getString(cursor.getColumnIndexOrThrow("ItemDescription"));
                tempPrice = cursor.getInt(cursor.getColumnIndexOrThrow("ItemPrice"));
                tempCategory = cursor.getString(cursor.getColumnIndexOrThrow("ItemCategory"));
                tempImage = cursor.getString(cursor.getColumnIndexOrThrow("ItemImage"));
                tempUserID = cursor.getInt(cursor.getColumnIndexOrThrow("UserID"));
                tempQuantity = cursor.getInt(cursor.getColumnIndexOrThrow("Quantity"));
                tempDate = cursor.getString(cursor.getColumnIndexOrThrow("TransactionDate"));
                tempTransactionID = cursor.getInt(cursor.getColumnIndexOrThrow("TransactionID"));
                tempTotalPrice = cursor.getInt(cursor.getColumnIndexOrThrow("TotalPrice"));

                tempitem = new Item(tempID, tempName, tempDescription, tempPrice, tempCategory, tempImage);
                temp.add(new Transaction(tempTransactionID,tempUserID, tempitem, tempQuantity, tempTotalPrice,tempDate));

                cursor.moveToNext();
            }while(!cursor.isAfterLast());
        }
        cursor.close();
        return temp;
    }
    public void deleteUserTransaction(int UserID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("DELETE FROM  MsCart WHERE UserID = " + UserID, null);
        cursor.close();
    }
    public void removeCartAfterTransaction(int UserID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("MsCart", "UserID = " + UserID, null);
        sqLiteDatabase.close();
    }
}


