package com.tymtorneos.modulotymuser1.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UserList {

    @SerializedName("data")
    private List<User> data;

    public List<User> getUserData() {
        return data;
    }

    public void setUserData(List<User> data) {
        this.data = data;
    }
}

