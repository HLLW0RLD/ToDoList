package com.example.todo_list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.todo_list.domain.ToDo;
import com.example.todo_list.ui.ui.details.ToDoDetailsFragment;
import com.example.todo_list.ui.ui.list.MainRouter;
import com.example.todo_list.ui.ui.list.ToDoAdapter;
import com.example.todo_list.ui.ui.list.ToDoListFragment;

import static com.example.todo_list.ui.ui.details.ToDoDetailsFragment.ARG_TODO;
import static com.example.todo_list.ui.ui.list.ToDoListFragment.ARG_TODO_LIST;

public class MainActivity extends AppCompatActivity implements ToDoAdapter.onToDoClicked  {

    public static final String ARG_TODO_LIST = "ARG_TODO_LIST";
    private MainRouter mainRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToDo toDo = getIntent().getParcelableExtra(ARG_TODO_LIST);

        if (savedInstanceState == null){
            mainRouter.showToDoList(toDo);
        }
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