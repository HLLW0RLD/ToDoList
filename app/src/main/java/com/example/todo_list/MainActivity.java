package com.example.todo_list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.todo_list.domain.ToDo;
import com.example.todo_list.ui.ui.details.ToDoDetailsFragment;
import com.example.todo_list.ui.ui.list.ToDoListFragment;

import static com.example.todo_list.ui.ui.details.ToDoDetailsFragment.ARG_TODO;

public class MainActivity extends AppCompatActivity implements ToDoListFragment.onToDoClicked  {

    public static final String ARG_TODO_LIST = "ARG_TODO_LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToDo toDo1 = getIntent().getParcelableExtra(ARG_TODO);
        ToDo toDo = getIntent().getParcelableExtra(ARG_TODO_LIST);

        if (savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerList, ToDoListFragment.newInstance(toDo))
                    .commit();
        }
    }

    @Override
    public void onToDoClicked(ToDo toDo) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerList, ToDoDetailsFragment.newInstance(toDo))
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return true;
    }
}