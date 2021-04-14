package com.app.smartepay.viewmodels;

import com.app.smartepay.Injection;
import com.app.smartepay.domain.usecase.AddShopUseCase;
import com.app.smartepay.model.Shop;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddShopViewModel extends ViewModel {

    private final AddShopUseCase addShopUseCase;
    private MutableLiveData<Boolean> success = new MutableLiveData<>();

    public AddShopViewModel() {
        addShopUseCase = Injection.getAddShopUseCase();
    }

    public void addNewShop(Shop shop) {
        addShopUseCase.execute(shop, success);
    }

    public MutableLiveData<Boolean> getSuccess() {
        return success;
    }
}
