package com.example.hp.studentregister;

import android.annotation.SuppressLint;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.hp.studentregister.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_STUDENT_ACTIVITY_REQUEST_CODE = 1;

    // this section will be called on for the first time when the app is created
    RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            addNewStudent(new Student(0, "Nirjan Munshi", "nirjan@careindia.org", "India", "17-02-2020"));
            addNewStudent(new Student(0, "Akash Munshi", "akash@careindia.org", "India", "17-02-2020"));
            addNewStudent(new Student(0, "Goutam Munshi", "gautam@careindia.org", "India", "17-02-2020"));
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    private StudentAppDatabase database;
    private List<Student> studentList;
    private StudentDataAdapter adapter;
    private RecyclerView recyclerView;

    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandler clickHandler;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View v = activityMainBinding.getRoot();
        setContentView(v);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        clickHandler = new MainActivityClickHandler(this);
        activityMainBinding.setClickHandler(clickHandler);


        recyclerView = activityMainBinding.layoutContentMain.rvStudents;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        database = Room.databaseBuilder(getApplicationContext(), StudentAppDatabase.class, "StudentDB").addCallback(callback).build();

        loadData();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                Student studentToDelete = studentList.get(viewHolder.getAdapterPosition());
                deleteStudent(studentToDelete);
            }
        }).attachToRecyclerView(recyclerView);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_STUDENT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                String name = data.getStringExtra("NAME");
                String email = data.getStringExtra("EMAIL");
                String country = data.getStringExtra("COUNTRY");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault());
                String currentDate = simpleDateFormat.format(new Date());
                Student student = new Student(0, name, email, country, currentDate);
                addNewStudent(student);
            } else {
                Toast.makeText(this, "Null value of intent data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void loadData() {
        new GetStudentsAsyncTask().execute();
    }

    private void deleteStudent(Student student) {
        new DeleteStudentAsyncTask().execute(student);
    }

    private void addNewStudent(Student student) {
        new AddStudentAsyncTask().execute(student);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("StaticFieldLeak")
    class GetStudentsAsyncTask extends AsyncTask<Void, Void, List<Student>> {

        @Override
        protected List<Student> doInBackground(Void... voids) {
            studentList = database.getStudentDao().getStudentDetails();
            return studentList;
        }

        @Override
        protected void onPostExecute(List<Student> students) {
            super.onPostExecute(students);
            adapter = new StudentDataAdapter(students);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @SuppressLint("StaticFieldLeak")
    class AddStudentAsyncTask extends AsyncTask<Student, Void, Void> {

        @Override
        protected Void doInBackground(Student... students) {
            database.getStudentDao().insert(students[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadData();
        }
    }

    @SuppressLint("StaticFieldLeak")
    class DeleteStudentAsyncTask extends AsyncTask<Student, Void, Void> {

        @Override
        protected Void doInBackground(Student... students) {
            database.getStudentDao().delete(students[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    public class MainActivityClickHandler {
        private Context context;

        public MainActivityClickHandler(Context context) {
            this.context = context;
        }

        public void onFabClick(View v) {
            Intent intent = new Intent(MainActivity.this, AddNewStudentActivity.class);
            startActivityForResult(intent, NEW_STUDENT_ACTIVITY_REQUEST_CODE);
        }


    }
}
