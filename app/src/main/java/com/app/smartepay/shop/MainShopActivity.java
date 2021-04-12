package com.app.smartepay.shop;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;

import com.app.smartepay.R;

public class MainShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shop);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.brandsView)
    public void onBrandsClicked() {
        startActivity(new Intent(this, BrandsActivity.class));
    }

    @OnClick(R.id.categoriesView)
    public void onCategoriesClicked() {
        startActivity(new Intent(this, CategoriesActivity.class));
    }

    @OnClick(R.id.itemsView)
    public void onItemsClicked() {
        startActivity(new Intent(this, ItemsActivity.class));
    }
}