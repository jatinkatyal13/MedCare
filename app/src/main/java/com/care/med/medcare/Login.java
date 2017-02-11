package com.care.med.medcare;

import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    Button register;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        register = (Button) findViewById(R.id.register_button);
        login = (Button) findViewById(R.id.login_button);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String username = ((EditText)findViewById(R.id.user_name)).getText().toString().trim();
                String password = ((EditText)findViewById(R.id.password)).getText().toString().trim();
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);

                //changing the login button
                login.setEnabled(false);
                login.setText("Logging in..");

                //changing the register button
                register.setEnabled(false);

                //making the post request
                makePost("localhost/checkSignIn", params);

                //if failed to execute the post

                //changing the login button
                login.setEnabled(true);
                login.setText("Login");

                //changing the register button
                register.setEnabled(true);
            }
        });

    }

    //method to make post request with given parameters
    public void makePost(String url, final Map<String, String> params){
        Intent intent = new Intent(Login.this, Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("username", params.get("username"));
        startActivity(intent);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //response contains the response string from the server

                //TODO
                //for now everything will take to the home with name dev

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                Log.e("debug", error.toString());
            }
        }) {
            protected Map<String, String> getParams() {
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
