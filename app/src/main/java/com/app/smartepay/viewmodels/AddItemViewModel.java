package com.app.smartepay.viewmodels;

import com.app.smartepay.Injection;
import com.app.smartepay.domain.usecase.AddItemUseCase;
import com.app.smartepay.model.Item;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddItemViewModel extends ViewModel {

    private final AddItemUseCase addItemUseCase;
    private MutableLiveData<Boolean> success = new MutableLiveData<>();

    public AddItemViewModel() {
        addItemUseCase = Injection.getAddItemUseCase();
    }

    public void addNewItem(Item item) {
        addItemUseCase.execute(item, success);
    }

    public MutableLiveData<Boolean> getSuccess() {
        return success;
    }
}
