package com.app.smartepay.presentation.guest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;

import com.app.smartepay.Constants;
import com.app.smartepay.R;
import com.app.smartepay.model.Brand;
import com.app.smartepay.presentation.adapters.BrandsAdapter;
import com.app.smartepay.viewmodels.RetrieveBrandsViewModel;
import com.google.android.gms.vision.text.Line;

import java.util.List;

public class MainGuestActivity extends AppCompatActivity implements BrandsAdapter.BrandsListener {

    @BindView(R.id.brandsRecyclerView)
    RecyclerView brandsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_guest);
        ButterKnife.bind(this);

        RetrieveBrandsViewModel retrieveBrandsViewModel = new ViewModelProvider(this).get(RetrieveBrandsViewModel.class);
        retrieveBrandsViewModel.retrieveBrands();
        retrieveBrandsViewModel.getBrandsListLiveData().observe(this, this::setupBrandsRecyclerView);
    }

    private void setupBrandsRecyclerView(List<Brand> brands) {
        BrandsAdapter brandsAdapter = new BrandsAdapter(this, brands, this);
        brandsRecyclerView.setAdapter(brandsAdapter);
        brandsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }

    @OnClick(R.id.brandsRecyclerView)
    public void onBrandsClicked() {
        startActivity(new Intent(this, MarketItemsActivity.class));
    }

    @Override
    public void onBrandClicked(Brand brand) {
        Intent intent = new Intent(this, MarketItemsActivity.class);
        intent.putExtra(Constants.BRAND, brand.getId());
        intent.putExtra(Constants.BRAND_NAME, brand.getName());
        startActivity(intent);
    }
}