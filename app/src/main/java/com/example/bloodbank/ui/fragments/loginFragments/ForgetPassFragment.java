package com.example.bloodbank.ui.fragments.loginFragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.bloodbank.R;
import com.example.bloodbank.ui.fragments.BaseFragment;

public class ForgetPassFragment extends BaseFragment {

    private EditText forgetPassET;
    private Button sendBtn;

    public ForgetPassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forget_pass, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setViews(view);
    }

    private void setViews(View view) {
        this.forgetPassET = view.findViewById(R.id.phoneET_forgetPass);
        this.sendBtn = view.findViewById(R.id.sendPhoneBtn_forgetPass);

        this.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putFragmentInContainer(new ChangePassFragment());
            }

        });
    }

}
