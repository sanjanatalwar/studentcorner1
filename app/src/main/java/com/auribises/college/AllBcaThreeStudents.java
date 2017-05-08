package com.auribises.college;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AllBcaThreeStudents extends AppCompatActivity implements AdapterView.OnItemClickListener {


    ListView listView;
    EditText editTextSearch;
    ArrayList<StudentBca3> studentList;
    Bca3Adapter adapter;
    StudentBca3 studentBca3;
    private SharedPreferences preferences;
    Teachers teachers;
    int pos;
    RequestQueue requestQueue;


    void init() {
        preferences=getSharedPreferences(Util.PREFS_NAME,MODE_PRIVATE);
        teachers = new Teachers();
        listView = (ListView) findViewById(R.id.listview);
        editTextSearch = (EditText) findViewById(R.id.search);
        requestQueue = Volley.newRequestQueue(this);
        studentBca3 = new StudentBca3();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_bca_three_students);
        init();
        retrieveFromCloud();
    }


    void retrieveFromCloud() {

        studentList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Util.RETERIEVE_BCA3_PHP, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("students");

                    int id = 0;
                    String n = "", p = "", e = "", a = "", g = " ", c = " ", m = " ";
                    int d = 0, y = 0, gm = 0, os = 0, mm = 0;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jObj = jsonArray.getJSONObject(i);

                        id = jObj.getInt("id");
                        n = jObj.getString("stuName");
                        p = jObj.getString("stuPhone");
                        e = jObj.getString("stuEmail");
                        a = jObj.getString("stuAddress");
                        g = jObj.getString("gender");
                        c = jObj.getString("studentClass");
                        m = jObj.getString("studentBirthMonth");
                        d = jObj.getInt("studentBirthDate");
                        //y = jObj.getInt("studentBirthYear");
                        gm = jObj.getInt("graphicsMarks");
                        os = jObj.getInt("operatingSystemMarks");
                        mm = jObj.getInt("mathMarks");

                        studentList.add(new StudentBca3(id, n, p, e, a, g, c, m, d, 0, gm, os, mm));
                        Log.i("test", studentList.toString());
                    }

                    adapter = new Bca3Adapter(AllBcaThreeStudents.this, R.layout.list_item_bca3, studentList);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(AllBcaThreeStudents.this);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("test", e.toString());
                    Toast.makeText(AllBcaThreeStudents.this, "One Some Exception" + e.toString(), Toast.LENGTH_LONG).show();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AllBcaThreeStudents.this, "Two Some Error", Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest); // Execute the Request

    }

    void  setDialog(String str){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(str);
        builder.setMessage("You are not Authorized for"+str);
        builder.setPositiveButton("OK",null);
        builder.setCancelable(false);
        builder.show();
    }

    void showOptions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String[] items = {"Marks of Graphics", "Marks of Operating System", "Marks of Math"};

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                switch (i) {
                    case 0:
                        StudentBca3 studentBca3=studentList.get(pos);
                        if (preferences.getString(Util.KEY_SUBJECT,"").equals("Graphics")) {
                            Intent in = new Intent(AllBcaThreeStudents.this, UpdateBca3.class);
                            in.putExtra("beanbca31",studentBca3);
                            startActivity(in);
                        }

                        else {
                            setDialog(" Graphics");
                        }

                        break;


                    case 1:
                        if (preferences.getString(Util.KEY_SUBJECT,"").equals("OperatingSystem")) {
                            StudentBca3 studentBca31=studentList.get(pos);
                            Intent in = new Intent(AllBcaThreeStudents.this, UpdateBca3.class);
                            in.putExtra("beanbca32",studentBca31);
                            startActivity(in);
                        }

                        else {
                            setDialog(" Operating System");
                        }

                        break;


                    case 2:

                        if (preferences.getString(Util.KEY_SUBJECT,"").equals("Math"))
                        {
                            StudentBca3 studentBca31=studentList.get(pos);
                            Intent inl = new Intent(AllBcaThreeStudents.this, UpdateBca3.class);
                            inl.putExtra("beanbca33",studentBca31);
                            startActivity(inl);
                        }

                        else {
                            setDialog(" Math");
                        }
                        break;

                }

            }
        });

        builder.create().show();
    }






    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        pos=position;
        studentBca3=studentList.get(position);
        showOptions();

    }
}
