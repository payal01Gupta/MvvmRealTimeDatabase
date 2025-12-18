package com.demoapp.demoappbox.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.demoapp.demoappbox.model.User;
import com.demoapp.demoappbox.ui.repositories.UserRepository;

import java.util.List;

public class UserViewModelFirebase extends AndroidViewModel {
    private final UserRepository repository;

    public UserViewModelFirebase(@NonNull Application application) {
        super(application);
        repository = new UserRepository();
    }

    public void insert(String person, User user) {
        repository.insert(person, user);
    }

    public LiveData<List<User>> getUsers(String person) {
        return repository.getUsers(person);
    }

    public LiveData<List<String>> getPersonNames() {
        return repository.getPersonNames();
    }
}
