package com.app.smartepay.domain.usecase;

import com.app.smartepay.data.ShopsRepository;

import androidx.lifecycle.MutableLiveData;

public class LoginUseCase {

    private final ShopsRepository shopsRepository;

    public LoginUseCase(ShopsRepository shopsRepository) {
        this.shopsRepository = shopsRepository;
    }

    public void execute(String email, String password, MutableLiveData<Boolean> success) {
        shopsRepository.loginWithShopCredentials(email, password, success);
    }
}
