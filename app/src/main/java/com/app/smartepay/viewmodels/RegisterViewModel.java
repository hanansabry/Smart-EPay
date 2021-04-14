package com.app.smartepay.viewmodels;

import com.app.smartepay.Injection;
import com.app.smartepay.domain.usecase.AddShopUseCase;
import com.app.smartepay.domain.usecase.RegisterUseCase;
import com.app.smartepay.model.Shop;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterViewModel extends ViewModel {

    private final RegisterUseCase registerUseCase;
    private final AddShopUseCase addShopUseCase;
    private MutableLiveData<Boolean> addShopSuccess = new MutableLiveData<>();
    private MutableLiveData<Shop> shopLiveData = new MutableLiveData<>();
    private MutableLiveData<String> emailError = new MutableLiveData<>();
    private MutableLiveData<String> passwordError = new MutableLiveData<>();
    private MutableLiveData<String> confirmPasswordError = new MutableLiveData<>();
    private MutableLiveData<String> shopNameError = new MutableLiveData<>();

    public RegisterViewModel() {
        registerUseCase = Injection.getRegisterUseCase();
        addShopUseCase = Injection.getAddShopUseCase();
    }

    public void addNewShop(Shop shop) {
        addShopUseCase.execute(shop, addShopSuccess);
    }

    public void register(String email, String password, String confirmPassword, String shopName) {
        if (validate(email, password, confirmPassword, shopName)) {
            Shop shop = new Shop(email, password, shopName);
            registerUseCase.execute(shop, shopLiveData);
        }
    }

    private boolean validate(String email, String password, String confirmPassword, String shopName) {
        boolean isValid = true;

        if (shopName == null || shopName.isEmpty()) {
            shopNameError.setValue("Required");
            isValid = false;
        }
        if (email == null || email.isEmpty()) {
            emailError.setValue("Required");
            isValid = false;
        } else if (!isEmailValid(email)) {
            emailError.setValue("Incorrect email format");
            isValid = false;
        }
        if (password == null || password.isEmpty()) {
            passwordError.setValue("Required");
            isValid = false;
        } else if (password.length() < 8) {
            passwordError.setValue("Password must be 8 characters or more");
            isValid = false;
        } else if (confirmPassword == null || confirmPassword.isEmpty()) {
            confirmPasswordError.setValue("Required");
            isValid = false;
        } else if (!password.equals(confirmPassword)) {
            confirmPasswordError.setValue("doesn't match password");
            isValid = false;
        }
        return isValid;
    }

    private boolean isEmailValid(String email) {
        boolean isValid = false;

        // String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        String expression = "(?!^[.+&'_-]*@.*$)(^[_\\w\\d+&'-]+(\\.[_\\w\\d+&'-]*)*@[\\w\\d-]+(\\.[\\w\\d-]+)*\\.(([\\d]{1,3})|([\\w]{2,}))$)";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public MutableLiveData<Boolean> getAddShopSuccess() {
        return addShopSuccess;
    }

    public MutableLiveData<Shop> getShopLiveData() {
        return shopLiveData;
    }

    public MutableLiveData<String> getEmailError() {
        return emailError;
    }

    public MutableLiveData<String> getPasswordError() {
        return passwordError;
    }

    public MutableLiveData<String> getConfirmPasswordError() {
        return confirmPasswordError;
    }

    public MutableLiveData<String> getShopNameError() {
        return shopNameError;
    }
}
