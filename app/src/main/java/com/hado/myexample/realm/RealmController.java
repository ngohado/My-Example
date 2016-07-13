package com.hado.myexample.realm;

import com.hado.myexample.realm.model.AccountRealmModel;
import com.hado.myexample.util.DebugLog;

import java.util.List;

import io.realm.Realm;

/**
 * Created by Ngo Hado on 10-Jul-16.
 */
public class RealmController {
    private static RealmController realmController;
    private Realm realm;

    public RealmController() {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController getInstance() {
        if (realmController == null) {
            realmController = new RealmController();
        }
        return realmController;
    }

    public List<AccountRealmModel> getAllAccounts() {
        try {
            beginTransaction();
            List<AccountRealmModel> allAccounts = realm.where(AccountRealmModel.class).findAll();
            commitTransaction();
            return allAccounts;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean saveAccount(AccountRealmModel account) {
        try {
            beginTransaction();
            account.id = getMaxID() + 1;
            realm.insert(account);
            commitTransaction();
            return true;
        } catch (Exception e) {
            DebugLog.e(e.toString());
            return false;
        }
    }

    private int getMaxID() {
        List<AccountRealmModel> accounts = realm.where(AccountRealmModel.class).findAll();

        if (accounts == null || accounts.size() == 0)
            return 0;

        int max = accounts.get(0).id;
        for (AccountRealmModel account : accounts)
            if (max <= account.id)
                max = account.id;

        return max;
    }

    public void beginTransaction() {
        realm.beginTransaction();
    }

    public void commitTransaction() {
        realm.commitTransaction();
    }

    public void closeRealm() {
        if (!realm.isClosed())
            realm.close();
    }

}
