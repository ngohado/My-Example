package com.hado.myexample.realm;

/**
 * Created by Hado on 06-Aug-16.
 */
public class LoginPresenter implements LoginMVP.RequiredPresenterOps, LoginMVP.PresenterOps {

    private LoginMVP.RequiredViewOps requiredViewOps;

    private LoginModel loginModel;

    public LoginPresenter(LoginMVP.RequiredViewOps requiredViewOps) {
        this.requiredViewOps = requiredViewOps;
        this.loginModel = new LoginModel(this);
    }

    @Override
    public void login(String userName, String password) {
        loginModel.login(userName, password);
    }

    @Override
    public void onConfigurationChanged(LoginMVP.RequiredViewOps requiredViewOps) {
        this.requiredViewOps = requiredViewOps;
    }

    @Override
    public void onDestroy() {
        loginModel.onDestroy();
    }

    @Override
    public void onLogin(boolean isSuccess, String message) {
        requiredViewOps.showToast(isSuccess, message);
    }
}
