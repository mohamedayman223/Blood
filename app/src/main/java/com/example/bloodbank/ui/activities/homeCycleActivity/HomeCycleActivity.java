package com.example.bloodbank.ui.activities.homeCycleActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.bloodbank.R;
import com.example.bloodbank.ui.activities.BaseActivity;
import com.example.bloodbank.ui.fragments.homeCycleFrg.HomeFragment;
import com.example.bloodbank.ui.fragments.homeCycleFrg.UsersFragment.UserFragment;
import com.example.bloodbank.ui.fragments.homeCycleFrg.notificationFragment.NotificationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeCycleActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.homeBottomNav)
    BottomNavigationView homeBottomNav;

    private final int CONTAINER = R.id.homeContainer;
    @BindView(R.id.homeContainer)
    FrameLayout homeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cycle);
        ButterKnife.bind(this);
        this.homeBottomNav.setOnNavigationItemSelectedListener(this);

        putFragmentInContainer(new HomeFragment(), CONTAINER);


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.bottomNav_home:
                putFragmentInContainer(new HomeFragment(), CONTAINER);
                break;
            case R.id.bottomNav_users:
                putFragmentInContainer(new UserFragment(), CONTAINER);
                break;
            case R.id.bottomNav_notification:
                putFragmentInContainer(new NotificationFragment(), CONTAINER);
                break;
            case R.id.bottomNav_more:
                putFragmentInContainer(new HomeFragment(), CONTAINER);
                break;
        }


        return true;
    }

    @OnClick(R.id.homeFab)
    public void onViewClicked() {
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
    }
}
