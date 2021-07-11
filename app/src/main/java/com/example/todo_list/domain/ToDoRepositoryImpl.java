package com.example.todo_list.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ToDoRepositoryImpl implements ToDoRepository {

    public static final ToDoRepository INSTANCE = new ToDoRepositoryImpl();
    private final ArrayList<ToDo> todos = new ArrayList<>();

    public ToDoRepositoryImpl(){

        todos.add(new ToDo("1","i am first note", "description test"));

    }

    @Override
    public List<ToDo> getToDo() {
        return todos;
    }

    @Override
    public ToDo add(String name, String description) {
        ToDo toDo = new ToDo(UUID.randomUUID().toString(), name, description);
        todos.add(toDo);
        return toDo;
    }

    @Override
    public void remove(ToDo toDo) {
        todos.remove(toDo);
    }

    @Override
    public ToDo update(ToDo toDo, String name, String description) {

        for (int i = 0; i < todos.size(); i++){

            ToDo item = todos.get(i);

            if (item.getId().equals(toDo.getId())){

                String nameToSet = item.getName();
                String descriptionToSet = item.getDescription();

                if (name != null){
                    nameToSet = name;
                }
                if (description != null){
                    descriptionToSet = description;
                }

                ToDo newToDo = new ToDo(item.getId(), nameToSet, descriptionToSet);

                todos.remove(i);
                todos.add(i, newToDo);
            }
        }
        return toDo;
    }
}

