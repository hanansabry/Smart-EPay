package com.app.smartepay.viewmodels;

import com.app.smartepay.Injection;
import com.app.smartepay.domain.usecase.RetrieveCategoriesUseCase;
import com.app.smartepay.model.Category;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RetrieveCategoriesViewModel extends ViewModel {

    private final RetrieveCategoriesUseCase retrieveCategoriesUseCase;
    private MutableLiveData<List<Category>> categoriesListLiveData = new MutableLiveData<>();

    public RetrieveCategoriesViewModel() {
        retrieveCategoriesUseCase = Injection.getRetrieveCategoriesUseCase();
    }

    public void retrieveCategories() {
        retrieveCategoriesUseCase.execute(categoriesListLiveData);
    }

    public MutableLiveData<List<Category>> getCategoriesListLiveData() {
        return categoriesListLiveData;
    }
}
