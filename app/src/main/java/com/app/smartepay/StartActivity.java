package com.app.smartepay;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;

import com.app.smartepay.presentation.guest.MainGuestActivity;
import com.app.smartepay.presentation.shop.MainShopActivity;
import com.app.smartepay.presentation.shop.ShopLoginActivity;
import com.app.smartepay.presentation.shop.ShopRegisterActivity;
import com.google.firebase.auth.FirebaseAuth;


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
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(this, ShopLoginActivity.class));
        } else {
            startActivity(new Intent(this, MainShopActivity.class));
        }
    }

    @OnClick(R.id.btnRegister)
    public void onRegisterClicked() {
        startActivity(new Intent(this, ShopRegisterActivity.class));
    }

}