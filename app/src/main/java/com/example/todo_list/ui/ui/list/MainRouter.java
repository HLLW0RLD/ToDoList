package com.example.todo_list.ui.ui.list;

import androidx.fragment.app.FragmentManager;

import com.example.todo_list.R;
import com.example.todo_list.domain.ToDo;
import com.example.todo_list.ui.ui.details.ToDoDetailsFragment;

public class MainRouter {

    private final FragmentManager fragmentManager;

    public MainRouter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void showToDoList(){
        fragmentManager
                .beginTransaction()
                .replace(R.id.containerList, ToDoListFragment.newInstance(), ToDoListFragment.TAG)
                .commit();
    }

    public void showToDoDetails(ToDo toDo){
        fragmentManager
                .beginTransaction()
                .replace(R.id.containerDetails, ToDoDetailsFragment.newInstance(toDo), ToDoDetailsFragment.TAG)
                .addToBackStack(ToDoDetailsFragment.TAG)
                .commit();
    }
}
