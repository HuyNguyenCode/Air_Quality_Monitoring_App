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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.ninegroup.weather.api.client.TokenClient;
import com.ninegroup.weather.databinding.FragmentLoginBinding;
import com.ninegroup.weather.webview.WebViewClient;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private Handler handler;
    private Runnable runnable;
    private String username;
    private String password;
    private com.ninegroup.weather.webview.WebViewClient webViewClient;

    private void initVariables() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                Log.d("Runnable","Runnable is running");
                //Log.d("WebView","is loading: " + WebViewClient.isRunning);

                if (!TokenClient.isRunning) {
                    if (TokenClient.accessToken != null) {
                        Log.d("WebView","is loading (else): " + WebViewClient.isRunning);

                        Intent i = new Intent(getContext(), MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        Toast.makeText(getContext(), "Login successful",
                                Toast.LENGTH_SHORT).show();

                        Log.d("Runnable","Runnable is stopped");
                        handler.removeCallbacks(runnable);
                    }
                    else {
                        binding.signInButton.setText(R.string.sign_in);
                        binding.signInButton.setEnabled(true);
                        binding.progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getContext(), "Login failed! Account does not exist",
                                Toast.LENGTH_SHORT).show();
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
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initVariables();

        binding.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = binding.usernameEditText.getText().toString();
                password = binding.passwordEditText.getText().toString();

                binding.signInButton.setText("");
                binding.signInButton.setEnabled(false);
                binding.progressBar.setVisibility(View.VISIBLE);

                WebStorage.getInstance().deleteAllData();
                CookieManager.getInstance().removeAllCookies(null);
                CookieManager.getInstance().flush();

//                WebView webView = binding.webView;
//                webViewClient = new WebViewClient(username, null, password, null, 2);
//                //webView.setVisibility(View.GONE);
//                CookieManager.getInstance().setAcceptCookie(true);
//                webView.getSettings().setJavaScriptEnabled(true);
//                webView.setWebViewClient(webViewClient);
//                webView.loadUrl("https://uiot.ixxc.dev/");
//
//                Log.d("WebView","is loading: " + WebViewClient.isRunning);

                TokenClient tokenClient = new TokenClient();
                tokenClient.getToken(username, password);

                handler.postDelayed(runnable, 1000);
                //handler.removeCallbacks(runnable);

                //startActivity(new Intent(getContext(), MainActivity.class));
                //Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_mainActivity);
            }
        });

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        binding.forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_resetPasswordFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
