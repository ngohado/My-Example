package com.hado.myexample.realm;

/**
 * Created by Hado on 06-Aug-16.
 */
public interface LoginMVP {
    /**
     * Presenter -> View
     */
    interface RequiredViewOps {
        void showToast(boolean isSuccess, String message);
        void showAlert(boolean isSuccess, String message);
    }

    /**
     * View -> Presenter
     */
    interface PresenterOps {
        void login(String userName, String password);
        void onConfigurationChanged(RequiredViewOps requiredViewOps);
        void onDestroy();
    }


    /**
     * Model -> Presenter
     */
    interface RequiredPresenterOps {
        void onLogin(boolean isSuccess, String messageError);
    }

    /**
     * Presenter -> Model
     */
    interface ModelOps {
        void login(String userName, String password);
        void onDestroy();
    }

}
