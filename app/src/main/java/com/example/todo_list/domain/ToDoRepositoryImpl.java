package com.example.todo_list.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ToDoRepositoryImpl implements ToDoRepository {

    public static final ToDoRepository INSTANCE = new ToDoRepositoryImpl();
    private final ArrayList<ToDo> todos = new ArrayList<>();

    public ToDoRepositoryImpl(){

        todos.add(new ToDo("i am first note", "description test"));

    }

    @Override
    public List<ToDo> getToDo() {
        return todos;
    }

    @Override
    public ToDo add(String name, String description) {
        ToDo toDo = new ToDo(name, description);
        todos.add(toDo);
        return toDo;
    }

    @Override
    public void remove(ToDo toDo) {
        todos.remove(toDo);
    }

}

