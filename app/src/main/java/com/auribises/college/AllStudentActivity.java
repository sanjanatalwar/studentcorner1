package com.auribises.college;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class AllStudentActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView listView;
    EditText editTextSearch;
    ArrayList<StudentBca1> studentList;
    StudentAdapter adapter;
    StudentBca1 studentBca1;
    Teachers teachers;
private SharedPreferences preferences;
    int pos;
    RequestQueue requestQueue;

 void init(){
     preferences=getSharedPreferences(Util.PREFS_NAME,MODE_PRIVATE);
     teachers=new Teachers();
     listView=(ListView)findViewById(R.id.listview);
     editTextSearch=(EditText)findViewById(R.id.search);
     requestQueue= Volley.newRequestQueue(this);
     studentBca1=new StudentBca1();

 }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_student);
        init();
        retrieveFromCloud();

    }

    void retrieveFromCloud(){


        studentList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Util.RETERIEVE_STUDENT_PHP, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                Log.i("test",response.toString());

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("students");

                    int id = 0;
                    String n = "", p = "", e = "",  a = "", g=" ", c=" ", m=" ";
                    int d=0 ,y=0,mm=0,cm=0,pm=0;
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
                        cm=jObj.getInt("cMArks");
                        pm=jObj.getInt("punjabiMarks");

                        studentList.add(new StudentBca1(id, n,p,e,a,g,c,"",mm,cm,pm,d,0));
                        Log.i("test",studentList.toString());
                    }

                    adapter = new StudentAdapter(AllStudentActivity.this, R.layout.list_item, studentList);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(AllStudentActivity.this);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("test",e.toString());
                    Toast.makeText(AllStudentActivity.this, "One Some Exception"+e.toString(), Toast.LENGTH_LONG).show();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AllStudentActivity.this,"Two Some Error",Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest); // Execute the Request
    }

    void  setDialog(String str){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(str);
        builder.setMessage("You are not Authorized for"+str);
        builder.setPositiveButton("OK",null);
        builder.setCancelable(false);
        builder.show();
    }

    void showOptions(){



        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String[] items ={"Marks of c","Marks of Math","Marks of punjabi"};




        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                switch (i){
                    case 0:
                        //StudentBca1 studentBca2=studentList.get(pos);
//                        if(preferences.getString(Util.KEY_NAME)
//                        if (preferences.getString(Util.KEY_SUBJECT,"").equals())
                        if (preferences.getString(Util.KEY_SUBJECT,"").equals(""))
                         {

                            Intent intent1 = new Intent(AllStudentActivity.this, TeacherMarksUpdate.class);

                            intent1.putExtra("bean1", studentBca1);
                            startActivity(intent1);

                        }
else {
                    setDialog("C");
                        }


                         break;

                    case 1:
                        StudentBca1 studentBca1=studentList.get(pos);
                        if (preferences.getString(Util.KEY_SUBJECT,"").equals("Math")) {


                            Intent intent = new Intent(AllStudentActivity.this, TeacherMarksUpdate.class);
                            //intent.putExtra("name",studentBca1.getStuName());
//                        intent.putExtra("mathMarks",String.valueOf(studentBca1.getMathMarks()));
//
                            intent.putExtra("bean", studentBca1);
                            Log.i("markss", studentBca1.getMathMarks() + "");

                            startActivity(intent);
                        }

                        else {
                            setDialog("Math");
                        }
                        break;

                    case 2:
                        // deleteStudent();
                        if (preferences.getString(Util.KEY_SUBJECT,"").equals("Punjabi")) {

                            StudentBca1 studentBca2 = studentList.get(pos);
                            Intent intent2 = new Intent(AllStudentActivity.this, TeacherMarksUpdate.class);
                            intent2.putExtra("bean2", studentBca2);
                            startActivity(intent2);
                        }
                        else {
                            setDialog("Punjabi");
                        }
                        break;
                }



            }
        });


        builder.create().show();
    }


    void showStudent(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Details of "+studentBca1.getStuName());
        builder.setMessage(studentBca1.toString());
        builder.setPositiveButton("Done",null);
        builder.create().show();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        pos=position;
        studentBca1=studentList.get(position);
        showOptions();
    }
}

