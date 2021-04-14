package com.app.smartepay.data;

import com.app.smartepay.model.Invoice;

import androidx.lifecycle.MutableLiveData;

public interface InvoiceRepository {

    void addNewOrder(Invoice invoice, MutableLiveData<Boolean> success);
}
