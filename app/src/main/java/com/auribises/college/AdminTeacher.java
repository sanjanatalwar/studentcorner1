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

public class AdminTeacher extends AppCompatActivity  implements AdapterView.OnItemClickListener{

        ListView listView;
        EditText editTextSearch;
        ArrayList<Teachers> teachersList;
        AdminTeacherAdapter adapter;
        Teachers teachers;
        int pos;
        RequestQueue requestQueue;

        void init() {

        listView = (ListView) findViewById(R.id.listview);
        editTextSearch = (EditText) findViewById(R.id.search);
        requestQueue = Volley.newRequestQueue(this);
        teachers = new Teachers();
        requestQueue= Volley.newRequestQueue(this);
        }


@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_teacher);
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


        teachersList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Util.RETERIEVE_TEACHER_PHP, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                Log.i("test", response.toString());

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("students");

                    int id = 0;
                    String n = "", a = "", s = "", e = "", p = " ", g = " ", pass = " ";

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jObj = jsonArray.getJSONObject(i);

                        id = jObj.getInt("id");
                        n = jObj.getString("teacherName");
                        a = jObj.getString("teacherAddress");
                        s = jObj.getString("teacherSubject");
                        e = jObj.getString("teacherEmail");
                        p = jObj.getString("teacherPhone");
                        g = jObj.getString("gender");

                        pass = jObj.getString("password");

                        teachersList.add(new Teachers(id, n, a, s, e, p, g,pass));
                        Log.i("test", teachersList.toString());
                    }

                    adapter = new AdminTeacherAdapter(AdminTeacher.this, R.layout.adminteacherlist_item, teachersList);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(AdminTeacher.this);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("test", e.toString());
                    Toast.makeText(AdminTeacher.this, "One Some Exception" + e.toString(), Toast.LENGTH_LONG).show();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AdminTeacher.this, "Two Some Error", Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest); // Execute the Request
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        pos=position;
        teachers=teachersList.get(position);

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
        builder.setTitle("Delete "+ teachers.getTeacherName());
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
        StringRequest request = new StringRequest(Request.Method.POST, Util.TEACHER_DELETE_PHP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject jsonObject = new JSONObject(response);
                    int success = jsonObject.getInt("success");
                    String message = jsonObject.getString("message");

                    if(success == 1){
                        teachersList.remove(pos);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(AdminTeacher.this,message,Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(AdminTeacher.this,message,Toast.LENGTH_LONG).show();
                    }
                    //progressDialog.dismiss();
                }catch (Exception e){
                    e.printStackTrace();
                    //progressDialog.dismiss();
                    Toast.makeText(AdminTeacher.this,"Some Exception",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //progressDialog.dismiss();
                Toast.makeText(AdminTeacher.this,"Some Volley Error: "+error.getMessage(),Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("id",String.valueOf(teachers.getId()));
                return map;
            }
        };

        requestQueue.add(request); // Execution of HTTP Request

    }



}




