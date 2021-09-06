package com.example.todo_list.ui.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.todo_list.MainActivity;
import com.example.todo_list.R;
import com.example.todo_list.domain.ToDo;
import com.example.todo_list.domain.ToDoRepository;
import com.example.todo_list.domain.ToDoRepositoryImpl;

public class ToDoUpdateFragment extends Fragment {

    public static final String TAG = "ToDoUpdateFragment";
    public static final String UPDATE_RESULT = "UPDATE_RESULT";
    public static final String ARG_NOTE = "ARG_NOTE";

    private final ToDoRepository repository = ToDoRepositoryImpl.INSTANCE;

    public static ToDoUpdateFragment newInstance(ToDo toDo) {
        ToDoUpdateFragment fragment = new ToDoUpdateFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_NOTE, toDo);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_to_do_update, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ToDo toDo = getArguments().getParcelable(ARG_NOTE);

        Toolbar toolbar = view.findViewById(R.id.update_toolbar);

        EditText name = view.findViewById(R.id.todo_name);

        EditText description = view.findViewById(R.id.todo_description);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.menu_done){

                    ToDo result = repository.update(toDo, name.getText().toString(), description.getText().toString());

                    if (requireActivity() instanceof MainActivity){
                        MainActivity mainActivity = (MainActivity) requireActivity();
                        mainActivity.getMainRouter().back();

                        Bundle bundle = new Bundle();
                        bundle.putParcelable(ARG_NOTE, result);

                        getParentFragmentManager().setFragmentResult(UPDATE_RESULT, bundle);

                    }
                    return true;
                }
                return false;
            }
        });
    }
}
