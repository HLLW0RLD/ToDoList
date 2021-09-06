package com.example.todo_list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.todo_list.domain.ToDo;
import com.example.todo_list.ui.ui.list.MainRouter;

public class MainActivity extends AppCompatActivity  {

    public static final String ARG_TODO_LIST = "ARG_TODO_LIST";

    public MainRouter getMainRouter() {
        return mainRouter;
    }

    private MainRouter mainRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainRouter = new MainRouter(getSupportFragmentManager());

        if (savedInstanceState == null){
            mainRouter.showToDoList();
        }
    }
}