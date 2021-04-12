package com.app.smartepay;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;

import com.app.smartepay.guest.MainGuestActivity;
import com.app.smartepay.shop.ShopLoginActivity;
import com.app.smartepay.shop.ShopRegisterActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }

    @OnClick(R.id.btnGuest)
    public void onGuestClicked() {
        startActivity(new Intent(this, MainGuestActivity.class));
    }

    @OnClick(R.id.btnShop)
    public void onShopClicked() {
        startActivity(new Intent(this, ShopLoginActivity.class));
    }

    @OnClick(R.id.btnRegister)
    public void onRegisterClicked() {
        startActivity(new Intent(this, ShopRegisterActivity.class));
    }

}