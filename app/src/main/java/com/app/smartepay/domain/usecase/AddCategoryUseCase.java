package com.app.smartepay.domain.usecase;

import com.app.smartepay.data.CategoriesRepository;
import com.app.smartepay.model.Category;

import androidx.lifecycle.MutableLiveData;

public class AddCategoryUseCase {

    private final CategoriesRepository categoriesRepository;

    public AddCategoryUseCase(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public void execute(Category category, MutableLiveData<Boolean> success) {
        categoriesRepository.addNewCategory(category, success);
    }
}
