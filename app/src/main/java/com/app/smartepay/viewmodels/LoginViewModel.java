package com.app.smartepay.viewmodels;

import com.app.smartepay.Injection;
import com.app.smartepay.domain.usecase.LoginUseCase;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private final LoginUseCase loginUseCase;
    private MutableLiveData<Boolean> success = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public LoginViewModel() {
        loginUseCase = Injection.getLoginUseCase();
    }

    public void login(String email, String password) {
        if (validate(email, password)) {
            loginUseCase.execute(email, password, success);
        }
    }

    private boolean validate(String email, String password) {
        boolean isValidate = true;
        if (email == null || email.isEmpty()
                || password == null || password.isEmpty()) {
            error.setValue("You must enter email and password");
            isValidate = false;
        }
        return isValidate;
    }

    public MutableLiveData<Boolean> getSuccess() {
        return success;
    }

    public MutableLiveData<String> getError() {
        return error;
    }
}
