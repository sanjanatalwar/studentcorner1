package com.auribises.college;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class TeacherRegistration extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();
    EditText teacherName,teacherAddress,teacherSubject,teacherEmail,teacherPhone;
    RadioButton teacherMale,teacherFemale;
    Button buttonSubmit;

    Teachers teachers;
    RequestQueue requestQueue;

    String email,subject,comment;


    void init()
    {

        teacherName = (EditText)findViewById(R.id.teacherName);
        teacherAddress =(EditText)findViewById(R.id.teacherAddress);
        teacherSubject = (EditText)findViewById(R.id.teacherSubject);
        teacherEmail = (EditText)findViewById(R.id.teacherEmail);
        teacherPhone= (EditText)findViewById(R.id.teacherPhone);

        teacherMale = (RadioButton)findViewById(R.id.teacherMale);
        teacherFemale = (RadioButton)findViewById(R.id.teacherFemale);

        buttonSubmit = (Button)findViewById(R.id.teacherSubmit);

        teachers=new Teachers();
        requestQueue = Volley.newRequestQueue(this);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_registration);
        init();
        randomString(8);
    }

    String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    void insertIntoCloud()
    {
        StringRequest request = new StringRequest(Request.Method.POST, Util.INSERT_TEACHER_PHP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(TeacherRegistration.this, "Response: " + response, Toast.LENGTH_LONG).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TeacherRegistration.this, "Some Error" + error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("teacherName", teachers.getTeacherName());
                map.put("teacherAddress", teachers.getTeacherAddress());
                map.put("teacherSubject", teachers.getTeacherSubject());
                map.put("teacherEmail", teachers.getTeacherEmail());
                map.put("teacherPhone",teachers.getTeacherPhone());
                map.put("gender",teachers.getGender());
                map.put("password",teachers.getPassword());

                return map;


            }


        };
        requestQueue.add(request);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id=buttonView.getId();
        if(isChecked) {


            if (id == R.id.teacherFemale) {teachers.setGender("Female");

            }
            else{
                teachers.setGender("Male");
            }
        }
    }

    public  void  clickMe(View view)
    {
        int id = view.getId();
        if (id ==R.id.teacherSubmit)
        {
            teachers.setTeacherName(teacherName.getText().toString().trim());
            teachers.setTeacherAddress(teacherAddress.getText().toString().trim());
            teachers.setTeacherSubject(teacherSubject.getText().toString().trim());
            teachers.setTeacherEmail(teacherEmail.getText().toString().trim());
            teachers.setTeacherPhone(teacherPhone.getText().toString().trim());
            teachers.setGender(teacherFemale.getText().toString().trim());
            teachers.setGender(teacherMale.getText().toString().trim());
            teachers.setPassword(randomString(8));


            email=teacherEmail.getText().toString().trim();
            //subject=student.getPassword();
            //comment="welcome to our College";
            subject = "Dear " +teachers.getTeacherName() +"Secret of Education lies in respecting the Student";
            comment = "Your Username:"+teachers.getTeacherEmail()+"\n"+
                    "Password:"+teachers.getPassword();





            insertIntoCloud();
            sendEmail();
        }

    }

    public void sendEmail(){
        StringRequest request=new StringRequest(Request.Method.POST, Util.EMAIL_TEACHER_PHP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(TeacherRegistration.this, "Response: " + response, Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TeacherRegistration.this, "Some Error" + error.getMessage(), Toast.LENGTH_LONG).show();

            }

        })


        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("email", email);
                map.put("subject", subject);
                map.put("comment", comment);



                return map;
            }
        };
        requestQueue.add(request);
    }

}
