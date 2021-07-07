package com.example.todo_list.domain;

import java.util.List;

public interface ToDoRepository {

    List<ToDo> getToDo();

    ToDo add(String name, String description);

    void remove(ToDo toDo);
}
