package com.example.todo_list.ui.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo_list.R;
import com.example.todo_list.domain.ToDo;
import com.example.todo_list.domain.ToDoRepositoryImpl;

import java.util.List;

import static com.example.todo_list.MainActivity.ARG_TODO_LIST;

public class ToDoListFragment extends Fragment {

    public static final String ARG_TODO_LIST = "ARG_TODO_LIST";
    public static final String TAG = "ToDoListFragment";

    private ToDoRepositoryImpl toDoRepository;
    private MainRouter mRouter;

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

        ToDoRepositoryImpl toDoRepository = new ToDoRepositoryImpl();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_todo_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.containerList);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        List<ToDo> todos = toDoRepository.getToDo();

        ToDoAdapter toDoAdapter = new ToDoAdapter();
        toDoAdapter.setData(todos);

        recyclerView.setAdapter(toDoAdapter);

        toDoAdapter.notifyDataSetChanged();

        toDoAdapter.setOnToDoClicked(new ToDoAdapter.onToDoClicked() {
            @Override
            public void onToDoClicked(ToDo toDo) {
                mRouter.showToDoDetails(toDo);
            }
        });
    }
}
