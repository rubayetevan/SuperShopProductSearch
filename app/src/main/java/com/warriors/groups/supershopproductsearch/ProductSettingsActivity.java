package com.warriors.groups.supershopproductsearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ProductSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_settings);
    }
////////////
public void onBackPressed() {

    finish();
    Intent intent = new Intent(ProductSettingsActivity.this, MainActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

    startActivity(intent);
}
////////////
    public void onClickAddItem(View view) {
        Intent intent=new Intent(ProductSettingsActivity.this,ProductAddActivity.class);
        startActivity(intent);
    }
    public void onClickDelete(View view) {

        Intent intent=new Intent(ProductSettingsActivity.this,ProductDeleteActivity.class);
        startActivity(intent);
    }
    public void onClickUpdate(View view) {
        Intent intent=new Intent(ProductSettingsActivity.this,ProductUpdateActivity.class);
        startActivity(intent);
    }
    public void onClickUpdateUser(View view) {
        Intent intent=new Intent(ProductSettingsActivity.this,UpdateUserActivity.class);
        startActivity(intent);
    }
}
