package com.auribises.college;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AdminBca2List extends AppCompatActivity  implements AdapterView.OnItemClickListener {

    ListView listView;
    EditText editTextSearch;
    ArrayList<StudentBca2> studentList;
    AdminBca2Adapter adapter;
    StudentBca2 studentBca2;
    int pos;
    RequestQueue requestQueue;


    void init() {

        listView = (ListView) findViewById(R.id.listview);
        editTextSearch = (EditText) findViewById(R.id.search);
        requestQueue = Volley.newRequestQueue(this);
        studentBca2 = new StudentBca2();
        requestQueue = Volley.newRequestQueue(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_bca2_list);

        init();
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString();
                if(adapter!=null){
                    adapter.filter(str);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        retrieveFromCloud();
    }

    void retrieveFromCloud() {


        studentList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Util.RETERIEVE_BCA2_PHP, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                Log.i("test", response.toString());

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("students");

                    int id = 0;
                    String n = "", p = "", e = "", a = "", g = " ", c = " ", m = " ";
                    int d = 0, y = 0, mm = 0, cm = 0, pm = 0;
                    String pass = "";
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jObj = jsonArray.getJSONObject(i);

                        id = jObj.getInt("id");
                        n = jObj.getString("stuName");
                        p = jObj.getString("stuPhone");
                        e = jObj.getString("stuEmail");
                        a = jObj.getString("stuAddress");
                        g = jObj.getString("gender");
                        c = jObj.getString("studentClass");
                        //m=jObj.getString("studentBirthMonth");
                        d = jObj.getInt("studentBirthDate");
                        //y=jObj.getInt("studentBirthYear");
                        mm = jObj.getInt("mathMarks");
                        //cm = jObj.getInt("cMArks");
                        //pm = jObj.getInt("punjabiMarks");
                        pass = jObj.getString("password");

                        studentList.add(new StudentBca2(id, n, p, e, a, g, c, "", mm, cm, pm, d, 0, pass));
                        Log.i("test", studentList.toString());
                    }

                    adapter = new AdminBca2Adapter(AdminBca2List.this, R.layout.adminbca2list_item, studentList);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(AdminBca2List.this);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("test", e.toString());
                    Toast.makeText(AdminBca2List.this, "One Some Exception" + e.toString(), Toast.LENGTH_LONG).show();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AdminBca2List.this, "Two Some Error", Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest); // Execute the Request
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        pos = position;
        studentBca2 = studentList.get(position);

        showOptions();
    }

    void showOptions(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] items ={"Delete"};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                switch (i){
                    case 0:
                        deleteStudent();
                        break;


                }

            }
        });
        //AlertDialog dialog = builder.create();
        //dialog.show();

        builder.create().show();
    }

    void deleteStudent(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete "+ studentBca2.getStuName());
        builder.setMessage("Do you wish to Delete?");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                /*// Code goes here to delete record from DB
                String where = Util.COL_ID+ " = "+student.getId();
                //String where = Util.COL_NAME+ " = '"+student.getName()+"'";
                int j = resolver.delete(Util.STUDENT_URI,where,null);
                if(j>0){
                    studentList.remove(pos);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(AllStudentsActivity.this,student.getName()+" deleted successfully..",Toast.LENGTH_LONG).show();
                }*/
                deleteFromCloud();

            }
        });
        builder.setNegativeButton("Cancel",null);
        builder.create().show();
    }

    void deleteFromCloud(){
        //progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, Util.BCA_TWO_DELETE_PHP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject jsonObject = new JSONObject(response);
                    int success = jsonObject.getInt("success");
                    String message = jsonObject.getString("message");

                    if(success == 1){
                        studentList.remove(pos);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(AdminBca2List.this,message,Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(AdminBca2List.this,message,Toast.LENGTH_LONG).show();
                    }
                    //progressDialog.dismiss();
                }catch (Exception e){
                    e.printStackTrace();
                    //progressDialog.dismiss();
                    Toast.makeText(AdminBca2List.this,"Some Exception",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //progressDialog.dismiss();
                Toast.makeText(AdminBca2List.this,"Some Volley Error: "+error.getMessage(),Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("id",String.valueOf(studentBca2.getId()));
                return map;
            }
        };

        requestQueue.add(request); // Execution of HTTP Request

    }
}

