package com.auribises.college;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Student_Registration extends AppCompatActivity {
    Spinner spinerStudentClass, studentBirthdate, studentBirthMonth, studentBirthYear;

    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;
    ArrayAdapter<String> adapter3;


    void initSpiner() {
        spinerStudentClass = (Spinner) findViewById(R.id.spinnerClass);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);


        adapter.add("----select class---");
        adapter.add("BCA");
        adapter.add("BBA");
        adapter.add("B.com");
        spinerStudentClass.setAdapter(adapter);



        studentBirthdate=(Spinner)findViewById(R.id.spinerDate);
        adapter1=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
adapter1.add("date");

        adapter1.add("1");
        adapter1.add("2");
        adapter1.add("3");
        adapter1.add("4");
        studentBirthdate.setAdapter(adapter1);

studentBirthMonth=(Spinner)findViewById(R.id.spinnerMounth);
        adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item);
        adapter2.add("Month");

        adapter2.add("january");
        adapter2.add("Feburary");
        adapter2.add("March");
        studentBirthMonth.setAdapter(adapter2);


studentBirthYear=(Spinner)findViewById(R.id.spinerYear);
        adapter3=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item);
        adapter3.add("year");

        adapter3.add("1995");
        adapter3.add("1996");
        adapter3.add("1997");
        adapter3.add("1998");
        adapter3.add("1999");
        studentBirthYear.setAdapter(adapter3);


    }





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__registration);
        initSpiner();


    }
}
