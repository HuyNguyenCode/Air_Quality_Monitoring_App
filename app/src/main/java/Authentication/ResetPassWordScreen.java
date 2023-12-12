package Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
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
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
//                                                                    i.putExtra("usermail_store", 123);
                                                                                i.putExtra("userpass_store", newPass);
//                                                                    i.putExtra("userre_store", 123);
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