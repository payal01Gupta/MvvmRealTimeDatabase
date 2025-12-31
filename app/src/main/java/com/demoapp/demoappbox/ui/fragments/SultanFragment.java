package com.demoapp.demoappbox.ui.fragments;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demoapp.demoappbox.R;
import com.demoapp.demoappbox.model.User;
import com.demoapp.demoappbox.ui.adapter.ListUserAdapter;
import com.demoapp.demoappbox.viewModel.UserViewModelFirebase;

import java.util.List;

public class SultanFragment extends Fragment {
    UserViewModelFirebase viewModel;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sultan, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = new ViewModelProvider(requireActivity()).get(UserViewModelFirebase.class);

        viewModel.getUsers("Sultan").observe(getActivity(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                recyclerView.setAdapter(new ListUserAdapter(users));
            }
        });

        return view;
    }
}
