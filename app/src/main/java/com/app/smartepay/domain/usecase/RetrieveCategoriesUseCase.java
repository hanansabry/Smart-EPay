package com.app.smartepay.domain.usecase;

import com.app.smartepay.data.CategoriesRepository;
import com.app.smartepay.model.Category;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class RetrieveCategoriesUseCase {

    private final CategoriesRepository categoriesRepository;

    public RetrieveCategoriesUseCase(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public void execute(MutableLiveData<List<Category>> categoryListLiveData) {
        categoriesRepository.retrieveAllCategories(categoryListLiveData);
    }
}
