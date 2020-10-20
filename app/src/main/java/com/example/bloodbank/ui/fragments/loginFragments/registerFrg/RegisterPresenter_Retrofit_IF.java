package com.example.bloodbank.ui.fragments.loginFragments.registerFrg;

import com.example.bloodbank.data.model.city.City;
import com.example.bloodbank.data.model.login.Client;
import com.example.bloodbank.data.model.login.Login;


public interface RegisterPresenter_Retrofit_IF {

    void getBloodList(City blood, String msg, boolean isSuccess);

    void getGovernoratesList(City blood, String msg, boolean isSuccess);

    void getCitiesList(City cityList);

    void clientRegister(Login client, boolean success, String msg);
}
