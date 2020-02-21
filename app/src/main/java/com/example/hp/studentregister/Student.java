package com.example.hp.studentregister;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

@Entity(tableName = "student_table")
public class Student extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int studentId;

    @ColumnInfo(name = "name")
    private String studentName;

    @ColumnInfo(name = "email")
    private String studentEmail;

    @ColumnInfo(name = "country")
    private String country;

    @ColumnInfo(name = "registeredTime")
    private String registeredTime;

    @Ignore
    public Student() {
    }

    public Student(int studentId, String studentName, String studentEmail, String country, String registeredTime) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.country = country;
        this.registeredTime = registeredTime;
    }

    @Bindable
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
        notifyPropertyChanged(BR.studentId);
    }

    @Bindable
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
        notifyPropertyChanged(BR.studentName);
    }

    @Bindable
    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
        notifyPropertyChanged(BR.studentEmail);
    }

    @Bindable
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
        notifyPropertyChanged(BR.country);
    }

    @Bindable
    public String getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(String registeredTime) {
        this.registeredTime = registeredTime;
        notifyPropertyChanged(BR.registeredTime);
    }
}
