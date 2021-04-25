package com.app.smartepay.presentation.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.app.smartepay.Constants;
import com.app.smartepay.R;
import com.app.smartepay.model.Brand;
import com.app.smartepay.model.Category;
import com.app.smartepay.model.Item;
import com.app.smartepay.presentation.adapters.ItemsAdapter;
import com.app.smartepay.viewmodels.RetrieveBrandsViewModel;
import com.app.smartepay.viewmodels.RetrieveCategoriesViewModel;
import com.app.smartepay.viewmodels.RetrieveItemsViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ItemsActivity extends AppCompatActivity {

    @BindView(R.id.brandsSpinner)
    AppCompatSpinner brandsSpinner;
    @BindView(R.id.categoriesSpinner)
    AppCompatSpinner categoriesSpinner;
    @BindView(R.id.itemsRecyclerView)
    RecyclerView itemsRecyclerView;
    private Brand selectedBrand;
    private Category selectedCategory;
    private RetrieveItemsViewModel retrieveItemsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        ButterKnife.bind(this);

        //get current shop
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
//            String uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//            GetShopViewModel getShopViewModel = new ViewModelProvider(this).get(GetShopViewModel.class);
//            getShopViewModel.getShopById(uId);
//            getShopViewModel.getShopLiveData().observe(this, shop -> {
//                initiateBrandsSpinner(shop.getBrands());
//                initiateCategoriesSpinner(shop.getCategories());
//            });

            RetrieveBrandsViewModel retrieveBrandsViewModel = new ViewModelProvider(this).get(RetrieveBrandsViewModel.class);
            retrieveBrandsViewModel.retrieveBrands();
            retrieveBrandsViewModel.getBrandsListLiveData().observe(this, this::initiateBrandsSpinner);

            RetrieveCategoriesViewModel retrieveCategoriesViewModel = new ViewModelProvider(this).get(RetrieveCategoriesViewModel.class);
            retrieveCategoriesViewModel.retrieveCategories();
            retrieveCategoriesViewModel.getCategoriesListLiveData().observe(this, this::initiateCategoriesSpinner);

            retrieveItemsViewModel = new ViewModelProvider(this).get(RetrieveItemsViewModel.class);
            retrieveItemsViewModel.retrieveItems();
            retrieveItemsViewModel.getItemsListLiveData().observe(this, this::initiateItemsRecyclerView);
        } else {
            finish();
            startActivity(new Intent(this, ShopLoginActivity.class));
        }
    }

    private void initiateItemsRecyclerView(List<Item> items) {
        ItemsAdapter itemsAdapter = new ItemsAdapter(items);
        itemsRecyclerView.setAdapter(itemsAdapter);
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initiateBrandsSpinner(List<Brand> brands) {
        ArrayAdapter<String> brandsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        ArrayList<String> brandsList = new ArrayList<>();
        for (Brand brand : brands) {
            brandsList.add(brand.getName());
        }
        brandsAdapter.addAll(brandsList);
        brandsSpinner.setAdapter(brandsAdapter);
        brandsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedBrand = brands.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initiateCategoriesSpinner(List<Category> categories) {
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        ArrayList<String> categoriesList = new ArrayList<>();
        for (Category category : categories) {
            categoriesList.add(category.getName());
        }
        categoriesAdapter.addAll(categoriesList);
        categoriesSpinner.setAdapter(categoriesAdapter);
        categoriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = categories.get(position);
                retrieveItemsViewModel.retrieveItems();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick(R.id.addItemFab)
    public void onAddItemFabClicked() {
        if (selectedBrand != null && selectedCategory != null ) {
            Intent intent = new Intent(this, AddItemActivity.class);
            intent.putExtra(Constants.BRAND, selectedBrand.getId());
            intent.putExtra(Constants.CATEGORY, selectedCategory.getName());
            startActivity(intent);
        } else {
            Toast.makeText(this, "You must select brand and category to add new item", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }
}