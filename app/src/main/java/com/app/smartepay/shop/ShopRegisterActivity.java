package com.app.smartepay.shop;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.app.smartepay.R;

public class ShopRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_register);
        ButterKnife.bind(this);
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
        Toast.makeText(this, "Shop is created successfully", Toast.LENGTH_SHORT).show();
    }
}