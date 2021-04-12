package com.app.smartepay.shop;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;

import com.app.smartepay.R;

public class BrandsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brands);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnAddBrand)
    public void onAddBrandClicked() {

    }
}