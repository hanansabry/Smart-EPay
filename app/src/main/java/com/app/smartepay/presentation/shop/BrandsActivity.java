package com.app.smartepay.presentation.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.app.smartepay.R;
import com.app.smartepay.model.Brand;
import com.app.smartepay.viewmodels.AddBrandViewModel;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

public class BrandsActivity extends AppCompatActivity implements IPickResult {

    @BindView(R.id.editTextBrandName)
    EditText editTextBrandName;
    @BindView(R.id.editTextDesc)
    EditText editTextDesc;
    @BindView(R.id.imageEditText)
    EditText imageEditText;
    private String imageUri;
    private AddBrandViewModel addBrandViewModel;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brands);
        ButterKnife.bind(this);

        addBrandViewModel = new ViewModelProvider(this).get(AddBrandViewModel.class);
        addBrandViewModel.getSuccess().observe(this, success -> {
            if (success) {
                finish();
                Toast.makeText(BrandsActivity.this, "Brand is added successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(BrandsActivity.this, "Error while adding brand, please try again later", Toast.LENGTH_SHORT).show();
            }
            progressDialog.hide();
        });
    }

    @OnClick(R.id.btnAddBrand)
    public void onAddBrandClicked() {
        String brandName = editTextBrandName.getText().toString();
        String brandDesc = editTextDesc.getText().toString();
        if (brandName.isEmpty() || brandDesc.isEmpty() || imageUri == null) {
            Toast.makeText(this, "You must enter all values", Toast.LENGTH_SHORT).show();
        } else {
            Brand brand = new Brand(brandName, brandDesc, imageUri);
            addBrandViewModel.addNewBrand(brand);
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

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }
}