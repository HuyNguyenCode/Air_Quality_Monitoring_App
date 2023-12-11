package com.ninegroup.weather.webview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ninegroup.weather.R;
import com.ninegroup.weather.databinding.ActivityWebviewBinding;

public class WebViewConnect extends AppCompatActivity {
    private ActivityWebviewBinding binding;
    static String userName;
    static String userMail;
    static String userPass1;
    static String userPass2;
    static String isUpdate ="" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWebviewBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent i = getIntent();

        userName = i.getStringExtra("username_store");
        userMail = i.getStringExtra("usermail_store");
        userPass1 = i.getStringExtra("userpass_store");
        userPass2 = i.getStringExtra("userre_store");
        isUpdate =i.getStringExtra("update");

        WebStorage.getInstance().deleteAllData();
        CookieManager.getInstance().removeAllCookies(null);
        CookieManager.getInstance().flush();

        class MyWeb extends WebViewClient {

            @Override
            public void onPageFinished(android.webkit.WebView view, String url) {
                super.onPageFinished(view, url);
                CookieManager.getInstance().setAcceptCookie(true);

                // Clear all the cookies
                if (url.contains("/registration")) {
                    System.out.println("regis");
                    fillForm(view);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 1500); //
                }
                else if (url.contains("UPDATE_PASSWORD")) {
                    System.out.println("UPDATING");
                    fillReset(view);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(WebViewConnect.this, "Reset successfully!", Toast.LENGTH_SHORT).show();
                            finish();
                            //Intent i = new Intent(WebViewConnect.this, SignIn.class);
                            //startActivity(i);
                        }
                    }, 1500); //
                }
                else {
                    if (isUpdate != null) {
                        fillLogin(view);
                    }
                    else
                        view.evaluateJavascript("document.getElementsByClassName('waves-light')[1].click()",null);
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
                return false;
            }
        }

        android.webkit.WebView webView = findViewById(R.id.webView);
//        webView.setVisibility(View.INVISIBLE);
        CookieManager.getInstance().setAcceptCookie(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWeb());
        webView.loadUrl("https://uiot.ixxc.dev/");
    }

    public static void fillForm(android.webkit.WebView view) {
        String usrScript = "document.getElementById('username').value='" + userName + "';";
        String emailScript = "document.getElementById('email').value='" + userMail + "';";
        String pwdScript = "document.getElementById('password').value='" +userPass1 + "';";
        String rePwdScript = "document.getElementById('password-confirm').value='" + userPass2  + "';";
        String submit = "document.querySelector('button[name=\"register\"]').click()";

        view.evaluateJavascript(usrScript,null);
        view.evaluateJavascript(emailScript,null);
        view.evaluateJavascript(pwdScript,null);
        view.evaluateJavascript(rePwdScript,null);
        view.evaluateJavascript(submit,null);
    }

    public static void fillReset(android.webkit.WebView view) {
        String pwdScript = "document.getElementById('password-new').value='" + userPass1 + "';";
        String repwdScript = "document.getElementById('password-confirm').value='" + userPass1 + "';";
        String submitScript = "document.querySelector('button').click()";

        view.evaluateJavascript(pwdScript,null);
        view.evaluateJavascript(repwdScript,null);
        view.evaluateJavascript(submitScript,null);
    }

    public static void fillLogin(WebView view) {
        String pwdScript = "document.getElementById('username').value='" + userName + "';";
        String repwdScript = "document.getElementById('password').value='" + userPass1 + "';";
        String submitScript = "document.querySelector('button').click()";

        view.evaluateJavascript(pwdScript,null);
        view.evaluateJavascript(repwdScript,null);
        view.evaluateJavascript(submitScript,null);
    }
}
