package com.app.smartepay.data;

import com.app.smartepay.model.Item;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public interface ItemsRepository {

    void addNewItem(Item item, MutableLiveData<Boolean> success);
    void retrieveAllItems(MutableLiveData<List<Item>> itemsListLiveData);
    void retrieveItemsByBrands(String brandId, MutableLiveData<List<Item>> itemsListLiveData);
}
