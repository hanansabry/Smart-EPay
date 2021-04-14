package com.app.smartepay.domain.usecase;

import com.app.smartepay.data.BrandsRepository;
import com.app.smartepay.model.Brand;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class RetrieveBrandsUseCase {

    private final BrandsRepository brandsRepository;

    public RetrieveBrandsUseCase(BrandsRepository brandsRepository) {
        this.brandsRepository = brandsRepository;
    }

    public void execute(MutableLiveData<List<Brand>> brandListLiveData) {
        brandsRepository.retrieveAllBrands(brandListLiveData);
    }
}
