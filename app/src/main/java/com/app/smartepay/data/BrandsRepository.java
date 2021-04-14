package com.app.smartepay.data;

import com.app.smartepay.model.Brand;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public interface BrandsRepository {

    void addNewBrand(Brand brand, MutableLiveData<Boolean> success);
    void retrieveAllBrands(MutableLiveData<List<Brand>> brandListLiveData);
}
