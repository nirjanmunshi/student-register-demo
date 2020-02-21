package com.example.hp.studentregister;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.studentregister.databinding.StudentListItemBinding;

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

        // both line is working fine
        StudentListItemBinding itemBinding = StudentListItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()),viewGroup,false);
//        StudentListItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),R.layout.student_list_item,viewGroup,false);
        return new StudentViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder viewHolder, int i) {
        Student student = students.get(i);
        if (student!= null){
            viewHolder.studentListItemBinding.setStudent(student);

            viewHolder.studentListItemBinding.tvCountry.setOnClickListener(v -> {
                Toast.makeText(v.getContext(), student.getCountry(), Toast.LENGTH_SHORT).show();
            });
        }
    }

    @Override
    public int getItemCount() {
        return students.size();

    }

    class StudentViewHolder extends RecyclerView.ViewHolder {

        StudentListItemBinding studentListItemBinding;

        StudentViewHolder(@NonNull StudentListItemBinding studentListItemBinding) {
            super(studentListItemBinding.getRoot());
            this.studentListItemBinding = studentListItemBinding;
        }
    }
}
