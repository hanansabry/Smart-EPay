package com.app.smartepay.domain;

import android.net.Uri;

import com.app.smartepay.data.ItemsRepository;
import com.app.smartepay.model.Category;
import com.app.smartepay.model.Item;
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

public class ItemsRepositoryImpl implements ItemsRepository {

    private final FirebaseDatabase mDatabase;
    private String uId;

    public ItemsRepositoryImpl() {
        mDatabase = FirebaseDatabase.getInstance();
    }

    @Override
    public void addNewItem(Item item, MutableLiveData<Boolean> success) {
        DatabaseReference itemRef = mDatabase.getReference("shops")
                .child(uId)
                .child("items")
                .push();

        DatabaseReference itemBrandRef = mDatabase.getReference("brands")
                .child(item.getBrand())
                .child("items")
                .push();

        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
        Uri file = Uri.parse(item.getImageUrl());
        StorageReference storageReference = mStorageRef.child(uId + "/items/" + item.getName() + "/" + UUID.randomUUID().toString());
        storageReference.putFile(file)
                .addOnSuccessListener(taskSnapshot -> {
                    storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
                        item.setImageUrl(uri.toString());
                        itemRef.setValue(item)
                                .addOnCompleteListener(task -> { success.setValue(task.isSuccessful());});
                        itemBrandRef.setValue(item);
                    });
                })
                .addOnFailureListener(e -> {
                    success.setValue(false);
                });
    }

    @Override
    public void retrieveAllItems(MutableLiveData<List<Item>> itemsListLiveData) {
        uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabase.getReference("shops")
                .child(uId)
                .child("items")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<Item> items = new ArrayList<>();
                        for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                            Item item = itemSnapshot.getValue(Item.class);
                            items.add(item);
                        }
                        itemsListLiveData.setValue(items);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        itemsListLiveData.setValue(null);
                    }
                });
    }

    @Override
    public void retrieveItemsByBrands(String brandId, MutableLiveData<List<Item>> itemsListLiveData) {
        mDatabase.getReference("brands")
                .child(brandId)
                .child("items")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<Item> items = new ArrayList<>();
                        for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                            Item item = itemSnapshot.getValue(Item.class);
                            item.setId(itemSnapshot.getKey());
                            items.add(item);
                        }
                        itemsListLiveData.setValue(items);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        itemsListLiveData.setValue(null);
                    }
                });
    }


}
