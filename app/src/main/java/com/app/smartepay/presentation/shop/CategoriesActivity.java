package com.app.smartepay.presentation.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.app.smartepay.R;
import com.app.smartepay.model.Category;
import com.app.smartepay.viewmodels.AddCategoryViewModel;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

public class CategoriesActivity extends AppCompatActivity implements IPickResult {

    @BindView(R.id.editTextCategoryName)
    EditText editTextCategoryName;
    @BindView(R.id.editTextDesc)
    EditText editTextDesc;
    @BindView(R.id.imageEditText)
    EditText imageEditText;
    private String imageUri;
    private AddCategoryViewModel addCategoryViewModel;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ButterKnife.bind(this);

        addCategoryViewModel = new ViewModelProvider(this).get(AddCategoryViewModel.class);
        addCategoryViewModel.getSuccess().observe(this, success -> {
            if (success) {
                finish();
                Toast.makeText(CategoriesActivity.this, "Category is added successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(CategoriesActivity.this, "Error while adding category, please try again later", Toast.LENGTH_SHORT).show();
            }
            progressDialog.hide();
        });
    }

    @OnClick(R.id.btnAddCategory)
    public void onAddCategoryClicked() {
        String categoryName = editTextCategoryName.getText().toString();
        String categoryDesc = editTextDesc.getText().toString();
        if (categoryName.isEmpty() || categoryDesc.isEmpty() || imageUri == null) {
            Toast.makeText(this, "You must enter all values", Toast.LENGTH_SHORT).show();
        } else {
            Category category = new Category(categoryName, categoryDesc, imageUri);
            addCategoryViewModel.addNewCategory(category);
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