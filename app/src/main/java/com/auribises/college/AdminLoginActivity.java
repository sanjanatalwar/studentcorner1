package com.auribises.college;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AdminLoginActivity extends AppCompatActivity {

    EditText  eadminMail;
    EditText eadminPassword;
    RequestQueue requestQueue;
    Button btn;
    String email;
    String password;
    int success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        eadminMail=(EditText)findViewById(R.id.adminName);
        requestQueue= Volley.newRequestQueue(this);
        eadminPassword=(EditText)findViewById(R.id.adminPassword);
        btn=(Button)findViewById(R.id.login);
    }

    public  void login()
    {
        StringRequest request = new StringRequest(Request.Method.POST, Util.ADMIN_Login_PHP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if (jsonObject.getInt("success")==1) {
                        //Toast.makeText(LoginActivity.this, "Response: " + "result is", Toast.LENGTH_LONG).show();
                        Intent i=new Intent(AdminLoginActivity.this,AdminOptions.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(AdminLoginActivity.this, "you are not admin", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }



        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdminLoginActivity.this, "Some Error" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();

                map.put("email", email);


                map.put("password", password);


                return map;
            }


        };
        requestQueue.add(request);

    }



    public void onClick(View v) {
        int id = v.getId();
        if(id==R.id.login)
        {
           email=eadminMail.getText().toString().trim();
            password=eadminPassword.getText().toString().trim();
            login();
            }
        }
    }
