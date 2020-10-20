package com.example.bloodbank.ui.activities.loginCycleActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.bloodbank.R;
import com.example.bloodbank.ui.fragments.loginFragments.LoginFrg.LoginFragment;

public class LoginCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        setFragments(new LoginFragment());

    }

    private void setFragments(Fragment fragments) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container_loginActivity, fragments);
        ft.commit();
    }
}
