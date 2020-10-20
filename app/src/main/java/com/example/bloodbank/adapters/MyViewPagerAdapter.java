package com.example.bloodbank.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.bloodbank.data.model.MyTabsModel;

import java.util.ArrayList;
import java.util.List;

public class MyViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<MyTabsModel> tabsFrg_List = new ArrayList<>();

    public MyViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    public void setFragmentList(List<Fragment> list) {
        this.fragmentList = list;
    }

    public void setTabs_FragmentsList(List<MyTabsModel> myTabsModels) {
        this.tabsFrg_List = myTabsModels;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (tabsFrg_List.size() > 0) {
            return tabsFrg_List.get(position).getFragment();
        }
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        if (tabsFrg_List.size() > 0) {
            return tabsFrg_List.size();
        }
        return fragmentList.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (tabsFrg_List.size() > 0) {
            return this.tabsFrg_List.get(position).getTabName();
        }
        return null;
    }
}
