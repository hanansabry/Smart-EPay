package com.app.smartepay.domain;

import com.app.smartepay.data.InvoiceRepository;
import com.app.smartepay.model.Invoice;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class InvoiceRepositoryImpl implements InvoiceRepository {

    private final FirebaseDatabase mDatabase;

    public InvoiceRepositoryImpl() {
        mDatabase = FirebaseDatabase.getInstance();
    }

    @Override
    public void addNewOrder(Invoice invoice, MutableLiveData<Boolean> success) {
        mDatabase.getReference("orders").push().setValue(invoice).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                success.setValue(task.isSuccessful());
            }
        });
    }
}
