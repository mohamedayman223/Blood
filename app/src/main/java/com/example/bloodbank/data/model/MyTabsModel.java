package com.example.bloodbank.data.model;

import androidx.fragment.app.Fragment;

public class MyTabsModel {
    private String tabName;
    private Fragment fragment;

    public MyTabsModel(String tabName, Fragment fragment) {
        this.tabName = tabName;
        this.fragment = fragment;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
