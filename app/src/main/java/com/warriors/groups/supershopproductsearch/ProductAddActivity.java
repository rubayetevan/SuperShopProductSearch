package com.warriors.groups.supershopproductsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductAddActivity extends AppCompatActivity
{
    private String productStatus;
    private String productCategory;
    ArrayList<ProductModel> productModels;
    EditText productNameET;
    EditText productPriceET;
    EditText productLocationET;
    EditText companyNameET;
    ProductStorage productStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);
        productNameET = (EditText) findViewById(R.id.productNameET);
        productPriceET = (EditText) findViewById(R.id.productPriceET);
        productLocationET = (EditText) findViewById(R.id.productLocationET);
        companyNameET = (EditText) findViewById(R.id.productCompanyET);
        productStorage = new ProductStorage(getApplicationContext());
        productCategorySpinner();
        productStatusSpinner();
    }

    public void onClickProductSave(View view)
    {
        String productName = productNameET.getText().toString();
        String productPrice = productPriceET.getText().toString();
        String productLocation = productLocationET.getText().toString();
        String companyName = companyNameET.getText().toString();
        boolean state = false;

        if (productName.matches("") ||productPrice.matches("") )
        {
            Toast.makeText(getApplication(), "Product Name & Price Can not be blank", Toast.LENGTH_LONG).show();
        }

        else {
            ArrayList<ProductModel> productModelArrayList = new ArrayList<>();
            productModelArrayList = productStorage.getAllProduct();
            int dataBaseSize = productModelArrayList.size();
            if (dataBaseSize != 0) {
                productModels = productStorage.getSearchProduct(productName.replaceAll("\\s+",""));
                int lastPoint = productModels.size();
                if (lastPoint!=0) {
                    for (int i = 0; i < lastPoint; i++) {
                        if ((((productModels.get(i)).getProductName()).replaceAll("\\s+", "")).equalsIgnoreCase(productName.replaceAll("\\s+","")) && (productModels.get(i)).getProductPrice().equalsIgnoreCase(productPrice) && (((productModels.get(i)).getCompanyName()).replaceAll("\\s+","")).equalsIgnoreCase(companyName.replaceAll("\\s+","")))
                        {
                            state = false;
                            break;
                        } else {
                            state = true;
                        }
                    }

                    if (state) {
                        ProductModel productModel = new ProductModel(productName, productPrice, productCategory, productStatus, companyName, productLocation);
                        boolean insert = productStorage.insertProduct(productModel);
                        if (insert) {
                            Toast.makeText(getApplication(), "Product Added Successfully", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplication(), "Failed", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getApplication(), "Product already Exists", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    ProductModel productModel = new ProductModel(productName, productPrice, productCategory, productStatus, companyName, productLocation);
                    boolean insert = productStorage.insertProduct(productModel);
                    if (insert) {
                        Toast.makeText(getApplication(), "Product Added Successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplication(), "Failed", Toast.LENGTH_LONG).show();
                    }
                }

            }
            else
            {
                ProductModel productModel = new ProductModel(productName, productPrice, productCategory, productStatus, companyName, productLocation);
                boolean insert = productStorage.insertProduct(productModel);
                if (insert) {
                    Toast.makeText(getApplication(), "Product Added Successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplication(), "Failed", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void productCategorySpinner()
    {
        Spinner spinner = (Spinner) findViewById(R.id.productCategorySP);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.product_category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                productCategory = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void productStatusSpinner()
    {
        Spinner spinner = (Spinner) findViewById(R.id.productStatusSP);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.product_status_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                productStatus = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
