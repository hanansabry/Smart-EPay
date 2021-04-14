package com.app.smartepay.domain.usecase;

import com.app.smartepay.data.ShopsRepository;
import com.app.smartepay.model.Shop;

import androidx.lifecycle.MutableLiveData;

public class RegisterUseCase {

    private final ShopsRepository shopsRepository;

    public RegisterUseCase(ShopsRepository shopsRepository) {
        this.shopsRepository = shopsRepository;
    }

    public void execute(Shop shop, MutableLiveData<Shop> shopLiveData) {
        shopsRepository.registerNewShop(shop, shopLiveData);
    }
}
