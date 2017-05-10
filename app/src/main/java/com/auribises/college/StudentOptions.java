package com.auribises.college;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentOptions extends AppCompatActivity {

    Button btnbca1,btnbc2,btnbca3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_options);
        btnbca1=(Button)findViewById(R.id.bca1result);
        btnbc2=(Button)findViewById(R.id.bca2result);
        btnbca3=(Button)findViewById(R.id.bca3result);
    }

    public  void bca1Click(View v){
        int id=v.getId();
        if(id==R.id.bca1result){
            Intent i=new Intent(StudentOptions.this,AllBcaOneResult.class);
            startActivity(i);
        }
    }
    public  void bca2Click(View v){
        int id=v.getId();
        if(id==R.id.bca2result){
            Intent i=new Intent(StudentOptions.this,AllBcaTwoResult.class);
            startActivity(i);
        }
    }
    public  void bca3Click(View v){
        int id=v.getId();
        if(id==R.id.bca3result){
            Intent i=new Intent(StudentOptions.this,AllBcaThreeResult.class);
            startActivity(i);
        }
    }
}
