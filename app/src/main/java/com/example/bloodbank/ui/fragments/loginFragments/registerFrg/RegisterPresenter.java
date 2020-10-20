package com.example.bloodbank.ui.fragments.loginFragments.registerFrg;

import com.example.bloodbank.data.model.city.City;
import com.example.bloodbank.data.model.login.Client;
import com.example.bloodbank.data.model.login.Login;
import com.example.bloodbank.data.retrofit.RetrofitFunctions;

import java.util.List;

public class RegisterPresenter implements RegisterPresenter_Retrofit_IF {

    private static RegisterPresenter INSTANCE;
    private RegisterPresenter_Retrofit_IF regInterface;

    public RegisterPresenter(RegisterPresenter_Retrofit_IF regInterface) {
        this.regInterface = regInterface;
    }

    public static RegisterPresenter getInstance(RegisterPresenter_Retrofit_IF regInterface) {
        if (INSTANCE == null) {
            INSTANCE = new RegisterPresenter(regInterface);
        }
        return INSTANCE;
    }

    public void getBloodsList() {
        RetrofitFunctions.getBloodsList(this);
    }

    public void getGovernorate() {
        RetrofitFunctions.getGoverNorates(this);
    }

    public void getCiries(int governorateID) {
        RetrofitFunctions.getCities(governorateID, this);
    }

    public void registerClient(Client client) {
        RetrofitFunctions.registerClient(client, this);
    }

    @Override
    public void getBloodList(City city, String msg, boolean isSuccess) {
        regInterface.getBloodList(city, msg, isSuccess);
    }

    @Override
    public void getGovernoratesList(City city, String msg, boolean isSuccess) {
        this.regInterface.getGovernoratesList(city, msg, isSuccess);
    }

    @Override
    public void getCitiesList(City city) {
        this.regInterface.getCitiesList(city);
    }

    @Override
    public void clientRegister(Login client, boolean success, String msg) {
        this.regInterface.clientRegister(client, success, msg);
    }

}
