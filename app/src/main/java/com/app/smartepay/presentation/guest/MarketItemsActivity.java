package com.app.smartepay.presentation.guest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.app.smartepay.Constants;
import com.app.smartepay.R;
import com.app.smartepay.model.InvoiceItem;
import com.app.smartepay.model.Item;
import com.app.smartepay.presentation.adapters.CartItemsAdapter;
import com.app.smartepay.viewmodels.RetrieveItemsViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MarketItemsActivity extends AppCompatActivity implements CartItemsAdapter.CartCallback {

    @BindView(R.id.brandNameTextView)
    TextView brandNameTextView;
    @BindView(R.id.cartItemsNumberTextView)
    TextView cartItemsNumberTextView;
    @BindView(R.id.itemsRecyclerView)
    RecyclerView itemsRecyclerView;
    private String brandName;
    private ArrayList<InvoiceItem> invoiceItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_items);
        ButterKnife.bind(this);

        String brandId = getIntent().getStringExtra(Constants.BRAND);
        brandName = getIntent().getStringExtra(Constants.BRAND_NAME);

        brandNameTextView.setText(brandName);
        RetrieveItemsViewModel retrieveItemsViewModel = new ViewModelProvider(this).get(RetrieveItemsViewModel.class);
        retrieveItemsViewModel.retrieveItemsByBrand(brandId);
        retrieveItemsViewModel.getItemsListLiveData().observe(this, this::setupItemsRecyclerView);
    }

    private void setupItemsRecyclerView(List<Item> items) {
        CartItemsAdapter cartItemsAdapter = new CartItemsAdapter(this, items, this);
        itemsRecyclerView.setAdapter(cartItemsAdapter);
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }

    @OnClick(R.id.btnCheckout)
    public void onCheckoutClicked() {
        if (invoiceItems.isEmpty()) {
            Toast.makeText(this, "You must add items to the cart", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, CartActivity.class);
            intent.putParcelableArrayListExtra(Constants.INVOICE_ITEMS, invoiceItems);
            intent.putExtra(Constants.BRAND_NAME, brandName);
            startActivity(intent);
        }
    }

    @Override
    public void onAddToCartClicked(Item item, int quantity) {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setItem(item);
        invoiceItem.setQuantity(quantity);
        invoiceItem.setTotalPrice(item.getPrice() * quantity);

        invoiceItems.add(invoiceItem);
        cartItemsNumberTextView.setText(getTotalCartItemsCount(invoiceItems));
        Toast.makeText(this, "Item is added successfully", Toast.LENGTH_SHORT).show();
    }

    private InvoiceItem checkExistItem(InvoiceItem item, List<InvoiceItem> invoiceItems) {
        boolean found = false;
        for (InvoiceItem invoiceItem : invoiceItems) {
            if (invoiceItem.getItem().getId().equals(item.getItem().getId())) {
                int totalQuantity = invoiceItem.getQuantity() + item.getQuantity();
                item.setQuantity(totalQuantity);
                item.setTotalPrice(totalQuantity * item.getItem().getPrice());
                found = true;
                break;
            }
        }
        if (found) {
            return item;
        } else {
            return null;
        }
    }

    private String getTotalCartItemsCount(List<InvoiceItem> invoiceItems) {
        int count = 0;
        for (InvoiceItem invoiceItem : invoiceItems) {
            count += invoiceItem.getQuantity();
        }
        return count + "";
    }
}