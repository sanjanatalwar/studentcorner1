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
 * Created by Admin on 09-05-2017.
 */

public class Bca1ResultAdapter extends ArrayAdapter<StudentBca1> {

    Context context;
    int resource;
    TeacherMarksUpdate teacherMarksUpdate;

    ArrayList<StudentBca1> studentList,tempList;
    public Bca1ResultAdapter( Context context,  int resource,  ArrayList<StudentBca1> objects) {
        super(context, resource, objects);

        this.context=context;
        this.resource=resource;
        studentList=objects;
        tempList = new ArrayList<>();
        tempList.addAll(studentList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=null;


        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(resource,parent,false);

        TextView txtName=(TextView)view.findViewById(R.id.textViewName);
        TextView txtMathMarks=(TextView)view.findViewById(R.id.textViewMathMarks);
        TextView txtCMarks=(TextView)view.findViewById(R.id.textViewCMarks);
        TextView txtPunjabiMarks=(TextView)view.findViewById(R.id.textViewPunjabiMarks);
        teacherMarksUpdate=new TeacherMarksUpdate();
        StudentBca1 studentBca1=studentList.get(position);
        txtName.setText(studentBca1.getStuName());

        txtMathMarks.setText("Marks in Math:= "+String.valueOf(studentBca1.getMathMarks()));

        txtCMarks.setText("Marks in C:= "+String.valueOf(studentBca1.getcMArks()));
        txtPunjabiMarks.setText("Marks  in Punjabi:= "+String.valueOf(studentBca1.getPunjabiMarks()));

        return  view;
    }
    public void filter(String str){

        studentList.clear();

        if(str.length()==0){
            studentList.addAll(tempList);
        }else{
            for(StudentBca1 s : tempList){
                if(s.getStuName().toLowerCase().contains(str.toLowerCase())){
                    studentList.add(s);
                }
            }
        }

        notifyDataSetChanged();
    }
}
