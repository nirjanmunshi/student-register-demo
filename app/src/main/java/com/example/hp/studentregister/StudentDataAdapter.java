package com.example.hp.studentregister;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class StudentDataAdapter extends RecyclerView.Adapter<StudentDataAdapter.StudentViewHolder> {

    private List<Student> students;

    public StudentDataAdapter(List<Student> students) {
        this.students = students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.student_list_item, viewGroup, false);

        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder viewHolder, int i) {
        Student student = students.get(i);
        if (student!= null){
            viewHolder.name.setText(student.getStudentName());
            viewHolder.email.setText(student.getStudentEmail());
            viewHolder.country.setText(student.getCountry());
            viewHolder.date.setText(student.getRegisteredTime());
        }
    }

    @Override
    public int getItemCount() {
        return students.size();

    }

    class StudentViewHolder extends RecyclerView.ViewHolder {

        private TextView name, email, country, date;

        StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            email = itemView.findViewById(R.id.tvEmail);
            country = itemView.findViewById(R.id.tvCountry);
            date = itemView.findViewById(R.id.tvTime);
        }
    }
}
