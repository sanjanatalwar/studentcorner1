package com.auribises.college;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 06-05-2017.
 */

public class Bca3Adapter  extends ArrayAdapter<StudentBca3>

{Context context;
    int resource;
    ArrayList<StudentBca3> studentList;
    StudentBca3 studentBca3;


    public Bca3Adapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<StudentBca3> objects) {


        super(context, resource, objects);



        this.context=context;
        this.resource=resource;
        studentList=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(resource, parent, false);

        TextView txtName=(TextView)view.findViewById(R.id.textViewName);
        TextView txtClass=(TextView)view.findViewById(R.id.textViewClass);

        StudentBca3 studentBca3=studentList.get(position);
        txtName.setText(studentBca3.getStuName());
        txtClass.setText(studentBca3.getStudentClass());

        return  view;
    }
}
