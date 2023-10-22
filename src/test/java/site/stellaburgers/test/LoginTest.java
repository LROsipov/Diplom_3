package site.stellaburgers.test;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.stellaburgers.api.dto.LoginJson;
import site.stellaburgers.ui.pages.*;

import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.Allure.step;

@DisplayName("Проверки модуля [Вход]")
class LoginTest extends BaseUiTest {
    Pair<String, LoginJson> pair;
    LoginJson user;
    MainPage mainPage;
    LoginPage loginPage;
    ProfilePage profilePage;

    @BeforeEach
    void getTestData() {
        pair = apiSteps.generateLoginUser();
        user = pair.getRight();
        mainPage = MainPage.open();
    }

    @Test
    @DisplayName("Проверка входа по кнопке [Войти в аккаунт] на главной")
    void loginToClickLoginButtonTest() {
        profilePage = mainPage.clickLoginButton()
                .fillLoginFields(pair.getRight().getEmail(), pair.getRight().getPassword())
                .clickLoginButton()
                .clickPersonalAreaButtonAfterAuthorization();
        step("Проверить, что  открыта страница с данными пользователя",
                () ->profilePage.getPersonalInfo().shouldBe(visible));
    }

    @Test
    @DisplayName("Проверка входа через кнопку [Личный кабинет]")
    void loginToClickPersonalAreaButtonTest() {
        loginPage = mainPage.clickPersonalAreaButtonBeforeAuthorization();
        profilePage = loginPage.fillLoginFields(pair.getRight().getEmail(), pair.getRight().getPassword())
                .clickLoginButton()
                .clickPersonalAreaButtonAfterAuthorization();
        step("Проверить, что  открыта страница с данными пользователя",
                () ->profilePage.getPersonalInfo().shouldBe(visible));
    }

    @Test
    @DisplayName("Проверка входа через кнопку [Войти] в форме регистрации")
    void loginToLoginButtonInRegisterTest() {
        loginPage = mainPage.clickPersonalAreaButtonBeforeAuthorization();
        RegisterPage registerPage = loginPage.clickRegisterButton();
        profilePage = registerPage.clickLoginButton()
                .fillLoginFields(pair.getRight().getEmail(), pair.getRight().getPassword())
                .clickLoginButton()
                .clickPersonalAreaButtonAfterAuthorization();
        step("Проверить, что  открыта страница с данными пользователя",
                () ->profilePage.getPersonalInfo().shouldBe(visible));
    }

    @Test
    @DisplayName("Проверка входа через кнопку [Войти] в форме восстановления пароля")
    void loginToLoginButtonInForgotPasswordTest() {
        loginPage = mainPage.clickPersonalAreaButtonBeforeAuthorization();
        ForgotPasswordPage forgotPasswordPage =  loginPage.clickForgotPasswordButton();
        profilePage = forgotPasswordPage.clickLoginButton()
                .fillLoginFields(user.getEmail(), user.getPassword())
                .clickLoginButton()
                .clickPersonalAreaButtonAfterAuthorization();
        step("Проверить, что  открыта страница с данными пользователя",
                () ->profilePage.getPersonalInfo().shouldBe(visible));
    }

    @AfterEach
    public void finish() {
        apiSteps.sendDelete(pair.getLeft());
    }
}
