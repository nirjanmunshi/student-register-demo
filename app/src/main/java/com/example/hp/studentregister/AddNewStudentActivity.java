package com.example.hp.studentregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewStudentActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText countryEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_student);

        nameEditText=findViewById(R.id.et_name);
        emailEditText=findViewById(R.id.et_email);
        countryEditText=findViewById(R.id.et_country);
        Button submitButton = findViewById(R.id.btnSubmit);

        submitButton.setOnClickListener(v -> {

            if(TextUtils.isEmpty(nameEditText.getText())){

                Toast.makeText(getApplicationContext(),"Name field cannot be empty",Toast.LENGTH_LONG).show();
            }else{

                String name=nameEditText.getText().toString();
                String email=emailEditText.getText().toString();
                String country=countryEditText.getText().toString();

                Intent intent=new Intent();
                intent.putExtra("NAME",name);
                intent.putExtra("EMAIL",email);
                intent.putExtra("COUNTRY",country);
                setResult(RESULT_OK,intent);
                finish();

            }

        });


    }
}
