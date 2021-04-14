package com.app.smartepay;

import com.app.smartepay.data.BrandsRepository;
import com.app.smartepay.data.CategoriesRepository;
import com.app.smartepay.data.InvoiceRepository;
import com.app.smartepay.data.ItemsRepository;
import com.app.smartepay.data.ShopsRepository;
import com.app.smartepay.domain.BrandsRepositoryImpl;
import com.app.smartepay.domain.CategoriesRepositoryImpl;
import com.app.smartepay.domain.InvoiceRepositoryImpl;
import com.app.smartepay.domain.ItemsRepositoryImpl;
import com.app.smartepay.domain.ShopsRepositoryImpl;
import com.app.smartepay.domain.usecase.AddBrandUseCase;
import com.app.smartepay.domain.usecase.AddCategoryUseCase;
import com.app.smartepay.domain.usecase.AddItemUseCase;
import com.app.smartepay.domain.usecase.AddOrderUseCase;
import com.app.smartepay.domain.usecase.AddShopUseCase;
import com.app.smartepay.domain.usecase.GetShopUseCase;
import com.app.smartepay.domain.usecase.LoginUseCase;
import com.app.smartepay.domain.usecase.RegisterUseCase;
import com.app.smartepay.domain.usecase.RetrieveBrandsUseCase;
import com.app.smartepay.domain.usecase.RetrieveCategoriesUseCase;
import com.app.smartepay.domain.usecase.RetrieveItemsUseCase;

public class Injection {
    public static LoginUseCase getLoginUseCase() {
        return new LoginUseCase(getShopsRepository());
    }

    private static ShopsRepository getShopsRepository() {
        return new ShopsRepositoryImpl();
    }

    public static RegisterUseCase getRegisterUseCase() {
        return new RegisterUseCase(getShopsRepository());
    }

    public static AddShopUseCase getAddShopUseCase() {
        return new AddShopUseCase(getShopsRepository());
    }

    public static AddBrandUseCase getAddBrandUseCase() {
        return new AddBrandUseCase(getBrandsRepository());
    }

    private static BrandsRepository getBrandsRepository() {
        return new BrandsRepositoryImpl();
    }

    public static AddCategoryUseCase getAddCategoryUseCase() {
        return new AddCategoryUseCase(getCategoriesRepository());
    }

    private static CategoriesRepository getCategoriesRepository() {
        return new CategoriesRepositoryImpl();
    }

    public static AddItemUseCase getAddItemUseCase() {
        return new AddItemUseCase(getItemsRepository());
    }

    private static ItemsRepository getItemsRepository() {
        return new ItemsRepositoryImpl();
    }

    public static AddOrderUseCase getAddOrderUseCase() {
        return new AddOrderUseCase(getInvoiceRepository());
    }

    private static InvoiceRepository getInvoiceRepository() {
        return new InvoiceRepositoryImpl();
    }

    public static RetrieveBrandsUseCase getRetrieveBrandsUseCase() {
        return new RetrieveBrandsUseCase(getBrandsRepository());
    }

    public static RetrieveCategoriesUseCase getRetrieveCategoriesUseCase() {
        return new RetrieveCategoriesUseCase(getCategoriesRepository());
    }

    public static RetrieveItemsUseCase getRetrieveItemsUseCase() {
        return new RetrieveItemsUseCase(getItemsRepository());
    }

    public static GetShopUseCase getShopUseCase() {
        return new GetShopUseCase(getShopsRepository());
    }
}
