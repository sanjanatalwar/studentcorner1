package com.auribises.college;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeacherOption extends AppCompatActivity {
    Button bca1List,bca2list,bca3list,bca1result,bca2result,bca3result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_option);
        bca1List=(Button)findViewById(R.id.bca1list);
        bca2list=(Button)findViewById(R.id.bca2list);
        bca3list=(Button)findViewById(R.id.bca3list);
        bca1result=(Button)findViewById(R.id.bca1result);
        bca2result=(Button)findViewById(R.id.bca2result);

        bca3result=(Button)findViewById(R.id.bca3result);

    }

    public void onClick(View v){
        int id=v.getId();
        if(id==R.id.bca1list){
            Intent i=new Intent(TeacherOption.this,AllStudentActivity.class);
            startActivity(i);
        }

    }

    public void bca2Click(View v){
        int id=v.getId();
        if(id==R.id.bca2list){
            Intent i=new Intent(TeacherOption.this,AllBcaTwoStudents.class);
            startActivity(i);
        }

    }



    public void onclick(View v){
        int id=v.getId();
        if(id==R.id.bca3list){
            Intent i=new Intent(TeacherOption.this,AllBcaThreeStudents.class);
            startActivity(i);
        }
    }

    public void result1(View v){
        int id=v.getId();
        if(id==R.id.bca1result){
            Intent i=new Intent(TeacherOption.this,AllBcaOneResult.class);
            startActivity(i);
        }
    }
    public void result2(View v){
        int id=v.getId();
        if(id==R.id.bca2result){
            Intent i=new Intent(TeacherOption.this,AllBcaTwoResult.class);
            startActivity(i);
        }
    }
    public void result3(View v){
        int id=v.getId();
        if(id==R.id.bca3result){
            Intent i=new Intent(TeacherOption.this,AllBcaThreeResult.class);
            startActivity(i);
        }
    }

    }


