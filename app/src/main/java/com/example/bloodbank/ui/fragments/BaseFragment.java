package com.example.bloodbank.ui.fragments;


import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bloodbank.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseFragment extends Fragment {
    public BaseFragment() {
    }

    public void putFragmentInContainer(Fragment fragment) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container_loginActivity, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }




}
