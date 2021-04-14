package com.app.smartepay.domain.usecase;

import com.app.smartepay.data.BrandsRepository;
import com.app.smartepay.model.Brand;

import androidx.lifecycle.MutableLiveData;

public class AddBrandUseCase {

    private final BrandsRepository brandsRepository;

    public AddBrandUseCase(BrandsRepository brandsRepository) {
        this.brandsRepository = brandsRepository;
    }

    public void execute(Brand brand, MutableLiveData<Boolean> success) {
        brandsRepository.addNewBrand(brand, success);
    }
}
