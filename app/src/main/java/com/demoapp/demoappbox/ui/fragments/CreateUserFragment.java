package com.demoapp.demoappbox.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.demoapp.demoappbox.R;
import com.demoapp.demoappbox.model.User;
import com.demoapp.demoappbox.viewModel.UserViewModelFirebase;

public class CreateUserFragment extends Fragment {
    EditText etName, etRoll, etSubject;
    Button btnSave, btnShow;
    UserViewModelFirebase viewModel;
    int userId = -1;
    String person;

    public static CreateUserFragment newInstance(String person) {
        Bundle b = new Bundle();
        b.putString("person", person);
        CreateUserFragment f = new CreateUserFragment();
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_user,
                container, false);

        person = getArguments().getString("person");

        etName = view.findViewById(R.id.etName);
        etRoll = view.findViewById(R.id.etRoll);
        etSubject = view.findViewById(R.id.etSubject);
        btnSave = view.findViewById(R.id.btnSave);

        viewModel = new ViewModelProvider(this)
                .get(UserViewModelFirebase.class);

        btnSave.setOnClickListener(v -> {

            User user = new User(
                    etName.getText().toString(),
                    etRoll.getText().toString(),
                    etSubject.getText().toString()
            );

            viewModel.insert(person, user);

            Toast.makeText(getContext(),
                    "Saved under " + person,
                    Toast.LENGTH_SHORT).show();

            etName.setText("");
            etRoll.setText("");
            etSubject.setText("");
        });

        return view;

 //       btnShow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(CreateUserActivity.this,ListUserActivity.class));
//            }
//        });
    }
}
