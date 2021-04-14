package com.app.smartepay.domain;

import android.net.Uri;

import com.app.smartepay.data.BrandsRepository;
import com.app.smartepay.model.Brand;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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

public class BrandsRepositoryImpl implements BrandsRepository {

    private final FirebaseDatabase mDatabase;

    public BrandsRepositoryImpl() {
        mDatabase = FirebaseDatabase.getInstance();
    }

    @Override
    public void addNewBrand(Brand brand, MutableLiveData<Boolean> success) {
        DatabaseReference brandRef = mDatabase.getReference("brands")
                .push();

        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
        Uri file = Uri.parse(brand.getImageUrl());
        StorageReference storageReference = mStorageRef.child("brands/" + brand.getName() + "/" + UUID.randomUUID().toString());
        storageReference.putFile(file)
                .addOnSuccessListener(taskSnapshot -> {
                    storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
                        brand.setImageUrl(uri.toString());
                        brandRef.setValue(brand)
                                .addOnCompleteListener(task -> { success.setValue(task.isSuccessful());});
                    });
                })
                .addOnFailureListener(e -> {
                    success.setValue(false);
                });
    }

    @Override
    public void retrieveAllBrands(MutableLiveData<List<Brand>> brandListLiveData) {
        mDatabase.getReference("brands")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<Brand> brands = new ArrayList<>();
                        for (DataSnapshot brandSnapshot : snapshot.getChildren()) {
                            Brand brand = new Brand();
                            brand.setId(brandSnapshot.getKey());
                            brand.setName(brandSnapshot.child("name").getValue(String.class));
                            brand.setDesc(brandSnapshot.child("desc").getValue(String.class));
                            brand.setImageUrl(brandSnapshot.child("imageUrl").getValue(String.class));
                            brands.add(brand);
                        }
                        brandListLiveData.setValue(brands);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        brandListLiveData.setValue(null);
                    }
                });
    }
}
