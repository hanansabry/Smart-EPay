package com.app.smartepay.data;

import com.app.smartepay.model.Category;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public interface CategoriesRepository {

    void addNewCategory(Category category, MutableLiveData<Boolean> success);
    void retrieveAllCategories(MutableLiveData<List<Category>> categoryListLiveData);
}
