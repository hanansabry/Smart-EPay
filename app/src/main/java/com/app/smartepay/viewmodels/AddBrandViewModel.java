package com.app.smartepay.viewmodels;

import com.app.smartepay.Injection;
import com.app.smartepay.domain.usecase.AddBrandUseCase;
import com.app.smartepay.model.Brand;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddBrandViewModel extends ViewModel {

    private final AddBrandUseCase addBrandUseCase;
    private MutableLiveData<Boolean> success = new MutableLiveData<>();

    public AddBrandViewModel() {
        addBrandUseCase = Injection.getAddBrandUseCase();
    }

    public void addNewBrand(Brand brand) {
        addBrandUseCase.execute(brand, success);
    }

    public MutableLiveData<Boolean> getSuccess() {
        return success;
    }
}
