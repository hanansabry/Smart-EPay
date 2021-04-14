package com.app.smartepay.domain.usecase;

import com.app.smartepay.data.InvoiceRepository;
import com.app.smartepay.model.Invoice;

import androidx.lifecycle.MutableLiveData;

public class AddOrderUseCase {

    private final InvoiceRepository invoiceRepository;

    public AddOrderUseCase(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public void execute(Invoice invoice, MutableLiveData<Boolean> success) {
        invoiceRepository.addNewOrder(invoice, success);
    }
}
