package com.warriors.groups.supershopproductsearch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.warriors.groups.supershopproductsearch.ProductDBHelper;
import com.warriors.groups.supershopproductsearch.ProductModel;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Mobile App Develop on 22-12-15.
 */
public class ProductStorage {
    private ProductDBHelper helper;
    private SQLiteDatabase database;
    private ProductModel productModel;

    public ProductStorage(Context context) {
        helper = new ProductDBHelper(context);
    }

    private void open() {
        database = helper.getWritableDatabase();
    }

    private void close() {
        helper.close();
    }

    public boolean insertProduct(ProductModel productModel)
    {
        this.open();

        ContentValues cv = new ContentValues();
        cv.put(ProductDBHelper.COL_PRODUCT_NAME, productModel.getProductName());
        cv.put(ProductDBHelper.COL_PRODUCT_PRICE, productModel.getProductPrice());
        cv.put(ProductDBHelper.COL_PRODUCT_CATEGORY,productModel.getProductCategory());
        cv.put(ProductDBHelper.COL_PRODUCT_STATUS,productModel.getProductStatus());
        cv.put(ProductDBHelper.COL_COMPANY_NAME,productModel.getCompanyName());
        cv.put(ProductDBHelper.COL_PRODUCT_LOCATION,productModel.getProductLocation());

        long inserted = database.insert(ProductDBHelper.TABLE_NAME, null, cv);
        database.close();

        if (inserted > 0) {
            return true;
        } else {
            return false;
        }

    }


    public boolean updateProduct(int id, ProductModel productModel)
    {
        this.open();

        ContentValues cv = new ContentValues();
        cv.put(ProductDBHelper.COL_PRODUCT_NAME, productModel.getProductName());
        cv.put(ProductDBHelper.COL_PRODUCT_PRICE, productModel.getProductPrice());
        cv.put(ProductDBHelper.COL_PRODUCT_CATEGORY,productModel.getProductCategory());
        cv.put(ProductDBHelper.COL_PRODUCT_STATUS,productModel.getProductStatus());
        cv.put(ProductDBHelper.COL_COMPANY_NAME,productModel.getCompanyName());
        cv.put(ProductDBHelper.COL_PRODUCT_LOCATION,productModel.getProductLocation());

        int updated = database.update(ProductDBHelper.TABLE_NAME, cv, ProductDBHelper.COL_PRODUCT_ID + "= " + id, null);
        database.close();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }
    }


    public boolean deleteProduct(int id)
    {
        this.open();

        int deleted=   database.delete(ProductDBHelper.TABLE_NAME, ProductDBHelper.COL_PRODUCT_ID + "= " + id, null);
        database.close();
        this.close();

        if (deleted > 0) {
            return true;
        } else {
            return false;
        }
    }
    public ArrayList<ProductModel> getAllProduct(){

        ArrayList<ProductModel> productList=new ArrayList<>();
        this.open();
        Cursor cursor=database.query(ProductDBHelper.TABLE_NAME, null, null, null, null, null, null);

        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){

                int productId=cursor.getInt(cursor.getColumnIndex(ProductDBHelper.COL_PRODUCT_ID));
                String productName=cursor.getString(cursor.getColumnIndex(ProductDBHelper.COL_PRODUCT_NAME));
                String productPrice=cursor.getString(cursor.getColumnIndex(ProductDBHelper.COL_PRODUCT_PRICE));
                String productCategory=cursor.getString(cursor.getColumnIndex(ProductDBHelper.COL_PRODUCT_CATEGORY));
                String productStatus=cursor.getString(cursor.getColumnIndex(ProductDBHelper.COL_PRODUCT_STATUS));
                String productLocation=cursor.getString(cursor.getColumnIndex(ProductDBHelper.COL_PRODUCT_LOCATION));
                String companyName=cursor.getString(cursor.getColumnIndex(ProductDBHelper.COL_COMPANY_NAME));
                productModel =new ProductModel(productId,productName,productPrice,productCategory,productStatus,companyName,productLocation);
                productList.add(productModel);
                cursor.moveToNext();
            }
        }
        database.close();
        this.close();
        return productList;
    }

    ///

    public ArrayList<ProductModel> getSearchProduct(String searchText){

        ArrayList<ProductModel> productList=new ArrayList<>();
        this.open();

        String selectQuery = "SELECT  * FROM " + ProductDBHelper.TABLE_NAME+" WHERE "+
                ProductDBHelper.COL_PRODUCT_NAME+" LIKE '%"+searchText+"%'" +" OR "+ProductDBHelper.COL_PRODUCT_CATEGORY+" LIKE '%"+searchText+"%'" ;

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){

                int productId=cursor.getInt(cursor.getColumnIndex(ProductDBHelper.COL_PRODUCT_ID));
                String productName=cursor.getString(cursor.getColumnIndex(ProductDBHelper.COL_PRODUCT_NAME));
                String productPrice=cursor.getString(cursor.getColumnIndex(ProductDBHelper.COL_PRODUCT_PRICE));
                String productCategory=cursor.getString(cursor.getColumnIndex(ProductDBHelper.COL_PRODUCT_CATEGORY));
                String productStatus=cursor.getString(cursor.getColumnIndex(ProductDBHelper.COL_PRODUCT_STATUS));
                String productLocation=cursor.getString(cursor.getColumnIndex(ProductDBHelper.COL_PRODUCT_LOCATION));
                String companyName=cursor.getString(cursor.getColumnIndex(ProductDBHelper.COL_COMPANY_NAME));
                productModel =new ProductModel(productId,productName,productPrice,productCategory,productStatus,companyName,productLocation);
                productList.add(productModel);
                cursor.moveToNext();
            }
        }
        database.close();
        this.close();
        return productList;
    }

    public ProductModel getSingleProduct(int pId)
    {
        ProductModel singleProduct=new ProductModel();

        this.open();

        String selectQuery = "SELECT  * FROM " + ProductDBHelper.TABLE_NAME+" WHERE "+
                ProductDBHelper.COL_PRODUCT_ID + " = "+pId;

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor!=null && cursor.getCount()>0) {

            cursor.moveToFirst();

            int productId = cursor.getInt(cursor.getColumnIndex(ProductDBHelper.COL_PRODUCT_ID));
            String productName = cursor.getString(cursor.getColumnIndex(ProductDBHelper.COL_PRODUCT_NAME));
            String productPrice = cursor.getString(cursor.getColumnIndex(ProductDBHelper.COL_PRODUCT_PRICE));
            String productCategory = cursor.getString(cursor.getColumnIndex(ProductDBHelper.COL_PRODUCT_CATEGORY));
            String productStatus = cursor.getString(cursor.getColumnIndex(ProductDBHelper.COL_PRODUCT_STATUS));
            String productLocation = cursor.getString(cursor.getColumnIndex(ProductDBHelper.COL_PRODUCT_LOCATION));
            String companyName = cursor.getString(cursor.getColumnIndex(ProductDBHelper.COL_COMPANY_NAME));
            // productModel =new ProductModel(productId,productName,productPrice,productCategory,productStatus,companyName,productLocation);
            singleProduct.setProductName(productName);
            singleProduct.setProductPrice(productPrice);
            singleProduct.setProductCategory(productCategory);
            singleProduct.setProductStatus(productStatus);
            singleProduct.setProductLocation(productLocation);
            singleProduct.setCompanyName(companyName);
                }
        database.close();
        this.close();
            return singleProduct;
           }

    public long getDatabaseSize()
    {
        this.open();
        long size = database.getMaximumSize();
        database.close();
        this.close();
        return size;
    }


}
