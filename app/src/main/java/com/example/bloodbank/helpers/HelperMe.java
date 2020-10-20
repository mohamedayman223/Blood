package com.example.bloodbank.helpers;


import android.view.View;

import androidx.fragment.app.Fragment;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HelperMe {
    public static Unbinder unbinder;
    private static Calendar calendar = Calendar.getInstance();

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void setUnbinder(View view, Object object) {
        unbinder = ButterKnife.bind(object, view);
    }

    public static String getCurrentDay() {
        return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }

    public static String getCurrentMonth() {
        return String.valueOf(calendar.get(Calendar.MONTH));
    }

    public static String getCurrentYear() {
        return String.valueOf(calendar.get(Calendar.YEAR));
    }


}
