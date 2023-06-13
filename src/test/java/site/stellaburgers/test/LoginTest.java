package site.stellaburgers.test;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.stellaburgers.api.dto.LoginJson;
import site.stellaburgers.api.steps.ApiSteps;
import site.stellaburgers.ui.pages.*;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Вход")
public class LoginTest extends BaseUiTest {
    private Pair<String, LoginJson> pair;
    private LoginJson user;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ProfilePage profilePage;
    private ForgotPasswordPage forgotPasswordPage;

    @BeforeEach
    void arrangeTestData() {
        mainPage = MainPage.open();
        registerPage = new RegisterPage();
        loginPage = new LoginPage();
        profilePage = new ProfilePage();
        pair = generateLoginUser();
        user = pair.getRight();
        forgotPasswordPage = new ForgotPasswordPage();
    }

    @Test
    @DisplayName("Вход по кнопке [Войти в аккаунт] на главной")
    void loginToClickLoginButtonTest() {
        loginInSite(user.getEmail(), user.getPassword());
        mainPage.clickPersonalAreaButton();
        step("Сравниваем значения поля [Email] с данными при регистрации",
                () -> assertEquals(profilePage.checkEmailField(), user.getEmail()));
    }

    @Test
    @DisplayName("Вход через кнопку [Личный кабинет]")
    void loginToClickPersonalAreaButtonTest() {
        mainPage.clickPersonalAreaButton();
        loginPage.fillLoginFields(user.getEmail(), user.getPassword())
                .clickLoginButton();
        mainPage.clickPersonalAreaButton();
        step("Сравниваем значения поля [Email] с данными при регистрации",
                () -> assertEquals(profilePage.checkEmailField(), user.getEmail()));
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    void loginToLoginButtonInRegisterTest() {
        mainPage.clickPersonalAreaButton();
        loginPage.clickRegisterButton();
        registerPage.clickLoginButton();
        loginPage.fillLoginFields(user.getEmail(), user.getPassword())
                .clickLoginButton();
        mainPage.clickPersonalAreaButton();
        step("Сравниваем значения поля [Email] с данными при регистрации",
                () -> assertEquals(profilePage.checkEmailField(), user.getEmail()));
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    void loginToLoginButtonInForgotPasswordTest() {
        mainPage.clickPersonalAreaButton();
        loginPage.clickForgotPasswordButton();
        forgotPasswordPage.clickLoginButton();
        loginPage.fillLoginFields(user.getEmail(), user.getPassword())
                .clickLoginButton();
        mainPage.clickPersonalAreaButton();
        step("Сравниваем значения поля [Email] с данными при регистрации",
                () -> assertEquals(profilePage.checkEmailField(), user.getEmail()));
    }

    @AfterEach
    public void finish() {
        closeWindow();
        ApiSteps.sendDelete(pair.getLeft());
    }
}
