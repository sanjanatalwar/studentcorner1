package com.auribises.college;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminOptions extends AppCompatActivity {
Button bca1,bca2,bca3,teacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_options);
        bca1=(Button)findViewById(R.id.adminbca1list);
        bca1=(Button)findViewById(R.id.adminbca2list);
        bca1=(Button)findViewById(R.id.adminbca3list);
        teacher=(Button)findViewById(R.id.adminTeacherlist);
    }
    public  void bca1Click(View v){
        int id=v.getId();
        if(id==R.id.adminbca1list){
            Intent i=new Intent(AdminOptions.this,AdminBca1List.class);
            startActivity(i);
        }
    }
    public  void bca2Click(View v){
        int id=v.getId();
        if(id==R.id.adminbca2list){
            Intent i=new Intent(AdminOptions.this,AdminBca2List.class);
            startActivity(i);
        }
    }
    public  void bcaThreeclick(View v){
        int id=v.getId();
        if(id==R.id.adminbca3list){
            Intent i=new Intent(AdminOptions.this,AdminBca3List.class);
            startActivity(i);
        }
    }
    public  void teacherclick(View v){
        int id=v.getId();
        if(id==R.id.adminTeacherlist){
            Intent i=new Intent(AdminOptions.this,AdminTeacher.class);

            startActivity(i);
        }
    }
}
