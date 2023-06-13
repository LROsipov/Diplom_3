package site.stellaburgers.test;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.stellaburgers.api.dto.LoginJson;
import site.stellaburgers.api.steps.ApiSteps;
import site.stellaburgers.ui.pages.MainPage;
import site.stellaburgers.ui.pages.ProfilePage;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static io.qameta.allure.Allure.step;

@DisplayName("Выход из аккаунта")
public class LogoutTest extends BaseUiTest {
    private final String LOGIN = "https://stellarburgers.nomoreparties.site/login";
    private Pair<String, LoginJson> pair;
    private LoginJson user;
    private MainPage mainPage;
    private ProfilePage profilePage;

    @BeforeEach
    void arrangeTestData() {
        mainPage = MainPage.open();
        profilePage = new ProfilePage();
        pair = generateLoginUser();
        user = pair.getRight();
    }

    @Test
    @DisplayName("Проверем выход по кнопке [Выйти] в личном кабинете.")
    void loginToClickLoginButtonTest() {
        loginInSite(user.getEmail(), user.getPassword());
        mainPage.clickPersonalAreaButton();
        profilePage.clickExitButton();
        step("Проверяем, что находимся на странице входа",
                () -> webdriver().shouldHave(url(LOGIN)));
    }

    @AfterEach
    public void finish() {
        closeWindow();
        ApiSteps.sendDelete(pair.getLeft());
    }
}
