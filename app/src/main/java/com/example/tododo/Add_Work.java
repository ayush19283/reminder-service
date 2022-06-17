package com.example.tododo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Work extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work);

        Database db = new Database(this);

        Button save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                EditText title = findViewById(R.id.task_title);
                String task_title = title.getText().toString();


                if (task_title.matches("")) {
                    Toast.makeText(Add_Work.this, "Task title can not be empty", Toast.LENGTH_LONG).show();

                } else {
                    db.add_task(task_title);

                    Intent intent = new Intent(Add_Work.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });
    }
}