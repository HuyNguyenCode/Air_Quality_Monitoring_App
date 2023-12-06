package Authentication;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.example.air_quality_monitoring_app.R;
import androidx.appcompat.app.AppCompatActivity;

//public class WebViewConnect extends AppCompatActivity {
//    static String userName;
//    static String userMail;
//    static String userPass1;
//    static String userPass2;
//    static Boolean isUpdate = false ;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_web_view_connect);
//        Intent i = getIntent();
//        Log.d("Debug", "Get into web view connect");
//        userName = i.getStringExtra("usermail_store");
//        userMail = i.getStringExtra("usermail_store");
//        userPass1 = i.getStringExtra("userpass_store");
//        userPass2 = i.getStringExtra("userre_store");
//        isUpdate = i.getBooleanExtra("update", false);
//
//
//        WebStorage.getInstance().deleteAllData();
//        CookieManager.getInstance().removeAllCookies(null);
//        CookieManager.getInstance().flush();
//
//        class MyWeb extends WebViewClient {
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                CookieManager.getInstance().setAcceptCookie(true);
//                Log.d("onPageFinished()", "Get into onPageFinished()");
//                Log.d("URL: ", url);
//                // Clear all the cookies
//                if (url.contains("/registration")) {
//                    Log.d("URL: ", url);
//                    Log.d("contains(\"/registration\")", "onPageFinished: ");
//                    System.out.println("regis");
//                    fillForm(view);
//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            finish();
//                        }
//                    }, 1500); //
//                }
//                else if (url.contains("UPDATE_PASSWORD")) {
////                    System.out.println("UPDATING");
//                    Log.d("contains(\"/UPDATE_PASSWORD\")", "UPDATING: ");
//                    fillReset(view);
//                    Handler handler = new Handler();
//                    Log.d("Handler", "handler successful: ");
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            Log.d("run()", "get into run(): ");
//                            Toast.makeText(WebViewConnect.this, "Reset successfully!", Toast.LENGTH_SHORT).show();
//                            finish();
//                            Intent i = new Intent(WebViewConnect.this, LoginActivity.class);
//                            startActivity(i);
//                        }
//                    }, 1500); //
//                }
//                else {
//                    if (isUpdate != null) {
//                        fillLogin(view);
//                    }
//                    else
//                        view.evaluateJavascript("document.getElementsByClassName('waves-light')[1].click()",null);
//                }
//            }
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return false;
//            }
//        }
//        WebView webView = findViewById(R.id.webview);
////        webView.setVisibility(View.INVISIBLE);
//        CookieManager.getInstance().setAcceptCookie(true);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setWebViewClient(new MyWeb());
//        webView.loadUrl("https://uiot.ixxc.dev/");
//    }
//
//    public static void fillForm(WebView view) {
//        Log.d("Fillform", "get into fill form : ");
//        String usrScript = "document.getElementById('username').value='" + userName + "';";
//        String emailScript = "document.getElementById('email').value='" + userMail + "';";
//        String pwdScript = "document.getElementById('password').value='" +userPass1 + "';";
//        String rePwdScript = "document.getElementById('password-confirm').value='" + userPass2  + "';";
//        String submit = "document.querySelector('button[name=\"register\"]').click()";
//
//        view.evaluateJavascript(usrScript,null);
//        view.evaluateJavascript(emailScript,null);
//        view.evaluateJavascript(pwdScript,null);
//        view.evaluateJavascript(rePwdScript,null);
//        view.evaluateJavascript(submit,null);
//
//
//    }
//
//    public static void fillReset(WebView view) {
//        Log.d("FillReset", "get into fill reset : ");
//        String pwdScript = "document.getElementById('password-new').value='" + userPass1 + "';";
//        String repwdScript = "document.getElementById('password-confirm').value='" + userPass1 + "';";
//        String submitScript = "document.querySelector('button').click()";
//
//        view.evaluateJavascript(pwdScript,null);
//        view.evaluateJavascript(repwdScript,null);
//        view.evaluateJavascript(submitScript,null);
//
//    }
//
//    public static void fillLogin(WebView view) {
//        Log.d("FillLogin", "get into fill login : ");
//        String pwdScript = "document.getElementById('username').value='" + userName + "';";
//        String repwdScript = "document.getElementById('password').value='" + userPass1 + "';";
//        String submitScript = "document.querySelector('button').click()";
//
//        view.evaluateJavascript(pwdScript,null);
//        view.evaluateJavascript(repwdScript,null);
//        view.evaluateJavascript(submitScript,null);
//
//    }
//}
//



import com.example.air_quality_monitoring_app.R;

public class WebViewConnect extends AppCompatActivity {


    static String userName;
    static String userMail;
    static String userPass1;
    static String userPass2;

    static String isUpdate = " " ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_connect);
        Intent i = getIntent();

        userName = i.getStringExtra("username_store");
        userMail = i.getStringExtra("username_store");
        userPass1 = i.getStringExtra("userpass_store");
        userPass2 = i.getStringExtra("userre_store");
        isUpdate = i.getStringExtra("update");


        WebStorage.getInstance().deleteAllData();
        CookieManager.getInstance().removeAllCookies(null);
        CookieManager.getInstance().flush();


        class MyWeb extends WebViewClient {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                CookieManager.getInstance().setAcceptCookie(true);

                // Clear all the cookies
                Log.d("contains", url);
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
                            Intent i = new Intent(WebViewConnect.this, LoginActivity.class);
                            startActivity(i);
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
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        }
        WebView webView = findViewById(R.id.webview);
//        webView.setVisibility(View.INVISIBLE);
        CookieManager.getInstance().setAcceptCookie(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWeb());
        webView.loadUrl("https://uiot.ixxc.dev/");
    }

    public static void fillForm(WebView view) {
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

    public static void fillReset(WebView view) {
        Log.d("fillReset", "get into fillReset(): ");
        String pwdScript = "document.getElementById('password-new').value='" + userPass1 + "';";
        String repwdScript = "document.getElementById('password-confirm').value='" + userPass1 + "';";
        String submitScript = "document.querySelector('button').click()";

        view.evaluateJavascript(pwdScript,null);
        view.evaluateJavascript(repwdScript,null);
        view.evaluateJavascript(submitScript,null);

    }

    public static void fillLogin(WebView view) {
        Log.d("fillLogin", "get into fillLogin(): ");
        String pwdScript = "document.getElementById('username').value='" + userName + "';";
        String repwdScript = "document.getElementById('password').value='" + userPass1 + "';";
        String submitScript = "document.querySelector('button').click()";
        view.evaluateJavascript(pwdScript,null);
        view.evaluateJavascript(repwdScript,null);
        view.evaluateJavascript(submitScript,null);

    }
}

