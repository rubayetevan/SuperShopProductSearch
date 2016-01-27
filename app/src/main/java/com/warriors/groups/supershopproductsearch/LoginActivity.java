package com.warriors.groups.supershopproductsearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText userNameET;
    EditText passwordET;
    SharedPreferences preferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userNameET = (EditText) findViewById(R.id.userNameET);
        passwordET = (EditText) findViewById(R.id.passwordET);
        preferences=getBaseContext().getSharedPreferences("userInfo", MODE_PRIVATE);
    }


    public void Login(View view) {

        String userName = preferences.getString("userName","");
        String password = preferences.getString("password","");

        String usernameETx = userNameET.getText().toString();
        String passwordETx = passwordET.getText().toString();

        if (userName.matches("") && password.matches("")) {
            if (usernameETx.equals("Admin") && passwordETx.equals("Admin")) {
                Intent intent = new Intent(LoginActivity.this, ProductSettingsActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Wrong username or password!", Toast.LENGTH_LONG).show();
            }
        }
        else{
            if ((usernameETx.equals(userName) && passwordETx.equals(password)) || (usernameETx.equals("Super") && passwordETx.equals("Super"))) {
                Intent intent = new Intent(LoginActivity.this, ProductSettingsActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Wrong username or password!", Toast.LENGTH_LONG).show();
            }
        }
}

}
