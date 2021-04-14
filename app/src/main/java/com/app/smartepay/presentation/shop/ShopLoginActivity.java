package com.app.smartepay.presentation.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.smartepay.R;
import com.app.smartepay.viewmodels.LoginViewModel;

public class ShopLoginActivity extends AppCompatActivity {

    @BindView(R.id.editTextEmail)
    EditText editTextEmail;
    @BindView(R.id.editTextPassword)
    EditText editTextPassword;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_login);
        ButterKnife.bind(this);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.getSuccess().observe(this, success -> {
            if (success) {
                startActivity(new Intent(this, MainShopActivity.class));
            } else {
                Toast.makeText(ShopLoginActivity.this, "Invalid credentials..", Toast.LENGTH_SHORT).show();
            }
        });
        loginViewModel.getError().observe(this, error -> {
            Toast.makeText(ShopLoginActivity.this, error, Toast.LENGTH_SHORT).show();
        });
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }

    @OnClick(R.id.btnLogin)
    public void onLoginClicked() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        loginViewModel.login(email, password);
    }

    @OnClick(R.id.btnRegister)
    public void onRegisterClicked() {
        startActivity(new Intent(this, ShopRegisterActivity.class));
    }
}