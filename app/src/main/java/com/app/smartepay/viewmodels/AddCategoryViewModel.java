package com.app.smartepay.viewmodels;

import com.app.smartepay.Injection;
import com.app.smartepay.domain.usecase.AddCategoryUseCase;
import com.app.smartepay.model.Category;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddCategoryViewModel extends ViewModel {

    private final AddCategoryUseCase addCategoryUseCase;
    private MutableLiveData<Boolean> success = new MutableLiveData<>();

    public AddCategoryViewModel() {
        addCategoryUseCase = Injection.getAddCategoryUseCase();
    }

    public void addNewCategory(Category category) {
        addCategoryUseCase.execute(category, success);
    }

    public MutableLiveData<Boolean> getSuccess() {
        return success;
    }
}
