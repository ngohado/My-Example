package com.hado.myexample.instagram;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hado.myexample.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ngo Hado on 07-Jul-16.
 */
public class InstagramAuthenticationDialog extends Dialog {

    @Bind(R.id.webView)
    public WebView webView;

    public String clientID;
    public String callbackURL;
    public OAuthInstagramListener oauthListener;

    private static InstagramAuthenticationDialog dialog;


    public static InstagramAuthenticationDialog getInstance(Context context, String clientID, String callbackURL, OAuthInstagramListener oauthListener) {
        if (dialog == null)
            dialog = new InstagramAuthenticationDialog(context, clientID, callbackURL, oauthListener);
        return dialog;
    }


    public InstagramAuthenticationDialog(Context context, String clientID, String callbackURL, OAuthInstagramListener oauthListener) {
        super(context);
        this.clientID = clientID;
        this.callbackURL = callbackURL;
        this.oauthListener = oauthListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.instagram_webview);
        ButterKnife.bind(this);
        setUpWebView();
        webView.loadUrl("https://api.instagram.com/oauth/authorize/?client_id=" + clientID + "&redirect_uri=" + callbackURL + "&response_type=token");
    }

    private void setUpWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new OAuthWebViewClient());
    }

    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(params);
    }

    public class OAuthWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith(callbackURL)) {
                oauthListener.onSuccess(callbackURL.split("=")[1]);
                dismiss();
                return true;
            }
            return false;
        }
    }

    public interface OAuthInstagramListener {
        void onSuccess(String accessToken);
    }
}
