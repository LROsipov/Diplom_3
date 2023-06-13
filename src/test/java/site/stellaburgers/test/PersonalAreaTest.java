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

import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;

@DisplayName("Переход в личный кабинет")
public class PersonalAreaTest extends BaseUiTest {
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
    @DisplayName("Проверяем переход по клику на [Личный кабинет]")
    void loginToClickLoginButtonTest() {
        loginInSite(user.getEmail(), user.getPassword());
        mainPage.clickPersonalAreaButton();
        step("Проверяем, что есть кнопка [Профиль]",
                () -> profilePage.getProfileButton().shouldBe(visible));
        step("Проверяем, что есть информация о нахождении в личном кабинете",
                () -> profilePage.getPersonalInfo().shouldBe(visible));
    }

    @AfterEach
    public void finish() {
        closeWindow();
        ApiSteps.sendDelete(pair.getLeft());
    }
}
