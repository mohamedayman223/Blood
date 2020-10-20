package com.example.bloodbank.ui.activities.onBoardCycleActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.bloodbank.R;
import com.example.bloodbank.adapters.MyViewPagerAdapter;
import com.example.bloodbank.ui.activities.BaseActivity;
import com.example.bloodbank.ui.activities.loginCycleActivity.LoginCycleActivity;
import com.example.bloodbank.ui.fragments.onBoardFragments.Board_one_Frg;
import com.example.bloodbank.ui.fragments.onBoardFragments.Board_three_Frg;
import com.example.bloodbank.ui.fragments.onBoardFragments.Board_two_Frg;
import com.example.bloodbank.ui.fragments.onBoardFragments.OnBoardFrgs_IF;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class onBoardActivity extends BaseActivity implements OnBoardFrgs_IF {

    @BindView(R.id.onBoardViewPager)
    ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private MyViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);
        ButterKnife.bind(this);

        this.adapter = new MyViewPagerAdapter(getSupportFragmentManager(), 0);
        this.adapter.setFragmentList(setFragmentList());

        this.viewPager.setAdapter(adapter);
    }

    private List<Fragment> setFragmentList() {
        this.fragmentList.add(new Board_one_Frg());
        this.fragmentList.add(new Board_two_Frg());
        this.fragmentList.add(new Board_three_Frg());
        return fragmentList;
    }

    @Override
    public void next(int position) {
        this.viewPager.setCurrentItem(position + 1);
    }



    @Override
    public void complete() {
        Intent intent = new Intent(this, LoginCycleActivity.class);
        startActivity(intent);
    }
}
