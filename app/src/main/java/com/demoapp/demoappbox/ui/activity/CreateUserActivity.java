package com.demoapp.demoappbox.ui.activity;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.demoapp.demoappbox.R;
import com.demoapp.demoappbox.ui.fragments.AnkitaFragment;
import com.demoapp.demoappbox.ui.fragments.CreateUserFragment;
import com.demoapp.demoappbox.ui.fragments.ManpreetFragment;
import com.demoapp.demoappbox.ui.fragments.PayalFragment;
import com.demoapp.demoappbox.ui.fragments.SultanFragment;
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
    String loginUser="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        bottomNavigation = findViewById(R.id.bottomNavigation);
        fab = findViewById(R.id.fabList);

         loginUser =getIntent().getStringExtra("loginUser");

        viewModel = new ViewModelProvider(this).get(UserViewModelFirebase.class);

  //      observeFirebasePersons();

        Fragment selectFragment= null;
        if(loginUser.equalsIgnoreCase("payal")){
            selectFragment= new PayalFragment();
        }else if(loginUser.equalsIgnoreCase("Sultan")){
            selectFragment= new SultanFragment();
        }

        loadFragment(selectFragment);

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

//        bottomNavigation.setOnItemSelectedListener(item -> {
//
//            Fragment selectedFragment;
//
//            switch (item.getItemId()) {
//
//                case R.id.tab_payal:
//                    selectedFragment = new PayalFragment();
//                    break;
//
//                case R.id.tab_ankita:
//                    selectedFragment = new AnkitaFragment();
//                    break;
//
//                case R.id.tab_sultan:
//                    selectedFragment = new SultanFragment();
//                    break;
//
//                case R.id.tab_manpreet:
//                    selectedFragment = new ManpreetFragment();
//                    break;
//
//                default:
//                    return false;   // 🔥 important
//            }
//
//            loadFragment(selectedFragment);
//            return true;
//        });




        bottomNavigation.setOnItemSelectedListener(item -> {

            Fragment selectedFragment;

            int id = item.getItemId();

            if (id == R.id.tab_payal) {
                selectedFragment = new PayalFragment();

            } else if (id == R.id.tab_ankita) {
                selectedFragment = new AnkitaFragment();

            } else if (id == R.id.tab_sultan) {
                selectedFragment = new SultanFragment();

            } else if (id == R.id.tab_manpreet) {
                selectedFragment = new ManpreetFragment();

            } else {
                return false;
            }

            loadFragment(selectedFragment);
            return true;
        });

    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
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