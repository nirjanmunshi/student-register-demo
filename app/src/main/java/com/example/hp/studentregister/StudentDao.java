package com.example.hp.studentregister;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface StudentDao {

    @Insert
    long insert(Student student);

    @Update
    void update(Student student);

    @Delete
    void delete(Student student);

    @Query("select * from student_table order by id asc")
    List<Student> getStudentDetails();

    @Query("select * from student_table where id = :studentId")
    Student getStudentDetails(int studentId);
}
