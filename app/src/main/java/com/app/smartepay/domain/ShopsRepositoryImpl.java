package com.app.smartepay.domain;

import com.app.smartepay.data.ShopsRepository;
import com.app.smartepay.model.Shop;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class ShopsRepositoryImpl implements ShopsRepository {

    private final FirebaseAuth auth;

    public ShopsRepositoryImpl() {
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void loginWithShopCredentials(String email, String password, MutableLiveData<Boolean> success) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> success.setValue(task.isSuccessful()));
    }

    @Override
    public void registerNewShop(Shop shop, MutableLiveData<Shop> shopLiveData) {
        auth.createUserWithEmailAndPassword(shop.getEmail(), shop.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            shop.setId(task.getResult().getUser().getUid());
                            shopLiveData.setValue(shop);
                        } else {
                            shopLiveData.setValue(null);
                        }
                    }
                });
    }

    @Override
    public void addNewShop(Shop shop, MutableLiveData<Boolean> success) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("shops");
        databaseReference.child(shop.getId()).setValue(shop).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                success.setValue(task.isSuccessful());
            }
        });
    }

    @Override
    public void retrieveShopById(String uId, MutableLiveData<Shop> shopLiveData) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("shops");
        databaseReference.child(uId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Shop shop = new Shop();
                shop.setId(snapshot.getKey());
                shop.setShopName(snapshot.child("shopName").getValue(String.class));
                shop.setEmail(snapshot.child("email").getValue(String.class));
                shopLiveData.setValue(shop);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                shopLiveData.setValue(null);
            }
        });
    }
}
