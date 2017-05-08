package com.auribises.college;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class UpdateBca3 extends AppCompatActivity {
    EditText bca3marks;
    Button bca3update;
    int signal=0;
    private SharedPreferences preferences;
    StudentBca3 studentBca3;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bca3);

        bca3marks=(EditText)findViewById(R.id.bca3marks);
        bca3update=(Button)findViewById(R.id.bca3update);


        studentBca3=new StudentBca3();
        Intent rcv = getIntent();
        requestQueue= Volley.newRequestQueue(this);

        if(rcv.hasExtra("beanbca31"))
            studentBca3=(StudentBca3)rcv.getSerializableExtra("beanbca31");
        bca3marks.setText(String.valueOf(studentBca3.getGraphicsMarks()));


        if(rcv.hasExtra("beanbca32"))
            studentBca3=(StudentBca3)rcv.getSerializableExtra("beanbca32");
        bca3marks.setText(String.valueOf(studentBca3.getOperatingSystemMarks()));



        if(rcv.hasExtra("beanbca33"))
            studentBca3=(StudentBca3)rcv.getSerializableExtra("beanbca33");
        bca3marks.setText(String.valueOf(studentBca3.getMathMarks()));

    }


    public void updateMarks(){

        preferences=getSharedPreferences(Util.PREFS_NAME,MODE_PRIVATE);
        if(  preferences.getString(Util.KEY_SUBJECT,"").equalsIgnoreCase("Graphics"))
            signal=7;
        else if(preferences.getString(Util.KEY_SUBJECT,"").equalsIgnoreCase("OperatingSystem"))
            signal=8;
        else
        if(preferences.getString(Util.KEY_SUBJECT,"").equalsIgnoreCase("Math"))
            signal=9;


        StringRequest request=new StringRequest(Request.Method.POST, Util.UPDATE_BCA3_PHP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if (jsonObject.getInt("success")==1)
                        Toast.makeText(UpdateBca3.this, "Response: " + "Updated Successfully", Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(UpdateBca3.this, "Response: " + response, Toast.LENGTH_LONG).show();

                Toast.makeText(UpdateBca3.this, "Response: " + response, Toast.LENGTH_LONG).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdateBca3.this, "Some Error" + error.getMessage(), Toast.LENGTH_LONG).show();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();

                map.put("id",String.valueOf(studentBca3.getId()));
                map.put("signal",String.valueOf(signal));
                map.put("marks", bca3marks.getText().toString());
                Log.i("resp",map.toString());



                return map;


            }

        };
        requestQueue.add(request);
    }

    public void onClick(View v){
        int id=v.getId();
        if(id==R.id.bca3update){

            studentBca3.setGraphicsMarks(Integer.parseInt(bca3marks.getText().toString().trim()));
            studentBca3.setOperatingSystemMarks(Integer.parseInt(bca3marks.getText().toString().trim()));
            studentBca3.setMathMarks(Integer.parseInt(bca3marks.getText().toString().trim()));
            updateMarks();

        }
    }
}
