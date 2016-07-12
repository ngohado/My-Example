package com.hado.myexample.realm;

import android.widget.EditText;
import android.widget.Toast;

import com.hado.myexample.R;
import com.hado.myexample.activity.BaseActivity;
import com.hado.myexample.realm.model.AccountRealmModel;
import com.hado.myexample.util.DebugLog;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Ngo Hado on 10-Jul-16.
 */
public class LoginActivity extends BaseActivity {

    @Bind(R.id.edt_username)
    EditText edtUsername;

    @Bind(R.id.edt_password)
    EditText edtPassword;

    RealmController realmController;


    @Override
    protected void initData() {
        realmController = RealmController.getInstance();
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
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        Date timeLogin = Calendar.getInstance().getTime();
        if (realmController.saveAccount(new AccountRealmModel(username, password, timeLogin))) {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            List<AccountRealmModel> allAccounts = realmController.getAllAccounts();
            if (allAccounts != null) {
                for (AccountRealmModel account : allAccounts) {
                    DebugLog.i(String.format("ID: %d, Username: %s, Password: %s, LastLogin: %s\n",
                            account.id, account.username, account.password, account.lastLogin.toString()));
                }
            } else {
                DebugLog.e("List is null");
            }
        } else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
        }

        edtUsername.setText("");
        edtPassword.setText("");
    }

    @Override
    protected void onDestroy() {
        realmController.closeRealm();
        super.onDestroy();
    }
}
