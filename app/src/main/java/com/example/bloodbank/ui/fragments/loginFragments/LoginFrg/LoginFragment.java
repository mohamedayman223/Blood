package com.example.bloodbank.ui.fragments.loginFragments.LoginFrg;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.bloodbank.R;
import com.example.bloodbank.data.model.login.Login;
import com.example.bloodbank.helpers.HelperMe;
import com.example.bloodbank.ui.activities.homeCycleActivity.HomeCycleActivity;
import com.example.bloodbank.ui.fragments.BaseFragment;
import com.example.bloodbank.ui.fragments.loginFragments.ForgetPassFragment;
import com.example.bloodbank.ui.fragments.loginFragments.registerFrg.RegisterFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class LoginFragment extends BaseFragment implements LoginPresenter_Retrofit_IF {


    @BindView(R.id.phoneET_login)
    EditText phoneET;
    @BindView(R.id.passwordET_login)
    EditText passwordET;
    private String phone;
    private String password;
    private LoginPresenter presenter;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        HelperMe.setUnbinder(view,this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private boolean viewsValidation() {

        this.phone = phoneET.getText().toString().trim();
        this.password = passwordET.getText().toString().trim();

        if (phone.isEmpty()) {
            phoneET.setError(getString(R.string.phone_empty));
            return false;
        } else if (phone.length() != 11) {
            phoneET.setError(getString(R.string.phone_numbers));
            return false;
        } else if (password.isEmpty()) {
            passwordET.setError(getString(R.string.password_empty));
            return false;
        } else if (password.length() < 6) {
            passwordET.setError(getString(R.string.password_nums));
            return false;
        }
        return true;
    }


    @OnClick({R.id.rememberMeCheckBox_Login, R.id.forgetPassTV_login, R.id.signInBtn_login, R.id.registerTV_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rememberMeCheckBox_Login:
                break;
            case R.id.forgetPassTV_login:
                this.putFragmentInContainer(new ForgetPassFragment());
                break;
            case R.id.signInBtn_login:
                if (viewsValidation()) {
                    this.presenter = LoginPresenter.getInstance(this);
                    this.presenter.loginUser(phone, password);
                }
                break;
            case R.id.registerTV_login:
                this.putFragmentInContainer(new RegisterFragment());
                break;
        }
    }


    @Override
    public void onResponse(Login login, String message, boolean userExists) {
        if (userExists) {
            this.openHomeActivity();
        } else {
            Toast.makeText(getContext(), "" + message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(getContext(), "" + msg, Toast.LENGTH_SHORT).show();
    }

    private void openHomeActivity() {
        Intent intent = new Intent(getContext(), HomeCycleActivity.class);
        startActivity(intent);
    }

}


