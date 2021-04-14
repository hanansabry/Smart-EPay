package com.app.smartepay.data;

import com.app.smartepay.model.Shop;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public interface ShopsRepository {

    void loginWithShopCredentials(String email, String password, MutableLiveData<Boolean> success);
    void registerNewShop(Shop shop, MutableLiveData<Shop> shopLiveData);
    void addNewShop(Shop shop, MutableLiveData<Boolean> success);
    void retrieveShopById(String uId, MutableLiveData<Shop> shopLiveData);
}
