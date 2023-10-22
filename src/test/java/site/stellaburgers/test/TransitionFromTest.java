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
    @DisplayName("Проверка  перехода  по клику на [Констурктор]")
    void transitionFromConstructorButtonTest() {
        profilePage = mainPage.clickPersonalAreaButtonAfterAuthorization();
        step("Проверяем, что есть информация о нахождении в личном кабинете",
                () -> profilePage.getPersonalInfo().shouldBe(visible));
        mainPage.clickConstructorButton();
        step("Проверяем, что есть информация о нахождении в разделе [Конструктор]",
        () -> mainPage.getAssembleBurger().shouldBe(visible));
    }

    @Test
    @DisplayName("Проверяем переход по клику на [Лого]")
    void transitionFromLogoTest() {
        profilePage = mainPage.clickPersonalAreaButtonAfterAuthorization();
        step("Проверяем, что есть инфорция о нахождении в личном кабинете",
                () -> profilePage.getPersonalInfo().shouldBe(visible));
        mainPage.clickLogo();
        step("Проверяем, что есть информация о нахождении в разделе [Конструктор]",
                () ->mainPage.getAssembleBurger().shouldBe(visible));
    }

    @AfterEach
    public void finish() {
        ApiSteps.sendDelete(pair.getLeft());
    }
}
