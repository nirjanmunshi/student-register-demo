package com.example.hp.studentregister;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.hp.studentregister.databinding.ActivityAddNewStudentBinding;

public class AddNewStudentActivity extends AppCompatActivity {

    private ActivityAddNewStudentBinding binding;
    private Student student;
    private AddStudentActivityClickHandler clickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddNewStudentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        clickHandler = new AddStudentActivityClickHandler(this);
        binding.setClickHandler(clickHandler);

        student = new Student();
        binding.setStudent(student);
    }

    public class AddStudentActivityClickHandler {
        private Context context;

        AddStudentActivityClickHandler(Context context) {
            this.context = context;
        }

        public void onSubmitButtonClick(View v) {
            if (student.getStudentName() == null) {
                Toast.makeText(context, "enter name", Toast.LENGTH_SHORT).show();
                return;
            }
            if (student.getStudentEmail() == null) {
                Toast.makeText(context, "enter email", Toast.LENGTH_SHORT).show();
                return;
            }
            if (student.getCountry() == null) {
                Toast.makeText(context, "enter country", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent();
            intent.putExtra("NAME", student.getStudentName());
            intent.putExtra("EMAIL", student.getStudentEmail());
            intent.putExtra("COUNTRY", student.getCountry());
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
