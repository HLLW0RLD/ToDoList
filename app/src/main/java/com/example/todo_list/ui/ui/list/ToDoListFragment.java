package com.example.todo_list.ui.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.todo_list.R;
import com.example.todo_list.domain.ToDo;
import com.example.todo_list.domain.ToDoRepository;
import com.example.todo_list.ui.ui.details.ToDoDetailsFragment;

import java.util.List;

import static com.example.todo_list.MainActivity.ARG_TODO_LIST;

public class ToDoListFragment extends Fragment {

    public static final String ARG_TODO = "ARG_TODO_LIST";
    private onToDoClicked onToDoClicked;

    public interface onToDoClicked {
        void onToDoClicked(ToDo toDo);
    }

    private ToDo example;
    private ToDoRepository toDoRepository;

    public static ToDoListFragment newInstance(ToDo toDo){
        ToDoListFragment toDoListFragment = new ToDoListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_TODO_LIST, toDo);
        toDoListFragment.setArguments(bundle);
        return toDoListFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ToDoRepository toDoRepository = new ToDoRepository();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onToDoClicked){
            onToDoClicked = (onToDoClicked) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onToDoClicked = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_todo_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout todoList = view.findViewById(R.id.to_do_list_container);

        List<ToDo> toDos = toDoRepository.getToDo();

        for(ToDo todo: toDos){

            View itemView = LayoutInflater.from(requireContext()).inflate(R.layout.todo_item, todoList, false);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onToDoClicked != null){
                        onToDoClicked.onToDoClicked(todo);
                    }
                }
            });

            TextView todoName = view.findViewById(R.id.todo_name);
            todoName.setText(example.getName());
            todoList.addView(itemView);
        }
    }
}
