package site.stellaburgers.ui.steps;

import io.qameta.allure.Step;
import site.stellaburgers.ui.pages.MainPage;

public class UiSteps {
    @Step("Авторизироваться  на сайте")
    public MainPage authorization(String email, String password) {
        return MainPage.open()
                .clickLoginButton()
                .fillLoginFields(email, password)
                .clickLoginButton();
    }
}
