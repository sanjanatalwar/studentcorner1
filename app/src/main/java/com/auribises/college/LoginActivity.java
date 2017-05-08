package com.auribises.college;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
Button login;
    RequestQueue requestQueue;
    EditText name,epassword;
    String email,password;

    Teachers teachers;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name=(EditText)findViewById(R.id.editTextName);
        requestQueue= Volley.newRequestQueue(this);
        epassword=(EditText)findViewById(R.id.editTextPassword);
         teachers = new Teachers();

        sharedPreferences = getSharedPreferences(Util.PREFS_NAME,MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
login=(Button)findViewById(R.id.login);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.studentRegistrationt) {
            Intent intent=new Intent(LoginActivity.this,Student_Registration.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.teacherRegistration) {

            Intent intent=new Intent(LoginActivity.this,TeacherRegistration.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
                  Intent i=new Intent(LoginActivity.this,SlideShowActivity.class);
            startActivity(i);



        } else if (id == R.id.nav_manage) {
            Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ggnimtldh.org"));
            startActivity(i);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void logIn() {


        final StringRequest request = new StringRequest(Request.Method.POST, Util.TEACHER_Login_PHP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                     JSONObject  jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("teacher");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                         Teachers teachers=new Teachers();
                        teachers.setTeacherSubject(jsonObject1.getString("teacherSubject"));
                        teachers.setTeacherName(jsonObject1.getString("teacherName"));
                         editor.putString(Util.KEY_NAME,teachers.getTeacherName());
                        editor.putString(Util.KEY_SUBJECT,teachers.getTeacherSubject());
//                        Log.i("SUBJECTS",teachers.getTeacherSubject());
                    }

                    //startActivity(new Intent(LoginActivity.this,AllStudentActivity.class));
                    startActivity(new Intent(LoginActivity.this,TeacherOption.class));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                editor.commit();




            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Some Error" + error.getMessage(), Toast.LENGTH_LONG).show();
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



    public  void onClick(View v){
        int id=v.getId();
        if(id==R.id.login){
            email=name.getText().toString().trim();
            password=epassword.getText().toString().trim();

              logIn();


        }
    }
}
