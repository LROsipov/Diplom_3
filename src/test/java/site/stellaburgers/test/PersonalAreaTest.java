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

@DisplayName("Проверки модуля [Личный кабинет]")
class PersonalAreaTest extends BaseUiTest {
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
    @DisplayName("Проверка перехода по клику на [Личный кабинет]")
    void loginToClickLoginButtonTest() {
        profilePage = mainPage.clickPersonalAreaButtonAfterAuthorization();
        step("Проверить, что есть кнопка [Профиль]",
                () -> profilePage.getProfileButton().shouldBe(visible));
        step("Провить, что есть информация о нахождении в личном кабинете",
                () -> profilePage.getPersonalInfo().shouldBe(visible));
    }

    @AfterEach
    public void finish() {
        ApiSteps.sendDelete(pair.getLeft());
    }
}
