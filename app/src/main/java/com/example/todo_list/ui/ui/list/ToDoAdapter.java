package com.example.todo_list.ui.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo_list.R;
import com.example.todo_list.domain.ToDo;

import java.util.ArrayList;
import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {

    private ArrayList<ToDo> todos = new ArrayList<>();
    private onToDoClicked onToDoClicked;

    public interface onToDoClicked {
        void onToDoClicked(ToDo toDo);

    }

    public void setData(List<ToDo> toSet){
        todos.clear();
        todos.addAll(toSet);
    }

    public int add(ToDo addedToDo){
        todos.add(addedToDo);
        return 0;
    }

    public ToDoAdapter.onToDoClicked getOnToDoClicked() {
        return onToDoClicked;
    }

    public void setOnToDoClicked(ToDoAdapter.onToDoClicked onToDoClicked) {
        this.onToDoClicked = onToDoClicked;
    }

    class ToDoViewHolder extends RecyclerView.ViewHolder{

        TextView todoName;

        public ToDoViewHolder(View itemView) {
            super(itemView);

            todoName = itemView.findViewById(R.id.todo_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getOnToDoClicked() != null){
                        getOnToDoClicked().onToDoClicked(todos.get(getAdapterPosition()));
                    }
                }
            });

        }
    }

    @Override
    public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
        return new ToDoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ToDoAdapter.ToDoViewHolder holder, int position) {

        ToDo todo = todos.get(position);

        holder.todoName.setText(todo.getName());

    }

    @Override
    public int getItemCount() {
        return todos.size();
    }


}
