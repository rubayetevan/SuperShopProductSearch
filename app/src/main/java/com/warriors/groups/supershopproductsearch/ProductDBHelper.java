package com.warriors.groups.supershopproductsearch;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mobile App Develop on 22-12-15.
 */
public class ProductDBHelper extends SQLiteOpenHelper
{
    static final String DATABASE_NAME="ProductInfo";
    static  final int DATABASE_VERSION=1;
    public static final String TABLE_NAME="product_table";

    public static final String COL_PRODUCT_ID="productId";
    public static final String COL_PRODUCT_NAME="productName";
    public static final String COL_PRODUCT_PRICE="productPrice";
    public static final String COL_PRODUCT_CATEGORY="productCategory";
    public static final String COL_PRODUCT_STATUS="productStatus";
    public static final String COL_COMPANY_NAME="companyName";
    public static final String COL_PRODUCT_LOCATION="productLocation";

    public ProductDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static final String CREATE_TABLE_PRODUCT ="CREATE TABLE "+TABLE_NAME +" ( " +COL_PRODUCT_ID +" INTEGER PRIMARY KEY,"+
            COL_PRODUCT_NAME +" TEXT,"+ COL_PRODUCT_PRICE+" TEXT,"+
            COL_PRODUCT_CATEGORY +" TEXT,"+COL_PRODUCT_STATUS+" TEXT,"+COL_COMPANY_NAME+" TEXT,"+COL_PRODUCT_LOCATION+" TEXT)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST"+ TABLE_NAME);

    }
}
