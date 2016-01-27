package com.warriors.groups.supershopproductsearch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductDeleteActivity extends AppCompatActivity {
    ListView productLV;
    ArrayList<ProductModel> productModels;
    ProductStorage productStorage;
    EditText deleteIdET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_delete);
        deleteIdET = (EditText) findViewById(R.id.deleteIdET);
        productLV = (ListView) findViewById(R.id.productDeleteLV);
        productStorage = new ProductStorage(getApplicationContext());
        showProduct();
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
        deleteIdET.setText(String.valueOf(productIdPosition));
    }

    public void OnClickDelete(View view)
    {
        final String tempID= deleteIdET.getText().toString();
        int maxxID = productModels.size();

        if(maxxID!=0)
        {
        int pos = maxxID-1;
        int maxID = (productModels.get(pos)).getProductId();
        int id = Integer.valueOf(tempID);
        
        if(tempID.matches("")||tempID.equals("0"))
        {
            Toast.makeText(getApplication(), "ID is not Valid", Toast.LENGTH_LONG).show();
        }
        else {
            if (id <= maxID) {

                new AlertDialog.Builder(ProductDeleteActivity.this)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                int id = Integer.valueOf(tempID);
                                boolean success = productStorage.deleteProduct(id);
                                if (success) {
                                    Toast.makeText(getApplication(), "Product Deleted Successfully", Toast.LENGTH_LONG).show();
                                    showProduct();
                                } else {
                                    Toast.makeText(getApplication(), "Failed", Toast.LENGTH_LONG).show();
                                }

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            } else {
                deleteIdET.setText("");
                Toast.makeText(getApplication(), "Product ID: " + String.valueOf(id) + " does not exists", Toast.LENGTH_SHORT).show();
            }
        }
        }
        else {
            deleteIdET.setText("");
            Toast.makeText(getApplication(), "There is no product in database", Toast.LENGTH_SHORT).show();
        }
    }
}
