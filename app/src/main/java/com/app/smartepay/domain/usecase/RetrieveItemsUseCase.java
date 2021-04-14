package com.app.smartepay.domain.usecase;

import com.app.smartepay.data.ItemsRepository;
import com.app.smartepay.model.Item;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class RetrieveItemsUseCase {

    private final ItemsRepository itemsRepository;

    public RetrieveItemsUseCase(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public void execute(MutableLiveData<List<Item>> itemsListLiveData) {
        itemsRepository.retrieveAllItems(itemsListLiveData);
    }

    public void executeByBrandId(String brandId, MutableLiveData<List<Item>> itemsListLiveData) {
        itemsRepository.retrieveItemsByBrands(brandId, itemsListLiveData);
    }
}
