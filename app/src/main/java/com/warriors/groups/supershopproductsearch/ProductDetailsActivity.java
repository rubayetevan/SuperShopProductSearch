package com.warriors.groups.supershopproductsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductDetailsActivity extends AppCompatActivity
{
    ArrayList<ProductModel> productModels;
    ProductStorage productStorage;

    TextView productIdDetailsTV;
    TextView productNameDetailsTV;
    TextView companyNameDetailsTV;
    TextView productPriceDetailsTV;
    TextView productCategoryDetailsTV;
    TextView productLocationDetailsTV;
    TextView productStatusDetailsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Initialization();
        ShowData();
    }

    private void ShowData()
    {
        productModels = productStorage.getAllProduct();
        String positionString = getIntent().getStringExtra("position");
        int tempPosition = Integer.valueOf(positionString);
        int position=0;
        if (tempPosition>0)
        {
            position = tempPosition-1;
        }
        else
        {
            position=tempPosition;
        }
        Integer productIdPosition = (productModels.get(position)).getProductId();
        String productNamePosition = (productModels.get(position)).getProductName();
        String productPricePosition = (productModels.get(position)).getProductPrice();
        String productStatusPosition = (productModels.get(position)).getProductStatus();
        String companyNamePosition = (productModels.get(position)).getCompanyName();
        String productLocationPosition = (productModels.get(position)).getProductLocation();
        String productStCategoryPosition = (productModels.get(position)).getProductCategory();


        productIdDetailsTV.setText(String.valueOf(productIdPosition));

        productNameDetailsTV.setText(productNamePosition);
        companyNameDetailsTV.setText(companyNamePosition);
        productPriceDetailsTV.setText(productPricePosition+" BDT");
        productCategoryDetailsTV.setText(productStCategoryPosition);
        productLocationDetailsTV.setText(productLocationPosition);
        productStatusDetailsTV.setText(productStatusPosition);
    }

    private void Initialization()
    {
        productStorage = new ProductStorage(getApplicationContext());
        productIdDetailsTV = (TextView) findViewById(R.id.productIdDetailsTV);
        productNameDetailsTV = (TextView) findViewById(R.id.productNameDetailsTV);
        companyNameDetailsTV = (TextView) findViewById(R.id.companyNameDetailsTV);
        productPriceDetailsTV = (TextView) findViewById(R.id.productPriceDetailsTV);
        productCategoryDetailsTV = (TextView) findViewById(R.id.productCategoryDetailsTV);
        productLocationDetailsTV = (TextView) findViewById(R.id.productLocationDetailsTV);
        productStatusDetailsTV = (TextView) findViewById(R.id.productStatusDetailsTV);
    }
}
