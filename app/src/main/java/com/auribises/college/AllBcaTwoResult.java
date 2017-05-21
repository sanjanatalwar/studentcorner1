package com.auribises.college;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AllBcaTwoResult extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    EditText editTextSearch;
    ArrayList<StudentBca2> studentList;
    Bca2ResultAdapter adapter;
    int pos;
    StudentBca2 studentBca2;
    RequestQueue requestQueue;


    void init(){
        listView=(ListView)findViewById(R.id.listview);
        editTextSearch=(EditText)findViewById(R.id.search);
        requestQueue = Volley.newRequestQueue(this);
        studentBca2=new StudentBca2();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_bca_two_result);
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


    void retrieveFromCloud(){


        studentList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Util.RETERIEVE_BCA2_PHP, new Response.Listener<String>() {
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
                        mm=jObj.getInt("mathMarks");
                        cm=jObj.getInt("unixMarks");
                        pm=jObj.getInt("dataStructureMarks");





                        pass= jObj.getString("password");

                        studentList.add(new StudentBca2(id,n,p,e,a,g,c,"",d,0,mm,cm,pm,pass));
                        Log.i("test",studentList.toString());
                        Log.i("math", String.valueOf(studentBca2.getMathMarks()));
                    }

                    adapter = new Bca2ResultAdapter(AllBcaTwoResult.this, R.layout.list_item_bca2result, studentList);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(AllBcaTwoResult.this);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("test",e.toString());
                    Toast.makeText(AllBcaTwoResult.this, "One Some Exception"+e.toString(), Toast.LENGTH_LONG).show();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AllBcaTwoResult.this,"Two Some Error",Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest); // Execute the Request
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        pos=position;
        studentBca2=studentList.get(position);
    }
}
