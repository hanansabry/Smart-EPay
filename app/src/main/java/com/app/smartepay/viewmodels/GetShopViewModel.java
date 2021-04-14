package com.app.smartepay.viewmodels;

import com.app.smartepay.Injection;
import com.app.smartepay.domain.usecase.GetShopUseCase;
import com.app.smartepay.model.Shop;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GetShopViewModel extends ViewModel {

    private final GetShopUseCase getShopUseCase;
    private MutableLiveData<Shop> shopLiveData = new MutableLiveData<>();

    public GetShopViewModel() {
        getShopUseCase = Injection.getShopUseCase();
    }

    public void getShopById(String uId) {
        getShopUseCase.execute(uId, shopLiveData);
    }

    public MutableLiveData<Shop> getShopLiveData() {
        return shopLiveData;
    }
}
