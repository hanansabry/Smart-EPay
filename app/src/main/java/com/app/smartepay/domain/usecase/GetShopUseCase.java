package com.app.smartepay.domain.usecase;

import com.app.smartepay.data.ShopsRepository;
import com.app.smartepay.model.Shop;

import androidx.lifecycle.MutableLiveData;

public class GetShopUseCase {

    private final ShopsRepository shopsRepository;

    public GetShopUseCase(ShopsRepository shopsRepository) {
        this.shopsRepository = shopsRepository;
    }

    public void execute(String uId, MutableLiveData<Shop> shopLiveData) {
        shopsRepository.retrieveShopById(uId, shopLiveData);
    }
}
