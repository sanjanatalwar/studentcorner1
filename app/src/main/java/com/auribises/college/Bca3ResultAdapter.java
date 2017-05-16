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

/**
 * Created by Admin on 10-05-2017.
 */

public class Bca3ResultAdapter extends ArrayAdapter<StudentBca3> {

    Context context;
    int resource;
    TeacherMarksUpdate teacherMarksUpdate;
    ArrayList<StudentBca3> studentList;
    public Bca3ResultAdapter(Context context, int resource, ArrayList<StudentBca3> objects) {
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

        TextView txtGraphicsMarks=(TextView)view.findViewById(R.id.textViewgraphicsMarks);
        TextView txtOperatingSystemMarks=(TextView)view.findViewById(R.id.textViewoperatingsystemMarks);
        TextView txtMathMarks=(TextView)view.findViewById(R.id.textViewmathMarks);
        teacherMarksUpdate=new TeacherMarksUpdate();
        StudentBca3 studentBca3=studentList.get(position);
        txtName.setText(studentBca3.getStuName());

        txtGraphicsMarks.setText("Marks in Graphics:= "+String.valueOf(studentBca3.getGraphicsMarks()));

        txtOperatingSystemMarks.setText("Marks in Operating System:= "+String.valueOf(studentBca3.getOperatingSystemMarks()));
        txtMathMarks.setText("Marks In Math:= "+String.valueOf(studentBca3.getMathMarks()));

        return  view;
    }
    }
