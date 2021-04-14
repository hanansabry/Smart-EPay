package com.app.smartepay.viewmodels;

import com.app.smartepay.Injection;
import com.app.smartepay.domain.usecase.AddOrderUseCase;
import com.app.smartepay.model.Invoice;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddOrderViewModel extends ViewModel {

    private final AddOrderUseCase addOrderUseCase;
    private MutableLiveData<Boolean> success = new MutableLiveData<>();

    public AddOrderViewModel() {
        addOrderUseCase = Injection.getAddOrderUseCase();
    }

    public void addNewOrder(Invoice invoice) {
        addOrderUseCase.execute(invoice, success);
    }

    public MutableLiveData<Boolean> getSuccess() {
        return success;
    }
}
