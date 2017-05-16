package com.auribises.college;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 10-05-2017.
 */

public class Bca2ResultAdapter extends ArrayAdapter<StudentBca2> {


    Context context;
    int resource;
    TeacherMarksUpdate teacherMarksUpdate;

    ArrayList<StudentBca2> studentList;
    public Bca2ResultAdapter(Context context, int resource,  ArrayList<StudentBca2> objects) {
        super(context, resource, objects);


        this.context=context;
        this.resource=resource;
        studentList=objects;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View view=null;

        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(resource,parent,false);

        TextView txtName=(TextView)view.findViewById(R.id.textViewName);
        TextView txtMathMarks=(TextView)view.findViewById(R.id.textViewmathMarks);
        TextView txtUnixMarks=(TextView)view.findViewById(R.id.textViewunixMarks);
        TextView txtDataStructureMarks=(TextView)view.findViewById(R.id.textViewdatastructureMarks);

        teacherMarksUpdate=new TeacherMarksUpdate();
        StudentBca2 studentBca2=studentList.get(position);
        txtName.setText(studentBca2.getStuName());


        txtMathMarks.setText("Marks in Math:= "+String.valueOf(studentBca2.getMathMarks()));
        Log.i("math",String.valueOf(studentBca2.getMathMarks()));

        txtUnixMarks.setText("Marks in Unix:= "+String.valueOf(studentBca2.getUnixMarks()));
        txtDataStructureMarks.setText("Marks in DataStructure:= "+String.valueOf(studentBca2.getDataStructureMarks()));

        return  view;
    }
}
