package com.hado.myexample.realm.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Ngo Hado on 10-Jul-16.
 */
public class AccountRealmModel extends RealmObject {
    @PrimaryKey
    public int id;

    public String username;
    public String password;
    public Date lastLogin;

    public AccountRealmModel() {

    }

    public AccountRealmModel(String username, String password, Date lastLogin) {
        this.username = username;
        this.password = password;
        this.lastLogin = lastLogin;
    }

    public AccountRealmModel(AccountModel accountModel) {
        this.id = accountModel.id;
        this.username = accountModel.username;
        this.password = accountModel.password;
        this.lastLogin = accountModel.lastLogin;
    }
}
