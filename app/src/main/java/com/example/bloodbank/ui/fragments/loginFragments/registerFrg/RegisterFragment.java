package com.example.bloodbank.ui.fragments.loginFragments.registerFrg;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;

import com.example.bloodbank.R;
import com.example.bloodbank.adapters.EmptySpinnerAdapter;
import com.example.bloodbank.data.model.DateTxt;
import com.example.bloodbank.data.model.city.City;
import com.example.bloodbank.data.model.city.CityData;
import com.example.bloodbank.data.model.login.Client;
import com.example.bloodbank.data.model.login.Login;
import com.example.bloodbank.helpers.HelperMe;
import com.example.bloodbank.helpers.HelperMethod;
import com.example.bloodbank.ui.activities.homeCycleActivity.HomeCycleActivity;
import com.example.bloodbank.ui.fragments.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class RegisterFragment extends BaseFragment implements RegisterPresenter_Retrofit_IF, AdapterView.OnItemSelectedListener {
    @BindView(R.id.userNameET_register)
    EditText userNameET;
    @BindView(R.id.emailET_register)
    EditText emailET;

    @BindView(R.id.phoneET_register)
    EditText phoneET;
    @BindView(R.id.passwordET_register)
    EditText passwordET;
    @BindView(R.id.confirmPasswordET_register)
    EditText confirmPasswordET;
    @BindView(R.id.registerBtn_register)
    Button registerBtnRegister;
    @BindView(R.id.bloodTypes)
    AppCompatSpinner bloodTypes;
    @BindView(R.id.bloodTypeTV)
    TextView bloodTypeTV;
    @BindView(R.id.governorateTV_register)
    TextView governorateTVRegister;
    @BindView(R.id.govervorateSpinner)
    AppCompatSpinner govervorateSpinner;
    @BindView(R.id.countryTV_register)
    TextView countryTVRegister;
    @BindView(R.id.countrySpinner)
    AppCompatSpinner countrySpinner;
    @BindView(R.id.birthDateIV)
    ImageView birthDateIV;
    @BindView(R.id.birthDateTV_register)
    TextView birthDateTVRegister;
    @BindView(R.id.lastDateIV)
    ImageView lastDateIV;
    @BindView(R.id.lastDateTV_register)
    TextView lastDateTVRegister;


    private String userName;
    private String email;
    private String birthDate;
    private String bloodType;
    private String lastDonation;
    private String governorate;
    private String country;
    private String phone;
    private String password;
    private String confirmPassword;
    private RegisterPresenter presenter;

    private List<CityData> governorateList;

    private City city;
    private City bloods;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HelperMe.setUnbinder(view, this);

        this.getBloodList();
        this.getGovernorateList();
    }

    private boolean viewsValidation() {
        this.getDataFromViews();
        if (userName.trim().isEmpty()) {
            userNameET.setError(getString(R.string.enterFeild));
            return false;
        } else if (email.trim().isEmpty()) {
            emailET.setError(getString(R.string.enterFeild));
            return false;
        } else if (!HelperMe.isEmailValid(email)) {
            emailET.setError(getString(R.string.email_pattern));
            return false;
        } else if (this.birthDate.isEmpty()) {
            Toast.makeText(getContext(), "" + R.string.birth_date, Toast.LENGTH_SHORT).show();
            return false;
        } else if (this.lastDonation.isEmpty()) {
            Toast.makeText(getContext(), "" + R.string.last_date_for_donation, Toast.LENGTH_SHORT).show();
            return false;
        } else if (phone.trim().isEmpty()) {
            phoneET.setError(getString(R.string.enterFeild));
            return false;
        } else if (phone.length() != 11) {
            phoneET.setError(getString(R.string.phoneNumbers));
            return false;
        } else if (password.trim().isEmpty()) {
            passwordET.setError(getString(R.string.enterFeild));
            return false;
        } else if (password.length() < 6) {
            passwordET.setError(getString(R.string.pass_numbers));
            return false;
        } else if (confirmPassword.trim().isEmpty()) {
            confirmPasswordET.setError(getString(R.string.enterFeild));
            return false;
        } else if (!confirmPassword.equals(password)) {
            confirmPasswordET.setError(getString(R.string.passwordsNotSame));
            return false;
        }
        return true;
    }


    private void getBloodList() {
        this.presenter = RegisterPresenter.getInstance(this);
        presenter.getBloodsList();
    }

    private void getGovernorateList() {
        this.presenter = RegisterPresenter.getInstance(this);
        presenter.getGovernorate();
    }


    private void getDataFromViews() {
        this.userName = userNameET.getText().toString();
        this.email = emailET.getText().toString();
        this.phone = phoneET.getText().toString();
        this.password = passwordET.getText().toString();
        this.confirmPassword = confirmPasswordET.getText().toString();
        this.lastDonation = lastDateTVRegister.getText().toString();
        this.birthDate = birthDateTVRegister.getText().toString();
    }

    @Override
    public void getBloodList(City city, String msg, boolean isSuccess) {
        this.bloods = city;
        if (isSuccess) {
            this.setSpinners(city.getData(), bloodTypes);
        } else {
            Toast.makeText(getContext(), "" + msg, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void getGovernoratesList(City city, String msg, boolean isSuccess) {
        if (isSuccess) {
            this.governorateList = city.getData();
            this.setSpinners(city.getData(), govervorateSpinner);
            this.getCitiesFromPresenter(city.getData().get(0).getId());
        } else {
            Toast.makeText(getContext(), "" + msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getCitiesList(City city) {
        this.city = city;
        this.setSpinners(city.getData(), countrySpinner);
    }

    @Override
    public void clientRegister(Login client, boolean success, String msg) {
        if (success) {
            Intent intent = new Intent(getActivity(), HomeCycleActivity.class);
            startActivity(intent);
        } else {
            this.birthDateTVRegister.setText(client.getMsg());
        }
    }


    private void getCitiesFromPresenter(int governorateID) {
        this.presenter.getCiries(governorateID);
    }

    // call this method when list is ready (take from database)
    private void setSpinners(List<CityData> cityData, Spinner spinner) {
        EmptySpinnerAdapter adapter = new EmptySpinnerAdapter(getContext());
        adapter.setBloodTypes(cityData);
        spinner.setAdapter(adapter);
    }


    @OnClick({R.id.birthDateIV, R.id.lastDateIV, R.id.registerBtn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.birthDateIV:
                HelperMethod.showCalender(getContext(), getString(R.string.birth_date)
                        , this.birthDateTVRegister,
                        new DateTxt(HelperMe.getCurrentDay(), HelperMe.getCurrentMonth()
                                , "1990", "amr"));
                break;
            case R.id.lastDateIV:
                HelperMethod.showCalender(getContext(), getString(R.string.last_donation_date)
                        , this.lastDateTVRegister,
                        new DateTxt(HelperMe.getCurrentDay(), HelperMe.getCurrentMonth()
                                , "2018", ""));
                break;
            case R.id.registerBtn_register:
                if (viewsValidation()) {
                    Client client = setClientData();
                    this.registerClient(client);
                }
                break;
        }
    }

    private Client setClientData() {
        String cityID = String.valueOf(
                this.city.getData().get(countrySpinner.getSelectedItemPosition()).getId()
        );
        String bloodID = String.valueOf(
                this.bloods.getData().get(bloodTypes.getSelectedItemPosition()).getId());
        return new Client(
                userName,
                email,
                birthDate,
                cityID,
                phone,
                lastDonation,
                bloodID,
                password
        );

    }

    private void registerClient(Client client) {
        this.presenter.registerClient(client);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()) {
            case R.id.bloodTypes:
                this.bloodType = bloodTypes.getSelectedItem().toString();
                break;
            case R.id.govervorateSpinner:

                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
