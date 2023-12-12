package com.ninegroup.weather;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.ninegroup.weather.api.client.TokenClient;
import com.ninegroup.weather.databinding.FragmentSignupBinding;
import com.ninegroup.weather.webview.WebViewClient;

public class SignupFragment extends Fragment {
    private FragmentSignupBinding binding;
    private Handler handler;
    private Runnable runnable;
    private Runnable tokenGetter;
    private View fragmentView;
    private String username;
    private String password;
    private String email;
    private com.ninegroup.weather.webview.WebViewClient webViewClient;

    private void initVariables() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                Log.d("Runnable","Runnable is running");
                Log.d("WebView","WebView is loading: " + WebViewClient.isRunning);

                if (!WebViewClient.isRunning) {
                    TokenClient tokenClient = new TokenClient();
                    tokenClient.getToken(username, password);

                    Log.d("Runnable","Runnable is stopped");
                    handler.postDelayed(tokenGetter, 1000);
                    handler.removeCallbacks(runnable);
                }
            }
        };

        tokenGetter = new Runnable() {
            @Override
            public void run() {
                Log.d("TokenGetter","TokenGetter is running");
                Log.d("WebView","WebView is loading: " + WebViewClient.isRunning);
                Log.d("TokenClient","TokenClient is running: " + TokenClient.isTokenRunning);

                if (!TokenClient.isTokenRunning) {
                    if (TokenClient.accessToken != null) {
                        Intent i = new Intent(getContext(), MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        Toast.makeText(getContext(), "Signed up and logged in successfully!",
                                Toast.LENGTH_SHORT).show();

                        Log.d("TokenGetter","TokenGetter is stopped. Token is not null");
                        Navigation.findNavController(fragmentView).navigate(R.id.action_signupFragment_to_loginFragment);
                        handler.removeCallbacks(tokenGetter);
                    }
                    else {
                        binding.signUpButton.setText(R.string.sign_up);
                        binding.signUpButton.setEnabled(true);
                        binding.progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getContext(), "Sign up failed! The username or email may already exists",
                                Toast.LENGTH_SHORT).show();
                        Log.d("TokenGetter","TokenGetter is stopped. Token is null");
                        handler.removeCallbacks(tokenGetter);
                    }
                }
            }
        };
    }

    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSignupBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initVariables();
        fragmentView = (View) view;

        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = binding.usernameEditText.getText().toString();
                email = binding.emailEditText.getText().toString();
                password = binding.passwordEditText.getText().toString();

                binding.signUpButton.setText("");
                binding.signUpButton.setEnabled(false);
                binding.progressBar.setVisibility(View.VISIBLE);

                WebStorage.getInstance().deleteAllData();
                CookieManager.getInstance().removeAllCookies(null);
                CookieManager.getInstance().flush();

                WebView webView = binding.webView;
                webViewClient = new WebViewClient(username, email, password, null, 1);
                //webView.setVisibility(View.GONE);
                CookieManager.getInstance().setAcceptCookie(true);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(webViewClient);
                webView.loadUrl("https://uiot.ixxc.dev/");

                handler.postDelayed(runnable, 1000);

//                TokenClient tokenClient = new TokenClient();
//                tokenClient.getToken(username, password);
//                Navigation.findNavController(view).navigate(R.id.action_signupFragment_to_loginFragment);
            }
        });

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
