package com.example.bloodbank.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bloodbank.R;

public class BaseActivity extends AppCompatActivity {

    public void putFragmentInContainer(Fragment fragment, int containerID) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(containerID, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

}
