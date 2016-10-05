package com.hado.myexample.socketio;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hado on 15-Sep-16.
 */
public class User {
    @SerializedName("username")
    public String userName;

    @SerializedName("age")
    public int age;

    @SerializedName("gender")
    public boolean gender;

    @Override
    public String toString() {
        return userName + "\n" + age + "\n" + gender;
    }
}
