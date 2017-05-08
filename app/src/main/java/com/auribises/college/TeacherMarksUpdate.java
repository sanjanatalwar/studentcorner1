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

public class TeacherMarksUpdate extends AppCompatActivity {
    EditText studentMarks;
    /////////////////////////////////////////////
int signal=0;
    private SharedPreferences preferences;
    Button update;
    RequestQueue requestQueue;
    StudentBca1 studentBca2;

StudentBca2 studentBcaTwo;
    StudentBca1 studentBca1;
    Teachers teachers;


    void init() {
        requestQueue = Volley.newRequestQueue(this);
studentBcaTwo=new StudentBca2();
        teachers=new Teachers();

        studentMarks = (EditText) findViewById(R.id.UpdateStudentName);
        update = (Button) findViewById(R.id.Update);
        studentBca1 = new StudentBca1();

        studentBca2=new StudentBca1();



        Intent rcv = getIntent();
        if(rcv.hasExtra("bean"))
            studentBca2 = (StudentBca1) rcv.getSerializableExtra("bean");
        studentMarks.setText(String.valueOf(studentBca2.getMathMarks()));



          if(rcv.hasExtra("bean1"))
              studentBca2 = (StudentBca1) rcv.getSerializableExtra("bean1");
                   studentMarks.setText(String.valueOf(studentBca2.getcMArks()));


          if(rcv.hasExtra("bean2"))
              studentBca2=(StudentBca1)rcv.getSerializableExtra("bean2");
              studentMarks.setText(String.valueOf(studentBca2.getPunjabiMarks()));

        ////////////////////////////////////////////////////////////

       /* if(rcv.hasExtra("beanbca21"))
            studentBcaTwo=(StudentBca2)rcv.getSerializableExtra("beanbca21");
        studentMarks.setText(String.valueOf(studentBcaTwo.getMathMarks()));


        if(rcv.hasExtra("beanbca22"))
            studentBcaTwo=(StudentBca2)rcv.getSerializableExtra("beanbca22");
        studentMarks.setText(String.valueOf(studentBcaTwo.getUnixMarks()));



        if(rcv.hasExtra("beanbca23"))
            studentBcaTwo=(StudentBca2)rcv.getSerializableExtra("beanbca23");
        studentMarks.setText(String.valueOf(studentBcaTwo.getDataStructureMarks()));*/
        ///////////////////////////////////////////////////////////////

    }




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_marks_update);
        init();


    }



    public void updateMarks(){


        preferences=getSharedPreferences(Util.PREFS_NAME,MODE_PRIVATE);
      if(  preferences.getString(Util.KEY_SUBJECT,"").equalsIgnoreCase("Math"))
          signal=1;
        else if(preferences.getString(Util.KEY_SUBJECT,"").equalsIgnoreCase("Punjabi"))

          signal=2;
        else
      if(preferences.getString(Util.KEY_SUBJECT,"").equalsIgnoreCase("C"))
            signal=3;


        StringRequest request=new StringRequest(Request.Method.POST, Util.UPDATE_STUDENT_PHP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if (jsonObject.getInt("success")==1)
                    Toast.makeText(TeacherMarksUpdate.this, "Response: " + "Updated Successfully", Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TeacherMarksUpdate.this, "Some Error" + error.getMessage(), Toast.LENGTH_LONG).show();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();

                map.put("id",String.valueOf(studentBca2.getId()));
                map.put("signal",String.valueOf(signal));
                map.put("marks", studentMarks.getText().toString());
                Log.i("respp",map.toString());


                return map;


            }

        };
        requestQueue.add(request);
    }


    public  void onClick(View v){
        int id=v.getId();
        if(id==R.id.Update){



            studentBca1.setMathMarks(Integer.parseInt(studentMarks.getText().toString().trim()));

            studentBca1.setPunjabiMarks(Integer.parseInt(studentMarks.getText().toString().trim()));
            studentBca1.setcMArks(Integer.parseInt(studentMarks.getText().toString().trim()));
            Log.i("mmm",studentBca1.getMathMarks()+"");
            updateMarks();
        }
    }
}
