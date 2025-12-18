package com.demoapp.demoappbox.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class UserListFragment extends Fragment {
        public static UserListFragment newInstance(String person) {
            Bundle b = new Bundle();
            b.putString("person", person);
            UserListFragment f = new UserListFragment();
            f.setArguments(b);
            return f;
        }

}
