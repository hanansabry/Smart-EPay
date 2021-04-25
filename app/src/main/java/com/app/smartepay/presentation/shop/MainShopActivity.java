package com.app.smartepay.presentation.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.app.smartepay.R;
import com.app.smartepay.StartActivity;
import com.app.smartepay.model.Shop;
import com.app.smartepay.viewmodels.GetShopViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class MainShopActivity extends AppCompatActivity {

    @BindView(R.id.shopNameTextView)
    TextView shopNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shop);
        ButterKnife.bind(this);

        //get current shop
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            GetShopViewModel getShopViewModel = new ViewModelProvider(this).get(GetShopViewModel.class);
            getShopViewModel.getShopById(uid);
            getShopViewModel.getShopLiveData().observe(this, shop -> {
                if (shop != null) {
                    String shopName = shop.getShopName();
                    shopNameTextView.setText(shopName);
                } else {
                    Toast.makeText(MainShopActivity.this, "Can't retrieve shop", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            finish();
            startActivity(new Intent(this, ShopLoginActivity.class));
        }
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

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();;
    }

    @OnClick(R.id.btnLogout)
    public void onLogoutClicked() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, StartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}