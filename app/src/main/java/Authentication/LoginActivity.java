package Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.air_quality_monitoring_app.HomeActivity;
import com.example.air_quality_monitoring_app.R;
import com.example.air_quality_monitoring_app.api.ApiService;
import com.example.air_quality_monitoring_app.api.Token;
import com.example.air_quality_monitoring_app.controller.AppController;
import com.example.air_quality_monitoring_app.controller.UserController;
import com.example.air_quality_monitoring_app.model.User;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    final AppController appController = AppController.getInstance();
    final UserController userController = UserController.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        MaterialButton logInBtn = findViewById(R.id.btnLogin);

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userNameGet = findViewById(R.id.username);
                EditText userPassGet = findViewById(R.id.password);
                String userName = userNameGet.getText().toString();
                String userPass = userPassGet.getText().toString();

                appController.checkRequiredFields(LoginActivity.this, userName, userPass);
                if (!appController.isNull){
                    ApiService.apiService.getToken("openremote", userName, userPass, "password")
                            .enqueue(new Callback<Token>() {
                                @Override
                                public void onResponse (Call<Token> call, Response<Token> response) {
                                    Log.d("APIC CALL", "SUCCESS ");

                                    Token token = response.body();
                                    if (token == null) {
                                        Log.d("APIC CALL", "null");
                                        Toast.makeText(LoginActivity.this, "Account does not exists!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Log.d("APIC CALL", token.getToken());
                                        userController.user = new User(userName, userPass);
//                            User user = new User(userName, userPass1, userMail);
                                        Log.d("APIC CALL", userController.user.getUserEmail());
                                        Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
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
        });

        TextView txSingUp = findViewById(R.id.login_to_signup);
        txSingUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,
                        SignupActivity.class);
                startActivity(intent);

            }
        });

    }
}