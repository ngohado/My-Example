package com.hado.myexample.realm;

import com.hado.myexample.realm.model.AccountRealmModel;

import java.util.List;

/**
 * Created by Hado on 06-Aug-16.
 */
public class LoginModel implements LoginMVP.ModelOps {
    private LoginMVP.RequiredPresenterOps presenterOps;
    private RealmController realmController;

    public LoginModel(LoginMVP.RequiredPresenterOps presenterOps) {
        this.presenterOps = presenterOps;
        realmController = RealmController.getInstance();
    }

    @Override
    public void login(String userName, String password) {
        List<AccountRealmModel> accounts = realmController.getAllAccounts();
        if (accounts == null || accounts.size() == 0) {
            presenterOps.onLogin(false, "Username or password aren't correct");
        } else {
            presenterOps.onLogin(false, "Login Successful");
        }

    }

    @Override
    public void onDestroy() {
        realmController.closeRealm();
    }
}
