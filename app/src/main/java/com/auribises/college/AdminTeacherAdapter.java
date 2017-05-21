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

public class AdminTeacherAdapter extends ArrayAdapter<Teachers> {

    Context context;
    int resource;
    ArrayList<Teachers> teachersList,tempList;
    Teachers teachers;

    public AdminTeacherAdapter(Context context, int resource, ArrayList<Teachers> objects) {

        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        teachersList=objects;
        tempList = new ArrayList<>();
        tempList.addAll(teachersList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=null;

        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(resource, parent, false);

        TextView txtName=(TextView)view.findViewById(R.id.textViewName);
        TextView txtEmail=(TextView)view.findViewById(R.id.textViewEmail);

        Teachers teachers=teachersList.get(position);
        txtName.setText(teachers.getTeacherName());
        txtEmail.setText(teachers.getTeacherEmail());

        return view;
    }
    public void filter(String str){

        teachersList.clear();

        if(str.length()==0){
            teachersList.addAll(tempList);
        }else{
            for(Teachers s : tempList){
                if(s.getTeacherName().toLowerCase().contains(str.toLowerCase())){
                    teachersList.add(s);
                }
            }
        }

        notifyDataSetChanged();
    }

}

