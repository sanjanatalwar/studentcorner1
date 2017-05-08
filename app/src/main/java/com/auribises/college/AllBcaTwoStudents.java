package com.auribises.college;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

public class AllBcaTwoStudents extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    EditText editTextSearch;
    ArrayList<StudentBca2> studentList;
    BcaTwoAdapter adapter;
    StudentBca2 studentBca2;
    Teachers teachers;
    private SharedPreferences preferences;
    int pos;
    RequestQueue requestQueue;


    void init() {
        preferences=getSharedPreferences(Util.PREFS_NAME,MODE_PRIVATE);
        teachers = new Teachers();
        listView = (ListView) findViewById(R.id.listview);
        editTextSearch = (EditText) findViewById(R.id.search);
        requestQueue = Volley.newRequestQueue(this);
        studentBca2 = new StudentBca2();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_bca_two_students);
        init();
        retrieveFromCloud();
    }

     void retrieveFromCloud() {

         studentList = new ArrayList<>();

         StringRequest stringRequest = new StringRequest(Request.Method.GET, Util.RETERIEVE_BCA2_PHP, new Response.Listener<String>() {
             @Override

             public void onResponse(String response) {


                 try {
                     JSONObject jsonObject = new JSONObject(response);
                     JSONArray jsonArray = jsonObject.getJSONArray("students");

                     int id = 0;
                     String n = "", p = "", e = "", a = "", g = " ", c = " ", m = " ";
                     int d = 0, y = 0, mm = 0, um = 0, dm = 0;
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
                         mm = jObj.getInt("mathMarks");
                         um = jObj.getInt("unixMarks");
                         dm = jObj.getInt("dataStructureMarks");

                         studentList.add(new StudentBca2(id, n, p, e, a, g, c, m, d, 0, mm, um, dm));
                         Log.i("test", studentList.toString());
                     }

                     adapter = new BcaTwoAdapter(AllBcaTwoStudents.this, R.layout.list_item_bca2, studentList);
                     listView.setAdapter(adapter);
                     listView.setOnItemClickListener(AllBcaTwoStudents.this);

                 } catch (Exception e) {
                     e.printStackTrace();
                     Log.i("test", e.toString());
                     Toast.makeText(AllBcaTwoStudents.this, "One Some Exception" + e.toString(), Toast.LENGTH_LONG).show();

                 }


             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {

                 Toast.makeText(AllBcaTwoStudents.this, "Two Some Error", Toast.LENGTH_LONG).show();
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
    final String[] items = {"Marks of Maths", "Marks of Unix", "Marks of DataStructure"};

    builder.setItems(items, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

            switch (i) {
                case 0:
                    StudentBca2 studentBca2=studentList.get(pos);
                    if (preferences.getString(Util.KEY_SUBJECT,"").equals("Math")) {
                        Intent in = new Intent(AllBcaTwoStudents.this, UpdateBca2.class);
                        in.putExtra("beanbca21",studentBca2);
                        startActivity(in);
                    }

                    else {
                        setDialog(" Math");
                    }

                    break;


                case 1:

                    if (preferences.getString(Util.KEY_SUBJECT,"").equals("Unix")) {
                        StudentBca2 studentBca3=studentList.get(pos);
                        Intent in3 = new Intent(AllBcaTwoStudents.this, UpdateBca2.class);
                        in3.putExtra("beanbca22",studentBca3);
                        startActivity(in3);
                    }

                    else {
                        setDialog(" Unix");
                    }

                    break;


                case 2:

                    if (preferences.getString(Util.KEY_SUBJECT,"").equals("DataStructure")) {
                        StudentBca2 studentBcaTwo=studentList.get(pos);
                        Intent in1 = new Intent(AllBcaTwoStudents.this, UpdateBca2.class);
                        //in.putExtra("beanbca23",studentBca2);
                        in1.putExtra("beanbca23",studentBcaTwo);

                        startActivity(in1);
                    }

                    else {
                        setDialog(" DataStructure");
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
        studentBca2=studentList.get(position);
        showOptions();

    }
}
