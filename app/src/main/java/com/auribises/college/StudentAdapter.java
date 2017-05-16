package com.auribises.college;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;



public class StudentAdapter  extends ArrayAdapter<StudentBca1>{
    Context context;
    int resource;
    ArrayList<StudentBca1> studentList;


    public StudentAdapter( Context context,  int resource,  ArrayList<StudentBca1> objects) {
        super(context, resource, objects);

        this.context=context;
        this.resource=resource;
        studentList=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=null;

        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(resource,parent,false);

        TextView txtName=(TextView)view.findViewById(R.id.textViewName);
        TextView txtClass=(TextView)view.findViewById(R.id.textViewClass);


        StudentBca1 studentBca1=studentList.get(position);
        txtName.setText(studentBca1.getStuName());
        txtClass.setText(studentBca1.getStudentClass());


        return  view;
    }
}
