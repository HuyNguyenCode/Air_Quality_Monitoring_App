package Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.air_quality_monitoring_app.R;
import com.example.air_quality_monitoring_app.api.ApiService;
import com.example.air_quality_monitoring_app.api.Param;
import com.example.air_quality_monitoring_app.api.Token;
import com.example.air_quality_monitoring_app.api.UserAPI;
import com.example.air_quality_monitoring_app.controller.AppController;
import com.example.air_quality_monitoring_app.controller.UserController;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ResetPassWordScreen extends AppCompatActivity {
    UserController userController = UserController.getInstance();
    String userName ;
    String oldPass;
    String newPass;
    String reNewPass;


    final AppController appController = AppController.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);


        RelativeLayout rspassword_content = findViewById(R.id.rspassword_content);
        Button backBtn = findViewById(R.id.btnBack);
        Button submitBtn = findViewById(R.id.btnSave);

        rspassword_content.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                appController.hideKeyboard(ResetPassWordScreen.this.getApplicationContext(), view);
                return false;
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
        CheckBox showPasswordCheckBox = findViewById(R.id.show_password_checkbox);
        EditText passwordEditText = findViewById(R.id.edt_pass);
        EditText newPasswordEditText = findViewById(R.id.edt_newpass);
        EditText confirmEditText = findViewById(R.id.edt_cpass);

        showPasswordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Nếu người dùng chọn CheckBox, hiển thị mật khẩu; ngược lại, ẩn mật khẩu
                if (isChecked) {
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                    newPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                    confirmEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    newPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    confirmEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userNameET = findViewById(R.id.edt_email);
                EditText passwordET = findViewById(R.id.edt_pass);
                EditText newPasswordET = findViewById(R.id.edt_newpass);
                EditText reNewPasswordET = findViewById(R.id.edt_cpass);

                userName = userNameET.getText().toString();
                oldPass = passwordET.getText().toString();
                newPass = newPasswordET.getText().toString();
                reNewPass = reNewPasswordET.getText().toString();

                appController.checkRequiredFields(ResetPassWordScreen.this, userName, oldPass, reNewPass, newPass);
                if (oldPass.equals(newPass)) {
                    Toast.makeText(ResetPassWordScreen.this, "Please enter new password!", Toast.LENGTH_SHORT).show();
                } else {
                    if (!newPass.equals(reNewPass)) {
                        Toast.makeText(ResetPassWordScreen.this, "Password and re-password must be the same!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        ApiService.apiService.getToken("openremote", userName, oldPass, "password")
                                .enqueue(new Callback<Token>() {
                                    @Override
                                    public void onResponse (Call<Token> call, Response<Token> response) {
                                        Log.d("APIC CALL", "SUCCESS ");

                                        Token token = response.body();
                                        if (token == null) {
                                            Log.d("APIC CALL", "null");
                                            Toast.makeText(ResetPassWordScreen.this, "User does not exists", Toast.LENGTH_SHORT).show();
                                        } else {
                                            String accountToken = token.getToken();
                                            System.out.println("Bearer " + accountToken);
                                            ApiService.apiService.getUser("Bearer " + accountToken)
                                                    .enqueue(new Callback<UserAPI>() {
                                                        @Override
                                                        public void onResponse (Call<UserAPI> call, Response<UserAPI> response) {
                                                            Log.d("APIC CALL", "SUCCESS ");

                                                            UserAPI userAPI = response.body();
                                                            if (userAPI == null) {
                                                                Log.d("APIC CALL", "null");
                                                                Toast.makeText(ResetPassWordScreen.this, "User does not exists", Toast.LENGTH_SHORT).show();
                                                            } else {
                                                                String userID = userAPI.getId();
                                                                System.out.println(userID);
                                                                Param param = new Param("string",newPass,true);
                                                                ApiService.apiService.resetPassword("Bearer "  + userController.token,"master",userAPI.getId(),param)
                                                                        .enqueue(new Callback<UserAPI>() {
                                                                            @Override
                                                                            public void onResponse (Call<UserAPI> call, Response<UserAPI> response) {
                                                                                Log.d("PUT", "SUCCESS ");
                                                                                Intent i = new Intent(ResetPassWordScreen.this, WebViewConnect.class);
                                                                                i.putExtra("username_store", userName);
                                                                                i.putExtra("userpass_store", newPass);
                                                                                i.putExtra("userre_store", reNewPass);
                                                                                i.putExtra("update", "true");
                                                                                startActivity(i);
                                                                            }
                                                                            @Override
                                                                            public void onFailure(Call<UserAPI> call, Throwable t) {
                                                                                Log.d("PUT", "FAILED ");
                                                                            }
                                                                        });
                                                            }

                                                        }

                                                        @Override
                                                        public void onFailure(Call<UserAPI> call, Throwable t) {
                                                            Log.d("APIC CALL", "FAILED ");
                                                        }
                                                    });

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Token> call, Throwable t) {
                                        Log.d("APIC CALL", "FAILED ");
                                    }
                                });
                    }
                }
            }
        });

    }
}
//
//ApiService.apiService.getUser("Bearer " + accountToken)
//        .enqueue(new Callback<UserAPI>() {
//@Override
//public void onResponse (Call<UserAPI> call, Response<UserAPI> response) {
//        Log.d("APIC CALL", "SUCCESS ");
//
//        UserAPI userAPI = response.body();
//        if (userAPI == null) {
//        Log.d("APIC CALL", "null");
//        Toast.makeText(ResetPassWordScreen.this, "User does not exists", Toast.LENGTH_SHORT).show();
//        } else {
//        String userID = userAPI.getId();
//        System.out.println(userID);
//        Param param = new Param("string",newPass,true);
////                                                                ApiService.apiService.resetPassword("Bearer "  + userController.token,"master",userAPI.getId(),param)
////                                                                        .enqueue(new Callback<UserAPI>() {
////                                                                            @Override
////                                                                            public void onResponse (Call<UserAPI> call, Response<UserAPI> response) {
////                                                                                Log.d("PUT", "SUCCESS ");
////                                                                                Intent i = new Intent(ResetPassWordScreen.this, WebViewConnect.class);
////                                                                                i.putExtra("username_store", userName);
////                                                                                i.putExtra("userpass_store", newPass);
////                                                                                i.putExtra("userre_store", reNewPass);
////                                                                                i.putExtra("update", "true");
////                                                                                startActivity(i);
////                                                                            }
////                                                                            @Override
////                                                                            public void onFailure(Call<UserAPI> call, Throwable t) {
////                                                                                Log.d("PUT", "FAILED ");
////                                                                            }
////                                                                        });
//
//        WebView webView = findViewById(R.id.webviewrs);
//        CookieManager.getInstance().setAcceptCookie(true);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl("https://uiot.ixxc.dev/");
//
//        String pwdScript = "document.getElementById('password-new').value='" + newPass + "';";
//        String repwdScript = "document.getElementById('password-confirm').value='" + reNewPass + "';";
//        String submitScript = "document.querySelector('button').click()";
//
//        // Fill password and confirm password
//        webView.evaluateJavascript(pwdScript, null);
//        webView.evaluateJavascript(repwdScript, null);
//        webView.evaluateJavascript(submitScript,null);
//
//        // Delay for a short time to ensure JavaScript execution
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//@Override
//public void run() {
//        // Make the API call to update password
//        ApiService.apiService.resetPassword("Bearer "  + userController.token,"master",userAPI.getId(),param);
//        call.enqueue(new Callback<UserAPI>() {
//@Override
//public void onResponse(Call<UserAPI> call, Response<UserAPI> response) {
//        if (response.isSuccessful()) {
//        UserAPI userAPI = response.body();
//        // Handle successful password update
////                                                                            fillReset(view);
//
//        Toast.makeText(ResetPassWordScreen.this, "Password updated successfully!", Toast.LENGTH_SHORT).show();
//        // Perform any additional actions
//
//
//        } else {
//        // Handle API error
//        Toast.makeText(ResetPassWordScreen.this, "Error updating password!", Toast.LENGTH_SHORT).show();
//        Log.e("API Error", response.errorBody().toString());
//        }
//        }
//
//@Override
//public void onFailure(Call<UserAPI> call, Throwable t) {
//        // Handle network or other errors
//        Toast.makeText(ResetPassWordScreen.this, "Error connecting to server!", Toast.LENGTH_SHORT).show();
//        Log.e("Network Error", t.getMessage());
//        }
//        });
//        }
//        }, 1500); // Adjust delay as needed
//
//        }
//
//        }
//
//@Override
//public void onFailure(Call<UserAPI> call, Throwable t) {
//        Log.d("APIC CALL", "FAILED ");
//        }
//        });