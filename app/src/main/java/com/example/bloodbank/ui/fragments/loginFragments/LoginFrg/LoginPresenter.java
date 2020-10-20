package com.example.bloodbank.ui.fragments.loginFragments.LoginFrg;

import com.example.bloodbank.data.model.login.Login;
import com.example.bloodbank.data.retrofit.RetrofitFunctions;

public class LoginPresenter implements LoginPresenter_Retrofit_IF {

    private static LoginPresenter INSTANCE;
    private LoginPresenter_Retrofit_IF loginInterface;

    public LoginPresenter(LoginPresenter_Retrofit_IF loginInterface) {
        this.loginInterface = loginInterface;
    }

    public static LoginPresenter getInstance(LoginPresenter_Retrofit_IF loginInterface) {
        if (INSTANCE == null) {
            INSTANCE = new LoginPresenter(loginInterface);
        }
        return INSTANCE;
    }

    protected void loginUser(String phone, String password) {
        RetrofitFunctions.login(phone, password, this);
    }


    @Override
    public void onResponse(Login login, String message, boolean userExists) {
        this.loginInterface.onResponse(login, message, userExists);
    }

    @Override
    public void onFailure(String msg) {
        this.loginInterface.onFailure(msg);
    }
}
