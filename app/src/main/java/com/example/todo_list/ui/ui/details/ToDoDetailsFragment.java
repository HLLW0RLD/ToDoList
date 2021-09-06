package com.example.todo_list.ui.ui.details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.todo_list.MainActivity;
import com.example.todo_list.R;
import com.example.todo_list.domain.ToDo;
import com.example.todo_list.domain.ToDoRepository;
import com.example.todo_list.domain.ToDoRepositoryImpl;
import com.example.todo_list.ui.ui.list.ToDoAdapter;

public class ToDoDetailsFragment extends Fragment {

    public static final String ARG_TODO = "ARG_TODO";
    public static final String TAG = "ToDoDetailsFragment";
    private final ToDoRepository repository = ToDoRepositoryImpl.INSTANCE;
    private ToDoAdapter todoAdapter;

    public static ToDoDetailsFragment newInstance(ToDo toDo){
        ToDoDetailsFragment toDoDetailsFragment = new ToDoDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_TODO, toDo);
        toDoDetailsFragment.setArguments(bundle);
        return toDoDetailsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_to_do_details, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ToDo toDo = getArguments().getParcelable(ARG_TODO);

        TextView name = view.findViewById(R.id.todo_name);

        TextView description = view.findViewById(R.id.todo_description);

        name.setText(toDo.getName());

        description.setText(toDo.getDescription());

        Toolbar toolbar = view.findViewById(R.id.details_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.menu_edit){

                    if (requireActivity() instanceof MainActivity){
                        MainActivity mainActivity = (MainActivity) requireActivity();
                        mainActivity.getMainRouter().showEditNote(toDo);
                    }
                }
                if(item.getItemId() == R.id.menu_delete){

                    repository.remove(toDo);

                    todoAdapter.remove(toDo);
                    todoAdapter.notifyDataSetChanged();
                }
                return true;
            }
        });
    }
}