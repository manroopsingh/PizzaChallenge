package com.example.singh.pizzachallenge.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.singh.pizzachallenge.model.NewOrder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by singh on 12-Apr-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "OrdersDB";
    //Table name
    public static final String TABLE_ORDERS = "orders";
    //Column names
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_TOPPINGS = "toppings";
    public static final String COLUMN_FAVOURITE = "favourite";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ORDERS_TABLE = "CREATE TABLE " + TABLE_ORDERS + " ( " +
                COLUMN_TIMESTAMP + " TEXT PRIMARY KEY," +
                COLUMN_NAME + " TEXT," +
                COLUMN_PHONE + " TEXT," +
                COLUMN_QUANTITY + " INTEGER," +
                COLUMN_TOPPINGS + " TEXT," +
                COLUMN_FAVOURITE + " TEXT " +
                ")";

        db.execSQL(CREATE_ORDERS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        onCreate(db);
    }

    public void addOrder(NewOrder newOrder){
        //for logging
        Log.d("addBook", newOrder.toString());

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(COLUMN_TIMESTAMP, newOrder.getTimestamp());
        values.put(COLUMN_NAME, newOrder.getUsername());
        values.put(COLUMN_PHONE, newOrder.getPhone());
        values.put(COLUMN_QUANTITY, newOrder.getQuantity());
        values.put(COLUMN_TOPPINGS, newOrder.getToppings().toString());
        values.put(COLUMN_FAVOURITE, String.valueOf(newOrder.isFavourite()));


        // 3. insert
        db.insert(TABLE_ORDERS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public List<NewOrder> getAllOrders() {
        List<NewOrder> newOrderList = new LinkedList<NewOrder>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_ORDERS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        NewOrder newOrder = null;
        if (cursor.moveToFirst()) {
            do {
                newOrder = new NewOrder();
                newOrder.setTimestamp(cursor.getString(0));
                newOrder.setUsername(cursor.getString(1));
                newOrder.setPhone(cursor.getString(2));
                newOrder.setQuantity(cursor.getInt(3));

                Gson gson = new Gson();
                Type listType = new TypeToken<List<String>>(){}.getType();

                System.out.println("xxxxxxxxxxx" + cursor.getString(5));
                newOrder.setToppings((List<String>) gson.fromJson(cursor.getString(4),listType));
                newOrder.setFavourite(Boolean.parseBoolean(cursor.getString(5)));
                // Add book to books
                newOrderList.add(newOrder);
            } while (cursor.moveToNext());
        }

        return newOrderList;
    }

    public int updateOrder(NewOrder newOrder) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(COLUMN_FAVOURITE, String.valueOf(newOrder.isFavourite()));

        // 3. updating row
        int i = db.update(TABLE_ORDERS, //table
                values, // column/value
                COLUMN_TIMESTAMP+" = ?", // selections
                new String[] { String.valueOf(newOrder.getTimestamp())}); //selection args

        // 4. close
        db.close();

        return i;

    }

}
