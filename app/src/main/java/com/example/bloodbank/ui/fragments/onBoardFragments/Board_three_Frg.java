package com.example.bloodbank.ui.fragments.onBoardFragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.bloodbank.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Board_three_Frg extends Fragment {
    private OnBoardFrgs_IF onBoardFrgs_if;
    private Unbinder unbinder;

    public Board_three_Frg() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.onBoardFrgs_if = (OnBoardFrgs_IF) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_onboard_three__frg, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @OnClick(R.id.done_Fab)
    public void onViewClicked() {
        this.onBoardFrgs_if.complete();
    }
}
