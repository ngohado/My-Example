package com.hado.myexample.realm.model;

import java.util.Date;

/**
 * Created by Ngo Hado on 10-Jul-16.
 */
public class AccountModel {
    public int id;
    public String username;
    public String password;
    public Date lastLogin;

    public AccountModel(AccountRealmModel accountRealmModel) {
        this.id = accountRealmModel.id;
        this.username = accountRealmModel.username;
        this.password = accountRealmModel.password;
        this.lastLogin = accountRealmModel.lastLogin;
    }
}
