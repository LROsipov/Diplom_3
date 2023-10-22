package site.stellaburgers.test;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.stellaburgers.api.dto.LoginJson;
import site.stellaburgers.ui.pages.MainPage;

import static com.codeborne.selenide.Condition.text;
import static io.qameta.allure.Allure.step;
import static site.stellaburgers.utils.DataStringConstants.*;

@DisplayName("Проврека модуля [Конструктор]")
class ChapterConstructorTest extends BaseUiTest {
    Pair<String, LoginJson> pair;
    LoginJson user;
    MainPage mainPage;

    @BeforeEach
    void getTestData() {
        pair = apiSteps.generateLoginUser();
        user = pair.getRight();
        mainPage = uiSteps.authorization(user.getEmail(), user.getPassword());
    }

    @Test
    @DisplayName("Проверка перехода по разделам констуктора")
    void jumpToSectionBunTest() {
        mainPage.clickSaucesButton();
        step("Проверяем, что есть информация о активном разделе",
                () -> mainPage.getActiveElement().shouldHave(text(SAUCES)));
        mainPage.clickBunButton();
        step("Проверяем, что есть информация о активном разделе",
                () -> mainPage.getActiveElement().shouldHave(text(BUN)));
        mainPage.clickStuffingButton();
        step("Проверяем, что есть информация о активном разделе",
                () -> mainPage.getActiveElement().shouldHave(text(STUFFING)));
    }

    @AfterEach
    public void finish() {
        apiSteps.sendDelete(pair.getLeft());
    }
}
