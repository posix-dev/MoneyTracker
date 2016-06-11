package com.example.moneytracker.rest;

import android.content.Context;

import com.example.moneytracker.rest.model.GoogleModel;
import com.example.moneytracker.rest.model.UserLoginModel;
import com.example.moneytracker.rest.model.UserLogoutModel;
import com.example.moneytracker.rest.model.UserRegistrationModel;
import com.example.moneytracker.util.DataBaseApp;

public class RestService {
    private static final String REGISTER_FLAG = "1";

    private RestClient restClient;

    public RestService() {
        restClient = new RestClient();
    }

    public UserRegistrationModel register(String login, String password) {
        return restClient.getRegisterUserApi().registerUser(login, password, REGISTER_FLAG);
    }

    public UserLoginModel login(String login, String password){
        return restClient.getLoginUserApi().loginUser(login, password);
    }

    public GoogleModel getJsonModel(Context context) {
        return restClient.getGoogleTokenApi().googleJsonToken(DataBaseApp.getGoogleToken(context));
    }

    public UserLogoutModel logout() {
        return restClient.getLogoutUserApi().logoutUser();
    }

}
