package com.example.myapplicationforjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RoomActivity extends AppCompatActivity {
    private EditText mTodoEditText;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        mTodoEditText = findViewById(R.id.todo_edit);
        mResultTextView = findViewById(R.id.result_text);

        // allow~ 는 원래는 back에서만 동작하는데, 이거를 공부용으로 프론트에서 보여주기 위한 방법
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "todo-db").allowMainThreadQueries().build();

        // DB불러와서 화면에 다시 보여주기
        mResultTextView.setText(db.todoDao().getAll().toString());

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.todoDao().insert(new Todo(mTodoEditText.getText().toString()));
                mResultTextView.setText(db.todoDao().getAll().toString());
            }
        });
    }
}