package com.example.room_sqlite_practice_jay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.room_sqlite_practice_jay.adapter.UserAdapter;
import com.example.room_sqlite_practice_jay.database.UserDatabase;
import com.example.room_sqlite_practice_jay.model.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtSalary, edtCompany;
    Button btnAdd, btnGet;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);
        edtCompany = findViewById(R.id.edtCompany);
        edtSalary = findViewById(R.id.edtSalary);
        btnAdd = findViewById(R.id.btnAdd);
        btnGet = findViewById(R.id.btnGet);
        recyclerView = findViewById(R.id.recyclerView);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edtName.getText().toString();
                String salary = edtSalary.getText().toString();
                String company = edtCompany.getText().toString();

                User user = new User(name, salary, company);
                Thread thread = new Thread() {

                    @Override
                    public void run() {
                        super.run();

                        UserDatabase.getInstance(MainActivity.this).userDao().insert(user);
                        edtCompany.setText("");
                        edtName.setText("");
                        edtSalary.setText("");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "Successfully Created", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                };
                thread.start();
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             Thread thread=new Thread(){
                 @Override
                 public void run() {
                     super.run();

                    List<User> userList= UserDatabase.getInstance(MainActivity.this).userDao().getAllUser();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            UserAdapter userAdapter=new UserAdapter(userList, MainActivity.this);
                            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            recyclerView.setAdapter(userAdapter);
                        }
                    });
                 }
             };
             thread.start();

            }
        });
    }
}