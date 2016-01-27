package com.warriors.groups.supershopproductsearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductUpdateActivity extends AppCompatActivity {

    private String productUpdateStatus;
    private String productUpdateCategory;

    EditText productUpdateIDET;
    EditText productUpdateNameET;
    EditText productUpdatePriceET;
    EditText productUpdateLocationET;
    EditText productUpdateCompanyET;

    ProductStorage productStorage;
    Button productUpdateBtn;
    ProductModel productModel = new ProductModel();
    ListView productLV;
    ArrayList<ProductModel> productModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_update);

        productUpdateIDET=(EditText) findViewById(R.id.productUpdateIDET);
        productUpdateNameET = (EditText) findViewById(R.id.productUpdateNameET);
        productUpdatePriceET = (EditText) findViewById(R.id.productUpdatePriceET);
        productUpdateLocationET = (EditText) findViewById(R.id.productUpdateLocationET);
        productUpdateCompanyET = (EditText) findViewById(R.id.productUpdateCompanyET);
        productStorage = new ProductStorage(getApplicationContext());
        productUpdateBtn=(Button) findViewById(R.id.updateBtn);

        productCategorySpinner();
        productStatusSpinner();

        productLV = (ListView) findViewById(R.id.productLV);
        productStorage = new ProductStorage(getApplicationContext());
        showProduct();

    }

    private void productCategorySpinner()
    {
        Spinner spinner = (Spinner) findViewById(R.id.productUpdateCategorySP);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.product_category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                productUpdateCategory = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void productStatusSpinner()
    {
        Spinner spinner = (Spinner) findViewById(R.id.productUpdateStatusSP);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.product_status_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                productUpdateStatus = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        productUpdateIDET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ids = productUpdateIDET.getText().toString();
                if(ids.matches("") || ids.equalsIgnoreCase("0"))
                {
                    productUpdateNameET.setText("");
                    productUpdatePriceET.setText("");
                    productUpdateLocationET.setText("");
                    productUpdateCompanyET.setText("");
                    Toast.makeText(getApplication(), "Product ID is not Valid", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int id = Integer.valueOf(ids);
                    showSearchProduct(id);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void showSearchProduct(int id) {
        ArrayList<ProductModel> productModelss = new ArrayList<>();
        productModelss =productStorage.getAllProduct();
        int maxxID = productModelss.size();
        if (maxxID != 0) {
            int pos = maxxID-1;
            int maxID = (productModelss.get(pos)).getProductId();
            if (id <= maxID) {
                productModel = productStorage.getSingleProduct(id);
                //productUpdateIDET.setText(String.valueOf(productModel.get));
                productUpdateNameET.setText(productModel.getProductName());
                productUpdatePriceET.setText(productModel.getProductPrice());
                productUpdateLocationET.setText(productModel.getProductLocation());
                productUpdateCompanyET.setText(productModel.getCompanyName());
            } else {
                productUpdateIDET.setText("");
                Toast.makeText(getApplication(), "Product ID: " + String.valueOf(id) + " does not exists", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            productUpdateIDET.setText("");
            Toast.makeText(getApplication(), "Product ID: " + String.valueOf(id) + " does not exists", Toast.LENGTH_SHORT).show();
        }

    }

    private void showProduct() {

        productModels = productStorage.getAllProduct();

        ProductLVadapter productLVadapter = new ProductLVadapter(getBaseContext(),productModels);
        productLV.setAdapter(productLVadapter);
        productLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FromfillUp(position);
            }
        });

    }

    private void FromfillUp(int position) {
        productModels = productStorage.getAllProduct();
        Integer productIdPosition = (productModels.get(position)).getProductId();
        String productNamePosition = (productModels.get(position)).getProductName();
        String productPricePosition = (productModels.get(position)).getProductPrice();
        //String productStatusPosition = (productModels.get(position)).getProductStatus();
        String companyNamePosition = (productModels.get(position)).getCompanyName();
        String productLocationPosition = (productModels.get(position)).getProductLocation();
        //String productStCategoryPosition = (productModels.get(position)).getProductCategory();

        productUpdateIDET.setText(String.valueOf(productIdPosition));
        productUpdateNameET.setText(productNamePosition);
        productUpdatePriceET.setText(productPricePosition);
        productUpdateLocationET.setText(productLocationPosition);
        productUpdateCompanyET.setText(companyNamePosition);
    }

    public void onClickUpdate(View view)
    {
        String productUpdateId=productUpdateIDET.getText().toString();
        String productUpdateName = productUpdateNameET.getText().toString();
        String productUpdatePrice = productUpdatePriceET.getText().toString();
        String productUpdateLocation = productUpdateLocationET.getText().toString();
        String productUpdateCompany = productUpdateCompanyET.getText().toString();

        if (productUpdateName.matches("") || productUpdatePrice.matches("") || productUpdateId.matches("") )
        {
            Toast.makeText(getApplication(), "Product ID, Name & Price Can not be blank", Toast.LENGTH_LONG).show();
        }
        else
        {
                ProductModel productModel = new ProductModel(productUpdateName, productUpdatePrice, productUpdateCategory, productUpdateStatus, productUpdateCompany, productUpdateLocation);
                boolean update = productStorage.updateProduct(Integer.parseInt(productUpdateId.toString()), productModel);
                if (update) {
                    Toast.makeText(getApplication(), "Product Updated Successfully", Toast.LENGTH_LONG).show();
                    showProduct();

                } else {
                    Toast.makeText(getApplication(), "Failed", Toast.LENGTH_LONG).show();
                }
        }
    }
}
