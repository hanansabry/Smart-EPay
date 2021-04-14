package com.app.smartepay.viewmodels;

import com.app.smartepay.Injection;
import com.app.smartepay.domain.usecase.RetrieveBrandsUseCase;
import com.app.smartepay.model.Brand;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RetrieveBrandsViewModel extends ViewModel {

    private final RetrieveBrandsUseCase retrieveBrandsUseCase;
    private MutableLiveData<List<Brand>> brandsListLiveData = new MutableLiveData<>();

    public RetrieveBrandsViewModel() {
        retrieveBrandsUseCase = Injection.getRetrieveBrandsUseCase();
    }

    public void retrieveBrands() {
        retrieveBrandsUseCase.execute(brandsListLiveData);
    }

    public MutableLiveData<List<Brand>> getBrandsListLiveData() {
        return brandsListLiveData;
    }
}
