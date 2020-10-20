package com.example.bloodbank.ui.fragments.homeCycleFrg;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.bloodbank.R;
import com.example.bloodbank.adapters.MyViewPagerAdapter;

import com.example.bloodbank.data.model.MyTabsModel;
import com.example.bloodbank.ui.fragments.homeCycleFrg.HomeFragmet.ArticlesFrg;
import com.example.bloodbank.ui.fragments.homeCycleFrg.HomeFragmet.DonationsFrg;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment {
    @BindView(R.id.homeTabLayout)
    TabLayout tabLayout;
    @BindView(R.id.homeViewPager)
    ViewPager viewPager;
    private MyViewPagerAdapter adapter;

    private Unbinder unbinder;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.adapter = new MyViewPagerAdapter(getFragmentManager(), 1);
        setViews();

        this.viewPager.setAdapter(adapter);

    }

    private void setViews() {
        tabLayout.setupWithViewPager(viewPager);
        List<MyTabsModel> list = new ArrayList<>();
        list.add(new MyTabsModel(getString(R.string.aricles), new ArticlesFrg()));
        list.add(new MyTabsModel(getString(R.string.donations), new DonationsFrg()));

        this.adapter.setTabs_FragmentsList(list);

    }
}
