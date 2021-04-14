package com.app.smartepay.viewmodels;

import com.app.smartepay.Injection;
import com.app.smartepay.domain.usecase.RetrieveItemsUseCase;
import com.app.smartepay.model.Item;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RetrieveItemsViewModel extends ViewModel {

    private final RetrieveItemsUseCase retrieveItemsUseCase;
    private MutableLiveData<List<Item>> itemsListLiveData = new MutableLiveData<>();

    public RetrieveItemsViewModel() {
        retrieveItemsUseCase = Injection.getRetrieveItemsUseCase();
    }

    public void retrieveItems() {
        retrieveItemsUseCase.execute(itemsListLiveData);
    }

    public void retrieveItemsByBrand(String brandId) {
        retrieveItemsUseCase.executeByBrandId(brandId, itemsListLiveData);
    }

    public MutableLiveData<List<Item>> getItemsListLiveData() {
        return itemsListLiveData;
    }
}
