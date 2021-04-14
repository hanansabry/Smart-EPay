package com.app.smartepay.presentation.shop;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.app.smartepay.Constants;
import com.app.smartepay.R;
import com.app.smartepay.model.Item;
import com.app.smartepay.viewmodels.AddItemViewModel;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddItemActivity extends AppCompatActivity implements IPickResult {

    @BindView(R.id.editTextItemName)
    EditText editTextItemName;
    @BindView(R.id.editTextDesc)
    EditText editTextDesc;
    @BindView(R.id.editTextPrice)
    EditText editTextPrice;
    @BindView(R.id.imageEditText)
    EditText imageEditText;
    private String imageUri;
    private String brand;
    private String category;
    private AddItemViewModel addItemViewModel;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        ButterKnife.bind(this);

        brand = getIntent().getStringExtra(Constants.BRAND);
        category = getIntent().getStringExtra(Constants.CATEGORY);

        addItemViewModel = new ViewModelProvider(this).get(AddItemViewModel.class);
        addItemViewModel.getSuccess().observe(this, success -> {
            if (success) {
                finish();
                Toast.makeText(AddItemActivity.this, "Item is added successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AddItemActivity.this, "Error while adding brand, please try again later", Toast.LENGTH_SHORT).show();
            }
            progressDialog.hide();
        });
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }

    @OnClick(R.id.btnAddItem)
    public void onAddItemClicked() {
        String itemName = editTextItemName.getText().toString();
        String itemDesc = editTextDesc.getText().toString();
        double itemPrice = editTextPrice.getText().toString().isEmpty() ? 0 : Double.parseDouble(editTextPrice.getText().toString());
        if (itemName.isEmpty() || itemDesc.isEmpty() || itemPrice == 0 || imageUri == null) {
            Toast.makeText(this, "You must enter all values", Toast.LENGTH_SHORT).show();
        } else {
            Item item = new Item(itemName, itemDesc, itemPrice, imageUri, brand, category);
            addItemViewModel.addNewItem(item);
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    @OnClick(R.id.imageEditText)
    public void onSelectImageClicked() {
        PickImageDialog.build(new PickSetup()).show(this);
    }

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            imageEditText.setText(r.getPath());
            imageUri = r.getUri().toString();
        } else {
            //Handle possible errors
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}