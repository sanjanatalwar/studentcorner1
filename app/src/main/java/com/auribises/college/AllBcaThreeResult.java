package com.auribises.college;

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

public class AllBcaThreeResult extends AppCompatActivity implements AdapterView.OnItemClickListener {


    ListView listView;
    EditText editTextSearch;
    ArrayList<StudentBca3> studentList;
    Bca3ResultAdapter adapter;
    int pos;
    StudentBca3 studentBca3;
    RequestQueue requestQueue;

    void init(){
        listView=(ListView)findViewById(R.id.listview);
        editTextSearch=(EditText)findViewById(R.id.search);
        requestQueue = Volley.newRequestQueue(this);
        studentBca3=new StudentBca3();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_bca_three_result);
        init();
        retrieveFromCloud();
    }


    void retrieveFromCloud(){


        studentList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Util.RETERIEVE_BCA3_PHP, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                Log.i("test",response.toString());

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("students");

                    int id = 0;
                    String n = "", p = "", e = "",  a = "", g=" ", c=" ", m=" ";
                    int d=0 ,y=0,mm=0,cm=0,pm=0;
                    String pass="";
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jObj = jsonArray.getJSONObject(i);

                        id = jObj.getInt("id");
                        n = jObj.getString("stuName");
                        p = jObj.getString("stuPhone");
                        e = jObj.getString("stuEmail");
                        a=jObj.getString("stuAddress");
                        g = jObj.getString("gender");
                        c=jObj.getString("studentClass");
                        //m=jObj.getString("studentBirthMonth");
                        d=jObj.getInt("studentBirthDate");

                        //y=jObj.getInt("studentBirthYear");
                        mm=jObj.getInt("graphicsMarks");
                        cm=jObj.getInt("operatingSystemMarks");
                        pm=jObj.getInt("mathMarks");

                        pass= jObj.getString("password");

                        studentList.add(new StudentBca3(id,n,p,e,a,g,c,"",d,0,mm,cm,pm,pass));
                        Log.i("test",studentList.toString());
                        Log.i("math", String.valueOf(studentBca3.getMathMarks()));
                    }

                    adapter = new Bca3ResultAdapter(AllBcaThreeResult.this, R.layout.list_item_bca3result, studentList);
                    Log.i("tooo",studentList.toString());
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(AllBcaThreeResult.this);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("test",e.toString());
                    Toast.makeText(AllBcaThreeResult.this, "One Some Exception"+e.toString(), Toast.LENGTH_LONG).show();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AllBcaThreeResult.this,"Two Some Error",Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest); // Execute the Request
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        pos=position;
        studentBca3=studentList.get(position);

    }
}
