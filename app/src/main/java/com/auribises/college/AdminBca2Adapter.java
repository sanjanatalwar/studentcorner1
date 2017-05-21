package com.auribises.college;

/**
 * Created by Admin on 16-05-2017.
 */
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdminBca2Adapter extends ArrayAdapter<StudentBca2> {

    Context context;
    int resource;
    ArrayList<StudentBca2> studentList,tempList;
    StudentBca2 studentBca2;

    public AdminBca2Adapter(Context context, int resource, ArrayList<StudentBca2> objects) {

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

        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(resource, parent, false);

        TextView txtName=(TextView)view.findViewById(R.id.textViewName);
        TextView txtEmail=(TextView)view.findViewById(R.id.textViewEmail);

        StudentBca2 studentBca2=studentList.get(position);
        txtName.setText(studentBca2.getStuName());
        txtEmail.setText(studentBca2.getStuEmail());

        return view;
    }
    public void filter(String str){

        studentList.clear();

        if(str.length()==0){
            studentList.addAll(tempList);
        }else{
            for(StudentBca2 s : tempList){
                if(s.getStuName().toLowerCase().contains(str.toLowerCase())){
                    studentList.add(s);
                }
            }
        }

        notifyDataSetChanged();
    }
}


