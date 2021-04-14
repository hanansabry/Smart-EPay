package com.app.smartepay.domain;

import android.net.Uri;

import com.app.smartepay.data.CategoriesRepository;
import com.app.smartepay.model.Brand;
import com.app.smartepay.model.Category;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class CategoriesRepositoryImpl implements CategoriesRepository {

    private final FirebaseDatabase mDatabase;

    public CategoriesRepositoryImpl() {
        mDatabase = FirebaseDatabase.getInstance();
    }

    @Override
    public void addNewCategory(Category category, MutableLiveData<Boolean> success) {
        DatabaseReference categoryRef = mDatabase.getReference("categories")
                .push();

        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
        Uri file = Uri.parse(category.getImageUrl());
        StorageReference storageReference = mStorageRef.child("categories/" + category.getName() + "/" + UUID.randomUUID().toString());
        storageReference.putFile(file)
                .addOnSuccessListener(taskSnapshot -> {
                    storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
                        category.setImageUrl(uri.toString());
                        categoryRef.setValue(category)
                                .addOnCompleteListener(task -> { success.setValue(task.isSuccessful());});
                    });
                })
                .addOnFailureListener(e -> {
                    success.setValue(false);
                });
    }

    @Override
    public void retrieveAllCategories(MutableLiveData<List<Category>> categoryListLiveData) {
        mDatabase.getReference("categories")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<Category> categories = new ArrayList<>();
                        for (DataSnapshot categorySnapshot : snapshot.getChildren()) {
                            Category category = categorySnapshot.getValue(Category.class);
                            category.setId(categorySnapshot.getKey());
                            categories.add(category);
                        }
                        categoryListLiveData.setValue(categories);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        categoryListLiveData.setValue(null);
                    }
                });
    }
}
