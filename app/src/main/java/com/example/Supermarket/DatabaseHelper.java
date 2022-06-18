package com.example.Supermarket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

        query  = "CREATE TABLE MsTransaction(ID INTEGER PRIMARY KEY AUTOINCREMENT, TransactionID, ItemID, " +
                "FOREIGN KEY(ItemID) REFERENCES MsItem(ItemID) ON DELETE CASCADE ON UPDATE CASCADE)";
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
}


