package site.stellaburgers.test;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.stellaburgers.api.dto.LoginJson;
import site.stellaburgers.ui.pages.MainPage;
import site.stellaburgers.ui.pages.ProfilePage;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static io.qameta.allure.Allure.step;
import static site.stellaburgers.utils.DataStringConstants.LOGIN_URL;

@DisplayName("Провеки модуля [Выход из аккаунта]")
class LogoutTest extends BaseUiTest {
    Pair<String, LoginJson> pair;
    LoginJson user;
    MainPage mainPage;
    ProfilePage profilePage;

    @BeforeEach
    void getTestData() {
        pair = apiSteps.generateLoginUser();
        user = pair.getRight();
        mainPage = uiSteps.authorization(user.getEmail(), user.getPassword());
    }

    @Test
    @DisplayName("Проверка выхода из аккаунта по кнопке [Выйти] в личном кабинете.")
    void loginToClickLoginButtonTest() {
        profilePage = mainPage.clickPersonalAreaButtonAfterAuthorization();
        profilePage.clickExitButton();
        step("Проверить, что открыта станица  авторизации",
                () -> webdriver().shouldHave(url(LOGIN_URL)));
    }

    @AfterEach
    public void finish() {
        apiSteps.sendDelete(pair.getLeft());
    }
}
