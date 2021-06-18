package com.example.todo_list.ui.ui.details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.todo_list.R;
import com.example.todo_list.domain.ToDo;
import com.example.todo_list.ui.ui.list.ToDoListFragment;

public class ToDoDetailsActivity extends AppCompatActivity{

    public static final String ARG_TODO = "ARG_TODO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_details);

        ToDo toDo = getIntent().getParcelableExtra(ARG_TODO);

        if (savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerDetails, ToDoDetailsFragment.newInstance(toDo))
                    .commit();
        }
    }
}