package com.auribises.college;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Student_Registration extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    EditText eStudentName,eStudentAdress,eStudentPhone,eStudentEmail;
    RadioButton studentMale,studentFemale;
    Spinner spinnerStudentClass, studentBirthdate, studentBirthMonth, studentBirthYear;

    StudentBca1 studentBca1;
    StudentBca2 studentBcatwo;
    StudentBca3 studentBca3;

    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;
    ArrayAdapter<String> adapter3;
    RequestQueue requestQueue;
    Button btnstudentRegistration;
    TeacherMarksUpdate teacher;

    void initSpinner() {

        teacher=new TeacherMarksUpdate();
        btnstudentRegistration=(Button)findViewById(R.id.studentregistration);
        requestQueue= Volley.newRequestQueue(this);
        eStudentName=(EditText)findViewById(R.id.studentname);
        eStudentAdress=(EditText)findViewById(R.id.studentadress);
        eStudentEmail=(EditText)findViewById(R.id.studentemail);
        eStudentPhone=(EditText)findViewById(R.id.studentphone);
        studentMale=(RadioButton)findViewById(R.id.studentmalele);
        studentFemale=(RadioButton)findViewById(R.id.studentfemale);


        studentBca1=new StudentBca1();
        studentBcatwo = new StudentBca2();
        studentBca3 = new StudentBca3();

        studentFemale.setOnCheckedChangeListener(this);
        studentFemale.setOnCheckedChangeListener(this);


        spinnerStudentClass = (Spinner) findViewById(R.id.spinnerClass);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);


        adapter.add("----Select Degree---");
        adapter.add("BCA1");
        adapter.add("BCA2");
        adapter.add("BCA3");
        spinnerStudentClass.setAdapter(adapter);


    spinnerStudentClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position!=0){
            studentBca1.setStudentClass(adapter.getItem(position));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});

       studentBirthdate=(Spinner)findViewById(R.id.spinerDate);
        adapter1=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
        adapter1.add("date");

        adapter1.add("1");
        adapter1.add("2");
        adapter1.add("3");
        adapter1.add("4");
        adapter1.add("5");
        adapter1.add("6");
        adapter1.add("7");
        adapter1.add("8");
        adapter1.add("9");
        adapter1.add("10");
        adapter1.add("11");
        adapter1.add("12");
        adapter1.add("13");
        adapter1.add("14");
        adapter1.add("15");
        adapter1.add("16");
        adapter1.add("17");
        adapter1.add("18");
        adapter1.add("19");
        adapter1.add("20");
        adapter1.add("21");
        adapter1.add("22");
        adapter1.add("23");
        adapter1.add("24");
        adapter1.add("25");
        adapter1.add("26");
        adapter1.add("27");
        adapter1.add("28");
        adapter1.add("29");
        adapter1.add("30");
        adapter1.add("31");
        studentBirthdate.setAdapter(adapter1);

        studentBirthdate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    studentBca1.setStudentBirthDate(Integer.parseInt((adapter1.getItem(position))));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

studentBirthMonth=(Spinner)findViewById(R.id.spinnerMounth);
        adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item);
        adapter2.add("Month");

        adapter2.add("January");
        adapter2.add("February");
        adapter2.add("March");
        adapter2.add("April");
        adapter2.add("May");
        adapter2.add("June");
        adapter2.add("July");
        adapter2.add("August");
        adapter2.add("September");
        adapter2.add("October");
        adapter2.add("November");
        adapter2.add("December");
        studentBirthMonth.setAdapter(adapter2);
        studentBirthMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position!=0){
            studentBca1.setStudentBirthMonth(adapter2.getItem(position));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});

        studentBirthYear=(Spinner)findViewById(R.id.spinerYear);
        adapter3=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item);
        adapter3.add("year");

        adapter3.add("1995");
        adapter3.add("1996");
        adapter3.add("1997");
        adapter3.add("1998");
        adapter3.add("1999");
        studentBirthYear.setAdapter(adapter3);


        studentBirthYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    studentBca1.setStudentBirthYear(Integer.parseInt(adapter3.getItem(position)));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__registration);
        initSpinner();



    }


    public void insertIntoCloud() {

        String url = "";
        if(spinnerStudentClass.getSelectedItem().equals("BCA1"))
        {

            url = Util.INSERT_STUDENT_PHP;
        }
        else if(spinnerStudentClass.getSelectedItem().equals("BCA2")){
            url=Util.INSERT_BCA_TWO_STUDENT_PHP;
        }
        else{
            url=Util.INSERT_BCA_THREE_STUDENT_PHP;
        }


            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(Student_Registration.this, "Response: " + response, Toast.LENGTH_LONG).show();


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Student_Registration.this, "Some Error" + error.getMessage(), Toast.LENGTH_LONG).show();

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("stuName", studentBca1.getStuName());
                    map.put("stuPhone", studentBca1.getStuPhone());
                    map.put("stuEmail", studentBca1.getStuEmail());
                    map.put("stuAddress", studentBca1.getStuAddress());
                    map.put("gender", studentBca1.getGender());
                    map.put("studentClass", studentBca1.getStudentClass());
                    map.put("studentBirthMonth", studentBca1.getStudentBirthMonth());
                    map.put("studentBirthDate", String.valueOf(studentBca1.getStudentBirthDate()));
                    map.put("StudentBirthYear", String.valueOf(studentBca1.getStudentBirthYear()));
                    map.put("mathMarks", new Integer(studentBca1.getMathMarks()).toString());
                    map.put("cMArks", String.valueOf(studentBca1.getcMArks()));
                    map.put("punjabiMarks", String.valueOf(studentBca1.getPunjabiMarks()));


                    return map;


                }


            };
            requestQueue.add(request);
        }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id=buttonView.getId();
        if(isChecked) {


            if (id == R.id.studentfemale) {studentBca1.setGender("Female");

            }
            else{
                    studentBca1.setGender("Male");
                }
            }

        }
        public  void studentRegisterClick(View v){
            int id=v.getId();
            if(id==R.id.studentregistration){
                studentBca1.setStuName(eStudentName.getText().toString().trim());
                studentBca1.setStuPhone(eStudentPhone.getText().toString().trim());
                studentBca1.setStuEmail(eStudentEmail.getText().toString().trim());
                studentBca1.setStuAddress(eStudentAdress.getText().toString().trim());
                studentBca1.setGender(studentMale.getText().toString().trim());
                studentBca1.setGender(studentFemale.getText().toString().trim());

                insertIntoCloud();

            }

        }
    }

