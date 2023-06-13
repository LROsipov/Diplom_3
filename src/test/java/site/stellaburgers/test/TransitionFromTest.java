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

@DisplayName("Переход из личного кабинета в [Коснтруктор]")
public class TransitionFromTest extends BaseUiTest {
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
    @DisplayName("Проверяем переход по клику на [Констурктор]")
    void transitionFromConstructorButtonTest() {
        loginInSite(user.getEmail(), user.getPassword());
        mainPage.clickPersonalAreaButton();
        step("Проверяем, что есть информация о нахождении в личном кабинете",
                () -> profilePage.getPersonalInfo().shouldBe(visible));
        mainPage.clickConstructorButton();
        step("Проверяем, что есть информация о нахождении в разделе [Конструктор]",
                () -> mainPage.getConstructorInfo().shouldBe(visible));
    }

    @Test
    @DisplayName("Проверяем переход по клику на [Лого]")
    void transitionFromLogoTest() {
        loginInSite(user.getEmail(), user.getPassword());
        mainPage.clickPersonalAreaButton();
        step("Проверяем, что есть информация о нахождении в личном кабинете",
                () -> profilePage.getPersonalInfo().shouldBe(visible));
        mainPage.clickLogo();
        step("Проверяем, что есть информация о нахождении в разделе [Конструктор]",
                () -> mainPage.getConstructorInfo().shouldBe(visible));
    }

    @AfterEach
    public void finish() {
        closeWindow();
        ApiSteps.sendDelete(pair.getLeft());
    }
}
