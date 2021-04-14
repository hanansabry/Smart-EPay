package com.app.smartepay.domain.usecase;

import com.app.smartepay.data.ItemsRepository;
import com.app.smartepay.model.Item;

import androidx.lifecycle.MutableLiveData;

public class AddItemUseCase {

    private final ItemsRepository itemsRepository;

    public AddItemUseCase(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public void execute(Item item, MutableLiveData<Boolean> success) {
        itemsRepository.addNewItem(item, success);
    }
}
