package com.app.smartepay.domain.usecase;

import com.app.smartepay.data.ShopsRepository;
import com.app.smartepay.model.Shop;

import androidx.lifecycle.MutableLiveData;

public class AddShopUseCase {

    private final ShopsRepository shopsRepository;

    public AddShopUseCase(ShopsRepository shopsRepository) {
        this.shopsRepository = shopsRepository;
    }

    public void execute(Shop shop, MutableLiveData<Boolean> success) {
        shopsRepository.addNewShop(shop, success);
    }
}
