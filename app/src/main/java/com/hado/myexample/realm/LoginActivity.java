package com.hado.myexample.realm;

import android.widget.EditText;
import android.widget.Toast;

import com.hado.myexample.R;
import com.hado.myexample.activity.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Ngo Hado on 10-Jul-16.
 */
public class LoginActivity extends BaseActivity implements LoginMVP.RequiredViewOps {

    protected final String TAG = getClass().getSimpleName();

    @Bind(R.id.edt_username)
    EditText edtUsername;

    @Bind(R.id.edt_password)
    EditText edtPassword;

    StateMaintainer stateMaintainer = new StateMaintainer(getSupportFragmentManager(), TAG);

    LoginPresenter loginPresenter;

    @Override
    protected void initData() {
        if (stateMaintainer.isFirstTime()) {
            initialize();
        } else {
            reinitialize();
        }
    }

    private void reinitialize() {
        loginPresenter = stateMaintainer.get(LoginPresenter.class.getSimpleName());
        if (loginPresenter == null) {
            initialize();
        } else {
            loginPresenter.onConfigurationChanged(this);
        }
    }

    private void initialize() {
        loginPresenter = new LoginPresenter(this);
        stateMaintainer.put(loginPresenter);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int setContentViewID() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.btn_login)
    public void onClickLogin() {
        loginPresenter.login(edtUsername.getText().toString(), edtPassword.getText().toString());
    }

    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showToast(boolean isSuccess, String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAlert(boolean isSuccess, String message) {

    }
}
