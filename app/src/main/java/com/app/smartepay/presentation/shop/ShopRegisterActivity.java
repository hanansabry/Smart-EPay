package com.app.smartepay.presentation.shop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.app.smartepay.R;
import com.app.smartepay.viewmodels.RegisterViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class ShopRegisterActivity extends AppCompatActivity {

    @BindView(R.id.editTextEmail)
    EditText editTextEmail;
    @BindView(R.id.editTextPassword)
    EditText editTextPassword;
    @BindView(R.id.editTextConfirmPassword)
    EditText editTextConfirmPassword;
    @BindView(R.id.editTextBrand)
    EditText editTextBrand;
    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_register);
        ButterKnife.bind(this);

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        registerViewModel.getShopLiveData().observe(this, shop -> {
            if (shop != null) {
                registerViewModel.addNewShop(shop);
            } else {
                Toast.makeText(ShopRegisterActivity.this, "Can't create shop, Please try again later", Toast.LENGTH_SHORT).show();
            }
        });
        registerViewModel.getAddShopSuccess().observe(this, success -> {
            if (success) {
                startActivity(new Intent(this, MainShopActivity.class));
            } else {
                Toast.makeText(ShopRegisterActivity.this, "Can't create shop, Please try again later", Toast.LENGTH_SHORT).show();
            }
        });
        registerViewModel.getEmailError().observe(this, error -> {
            editTextEmail.setError(error);
        });
        registerViewModel.getPasswordError().observe(this, error -> {
            editTextPassword.setError(error);
        });
        registerViewModel.getConfirmPasswordError().observe(this, error -> {
            editTextConfirmPassword.setError(error);
        });
        registerViewModel.getShopNameError().observe(this, error -> {
            editTextBrand.setError(error);
        });

    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }

    @OnClick(R.id.btnLogin)
    public void onLoginClicked() {
        startActivity(new Intent(this, ShopLoginActivity.class));
    }

    @OnClick(R.id.btnCreate)
    public void onCreateClicked() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();
        String brandName = editTextBrand.getText().toString();

        registerViewModel.register(email, password, confirmPassword, brandName);
    }

    @OnTextChanged(R.id.editTextEmail)
    public void removeEmailError() {
        editTextEmail.setError(null);
    }

    @OnTextChanged(R.id.editTextPassword)
    public void removePasswordError() {
        editTextPassword.setError(null);
    }

    @OnTextChanged(R.id.editTextConfirmPassword)
    public void removeConfirmPasswordError() {
        editTextConfirmPassword.setError(null);
    }

    @OnTextChanged(R.id.editTextBrand)
    public void removeBrandNameError() {
        editTextBrand.setError(null);
    }
}