package com.example.bloodbank.ui.fragments.loginFragments.LoginFrg;

import com.example.bloodbank.data.model.login.Login;

public interface LoginPresenter_Retrofit_IF {
    void onResponse(Login login, String message,boolean userExists);

    void onFailure(String msg);

}
