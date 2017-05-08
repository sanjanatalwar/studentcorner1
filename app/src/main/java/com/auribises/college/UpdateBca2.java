package com.auribises.college;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class UpdateBca2 extends AppCompatActivity {
int signal=0;
    private SharedPreferences preferences;
    EditText bca2marks;
    Button bca2update;
    StudentBca2 studentBca2;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bca2);

        bca2marks=(EditText)findViewById(R.id.bca2Marks);
        bca2update=(Button)findViewById(R.id.bca2Update);

        studentBca2=new StudentBca2();
        requestQueue= Volley.newRequestQueue(this);

        Intent rcv = getIntent();
        if(rcv.hasExtra("beanbca21"))
            studentBca2=(StudentBca2)rcv.getSerializableExtra("beanbca21");
        bca2marks.setText(String.valueOf(studentBca2.getMathMarks()));


        if(rcv.hasExtra("beanbca22"))
            studentBca2=(StudentBca2)rcv.getSerializableExtra("beanbca22");
        bca2marks.setText(String.valueOf(studentBca2.getUnixMarks()));



        if(rcv.hasExtra("beanbca23"))
            studentBca2=(StudentBca2)rcv.getSerializableExtra("beanbca23");
        bca2marks.setText(String.valueOf(studentBca2.getDataStructureMarks()));
        Log.i("datastructure",String.valueOf(studentBca2.getDataStructureMarks()));
        ///////////////////////////////////////////////////////////////
    }




    public void updateMarks(){


        preferences=getSharedPreferences(Util.PREFS_NAME,MODE_PRIVATE);
        if(  preferences.getString(Util.KEY_SUBJECT,"").equalsIgnoreCase("Math"))
            signal=4;
        else if(preferences.getString(Util.KEY_SUBJECT,"").equalsIgnoreCase("Unix"))
            signal=5;
        else
        if(preferences.getString(Util.KEY_SUBJECT,"").equalsIgnoreCase("DataStructure"))
            signal=6;



        StringRequest request=new StringRequest(Request.Method.POST, Util.UPDATE_BCA2_PHP, new Response.Listener<String>() {



            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if (jsonObject.getInt("success")==1)
                        Toast.makeText(UpdateBca2.this, "Response: " + "Updated Successfully", Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(UpdateBca2.this, "Response: " + response, Toast.LENGTH_LONG).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdateBca2.this, "Some Error" + error.getMessage(), Toast.LENGTH_LONG).show();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();

                map.put("id",String.valueOf(studentBca2.getId()));
                map.put("signal",String.valueOf(signal));
                map.put("marks", bca2marks.getText().toString());
                Log.i("resp",map.toString());

                return map;


            }

        };
        requestQueue.add(request);
    }

    public void onClick(View v){
        int id=v.getId();
        if(id==R.id.bca2Update){

            studentBca2.setMathMarks(Integer.parseInt(bca2marks.getText().toString().trim()));
            studentBca2.setUnixMarks(Integer.parseInt(bca2marks.getText().toString().trim()));
            studentBca2.setDataStructureMarks(Integer.parseInt(bca2marks.getText().toString().trim()));

                        updateMarks();

        }

    }

}
