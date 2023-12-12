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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.air_quality_monitoring_app.HomeActivity;
import com.example.air_quality_monitoring_app.R;
import com.example.air_quality_monitoring_app.controller.AppController;
import com.google.android.material.button.MaterialButton;

import com.example.air_quality_monitoring_app.api.ApiService;
import com.example.air_quality_monitoring_app.api.Token;
import com.example.air_quality_monitoring_app.controller.UserController;
import com.example.air_quality_monitoring_app.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    final AppController appController = AppController.getInstance();
    UserController userController = UserController.getInstance();
    EditText edtEmail, edtPass, edtCPass;
    MaterialButton SignupBtn;
    TextView signupToLogin;

    String inputMail;
    String inputPass;
    String inputRePass;
    CheckBox showPasswordCheckBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();

        showPasswordCheckBox = findViewById(R.id.show_password_checkbox);
        // Lắng nghe sự kiện khi người dùng thay đổi trạng thái của CheckBox
        showPasswordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Nếu người dùng chọn CheckBox, hiển thị mật khẩu; ngược lại, ẩn mật khẩu
                if (isChecked) {
                    edtPass.setInputType(InputType.TYPE_CLASS_TEXT);
                    edtCPass.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    edtPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    edtCPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        Button signUpBtn = findViewById(R.id.btnSignup);
        RelativeLayout signUpContent =findViewById(R.id.singup_content);

        signUpContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                appController.hideKeyboard(SignupActivity.this.getApplicationContext(), view);
                return false;
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userEamilGet = findViewById(R.id.edt_email);
                EditText userPassGet = findViewById(R.id.edt_pass);
                EditText userRePassGet = findViewById(R.id.edt_cpass);

                inputMail = userEamilGet.getText().toString();
                inputPass = userPassGet.getText().toString();
                inputRePass = userRePassGet.getText().toString();

                appController.checkRequiredFields(SignupActivity.this, inputMail, inputPass, inputRePass);
                if (!appController.isNull) {
                    Intent i = new Intent(SignupActivity.this, WebViewConnect.class);
                    i.putExtra("usermail_store", inputMail);
                    i.putExtra("userpass_store", inputPass);
                    i.putExtra("userre_store", inputRePass);
                    startActivity(i);
                }
            }
        });

        signupToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this,
                        LoginActivity.class));
            }
        });
    }

    void init() {
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPass = (EditText) findViewById(R.id.edt_pass);
        edtCPass = (EditText) findViewById(R.id.edt_cpass);
        SignupBtn = (MaterialButton) findViewById(R.id.btnSignup);
        signupToLogin = (TextView) findViewById(R.id.signup_to_login);
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.d("onRestart()", "get into onRestart() : ");
        ApiService.apiService.getToken("openremote", inputMail, inputPass, "password")
                .enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse (Call<Token> call, Response<Token> response) {
                        Log.d("APIC CALL", "SUCCESS ");
                        Log.d("Restart", "get into signup from fillform() : ");
                        Token token = response.body();
                        if (token == null) {
                            Log.d("APIC CALL", "null");
                            Toast.makeText(SignupActivity.this, "Invalid input! Please check again!", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("APIC CALL", token.getToken());
                            userController.user = new User(inputMail, inputPass);
                            Log.d("APIC CALL", userController.user.getUserEmail());
                            Toast.makeText(SignupActivity.this, "Signup success", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SignupActivity.this, HomeActivity.class);
                            startActivity(i);
                        }
                    }
                    @Override
                    public void onFailure(Call<Token> call, Throwable t) {
                        Log.d("APIC CALL", "FAILED ");
                    }
                });

    }

}