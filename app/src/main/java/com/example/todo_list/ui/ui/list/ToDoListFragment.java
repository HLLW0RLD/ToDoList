package com.example.todo_list.ui.ui.list;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo_list.MainActivity;
import com.example.todo_list.R;
import com.example.todo_list.domain.ToDo;
import com.example.todo_list.domain.ToDoRepository;
import com.example.todo_list.domain.ToDoRepositoryImpl;
import com.example.todo_list.ui.ui.details.ToDoDetailsFragment;
import com.example.todo_list.ui.ui.details.ToDoUpdateFragment;

import java.util.ArrayList;
import java.util.List;

import static com.example.todo_list.MainActivity.ARG_TODO_LIST;

public class ToDoListFragment extends Fragment {

    public static final String ARG_TODO = "ARG_TODO";
    public static final String TAG = "ToDoListFragment";

    private final ToDoRepository repository = ToDoRepositoryImpl.INSTANCE;
    private ToDoAdapter todoAdapter;

    public static ToDoListFragment newInstance(){
        return new ToDoListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        todoAdapter = new ToDoAdapter();

        getParentFragmentManager().setFragmentResultListener(ToDoUpdateFragment.UPDATE_RESULT, this, new FragmentResultListener() {

            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                if (result.containsKey(ToDoUpdateFragment.ARG_NOTE)) {
                    ToDo toDo = result.getParcelable(ToDoUpdateFragment.ARG_NOTE);

                    todoAdapter.update(toDo);

                    todoAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_todo_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.to_do_list_container);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        DefaultItemAnimator animator = new DefaultItemAnimator();

        animator.setAddDuration(1000L);
        animator.setRemoveDuration(1000L);

        recyclerView.setItemAnimator(animator);

        Toolbar toolbar = view.findViewById(R.id.list_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.menu_add) {

                    ToDo addedToDo = repository.add("", "");

                    int index = todoAdapter.add(addedToDo);

                    todoAdapter.notifyItemChanged(index);
                }
                return true;
            }
        });


        List<ToDo> todos = repository.getToDo();

        todoAdapter.setData(todos);

        recyclerView.setAdapter(todoAdapter);

        todoAdapter.notifyDataSetChanged();

        todoAdapter.setOnToDoClicked(new ToDoAdapter.onToDoClicked() {
            @Override
            public void onToDoClicked(ToDo toDo) {
                if (requireActivity() instanceof MainActivity){
                    MainActivity mainActivity = (MainActivity) requireActivity();
                    mainActivity.getMainRouter().showToDoDetails(toDo);
                }
            }
        });
    }
}
