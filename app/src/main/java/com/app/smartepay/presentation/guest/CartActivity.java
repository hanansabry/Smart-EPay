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
import android.widget.TextView;
import android.widget.Toast;

import com.app.smartepay.Constants;
import com.app.smartepay.R;
import com.app.smartepay.model.Invoice;
import com.app.smartepay.model.InvoiceItem;
import com.app.smartepay.presentation.adapters.InvoiceItemsAdapter;
import com.app.smartepay.viewmodels.AddOrderViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class CartActivity extends AppCompatActivity {

    private ArrayList<InvoiceItem> invoiceItems;
    private Invoice newInvoice;
    private AddOrderViewModel addOrderViewModel;

    @BindView(R.id.itemsRecyclerView)
    RecyclerView itemsRecyclerView;
    @BindView(R.id.orderIdTextView)
    TextView orderIdTextView;
    @BindView(R.id.dateTextView)
    TextView dateTextView;
    @BindView(R.id.marketNameTextView)
    TextView marketNameTextView;
    @BindView(R.id.totalPriceTextView)
    TextView totalPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        invoiceItems = intent.getParcelableArrayListExtra(Constants.INVOICE_ITEMS);
        String brandName = intent.getStringExtra(Constants.BRAND_NAME);
        String orderId = UUID.randomUUID().toString();
        String date = getCurrentDate();

        orderIdTextView.setText(orderId);
        dateTextView.setText(date);
        marketNameTextView.setText(brandName);
        totalPriceTextView.setText(getTotalPrice()+"");

        newInvoice = new Invoice();
        newInvoice.setOrderId(orderId);
        newInvoice.setDate(date);
        newInvoice.setMarketName(brandName);
        newInvoice.setInvoiceItems(invoiceItems);
        newInvoice.setTotal(getTotalPrice());
        newInvoice.setQrCode(generateQrCode());

        setupItemsRecyclerView();

        addOrderViewModel = new ViewModelProvider(this).get(AddOrderViewModel.class);
        addOrderViewModel.getSuccess().observe(this, success -> {
            if (success) {
                Intent intent1 = new Intent(CartActivity.this, QRCodeGenertorActivity.class);
                intent1.putExtra(Constants.ORDER_ID, newInvoice.getOrderId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
            } else {
                Toast.makeText(this, "Can't complete process, Please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupItemsRecyclerView() {
        InvoiceItemsAdapter invoiceItemsAdapter = new InvoiceItemsAdapter(this, invoiceItems);
        itemsRecyclerView.setAdapter(invoiceItemsAdapter);
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private String generateQrCode() {
        return null;
    }

    private double getTotalPrice() {
        double total = 0;
        for (InvoiceItem invoiceItem : invoiceItems) {
            total += invoiceItem.getTotalPrice();
        }
        return total;
    }

    private String getCurrentDate() {
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        return df.format(c);
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }

    @OnClick(R.id.btnFinish)
    public void onFinishClicked() {
        addOrderViewModel.addNewOrder(newInvoice);
    }
}