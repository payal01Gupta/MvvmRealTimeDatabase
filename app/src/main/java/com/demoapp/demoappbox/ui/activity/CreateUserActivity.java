package com.demoapp.demoappbox.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.demoapp.demoappbox.R;
import com.demoapp.demoappbox.ui.fragments.CreateUserFragment;
import com.demoapp.demoappbox.ui.fragments.UserListFragment;
import com.demoapp.demoappbox.viewModel.UserViewModelFirebase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class CreateUserActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    FloatingActionButton fab;
    UserViewModelFirebase viewModel;

    String currentPerson = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        bottomNavigation = findViewById(R.id.bottomNavigation);
        fab = findViewById(R.id.fabList);

        viewModel = new ViewModelProvider(this).get(UserViewModelFirebase.class);

        observeFirebasePersons();

        fab.setOnClickListener(v -> openListFragment());


        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() == null) {
            auth.signInAnonymously()
                    .addOnSuccessListener(result -> {
                        Log.e("AUTH", "Anonymous login success: " + result.getUser().getUid());
                    })
                    .addOnFailureListener(e -> {
                        Log.e("AUTH", "Auth failed: " + e.getMessage());
                    });
        }

        viewModel = new ViewModelProvider(this).get(UserViewModelFirebase.class);

//        if (getIntent().hasExtra("id")) {
//            userId = getIntent().getIntExtra("id", -1);
//            etName.setText(getIntent().getStringExtra("name"));
//            etRoll.setText(getIntent().getStringExtra("roll"));
//            etSubject.setText(getIntent().getStringExtra("subject"));
//            btnSave.setText("Update");
//        }
    }

    private void observeFirebasePersons() {

        viewModel.getPersonNames()
                .observe(this, names -> {

                    Menu menu = bottomNavigation.getMenu();
                    menu.clear();

                    for (int i = 0; i < names.size(); i++) {
                        menu.add(Menu.NONE, i, i, names.get(i))
                                .setIcon(android.R.drawable.ic_menu_myplaces);
                    }

                    // select first tab by default
                    if (!names.isEmpty()) {
                        currentPerson = names.get(0);
                        openCreateFragment();
                    }

                    bottomNavigation.setOnItemSelectedListener(item -> {
                        currentPerson = item.getTitle().toString();
                        openCreateFragment();
                        return true;
                    });
                });
    }

    private void openCreateFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,
                        CreateUserFragment.newInstance(currentPerson))
                .commit();
    }

    private void openListFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,
                        UserListFragment.newInstance(currentPerson))
                .addToBackStack(null)
                .commit();
    }
}