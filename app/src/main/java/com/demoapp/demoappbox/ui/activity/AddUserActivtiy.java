package com.demoapp.demoappbox.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.demoapp.demoappbox.R;
import com.demoapp.demoappbox.model.User;
import com.demoapp.demoappbox.viewModel.UserViewModelFirebase;

public class AddUserActivtiy extends AppCompatActivity {

    EditText etName, etRoll, etSubject;
    Button btnSave, btnShow;
    UserViewModelFirebase viewModel;
    int userId = -1;
    String person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_activtiy);


        person = "payal";

        etName = findViewById(R.id.etName);
        etRoll = findViewById(R.id.etRoll);
        etSubject = findViewById(R.id.etSubject);
        btnSave = findViewById(R.id.btnSave);

        viewModel = new ViewModelProvider(this)
                .get(UserViewModelFirebase.class);

        btnSave.setOnClickListener(v -> {

            User user = new User(
                    etName.getText().toString(),
                    etRoll.getText().toString(),
                    etSubject.getText().toString()
            );

            viewModel.insert(person, user);
            Toast.makeText(this, "Saved under " + person, Toast.LENGTH_SHORT).show();

            etName.setText("");
            etRoll.setText("");
            etSubject.setText("");

            Intent intent= new Intent(this,CreateUserActivity.class);
            intent.putExtra("loginUser",person);
            startActivity(intent);
        });

    }
}