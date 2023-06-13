package site.stellaburgers.test;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.stellaburgers.api.dto.LoginJson;
import site.stellaburgers.api.steps.ApiSteps;
import site.stellaburgers.ui.pages.MainPage;

import static com.codeborne.selenide.Condition.text;
import static io.qameta.allure.Allure.step;

@DisplayName("Раздел [Конструктор]")
public class ChapterConstructorTest extends BaseUiTest {
    private Pair<String, LoginJson> pair;
    private LoginJson user;
    private MainPage mainPage;

    @BeforeEach
    void arrangeTestData() {
        mainPage = MainPage.open();
        pair = generateLoginUser();
        user = pair.getRight();
    }

    @Test
    @DisplayName("Проверяем переход к разделу [Булки]")
    void jumpToSectionBunTest() {
        loginInSite(user.getEmail(), user.getPassword());
        mainPage.clickSaucesButton()
                .clickBunButton();
        step("Проверяем, что есть информация о активном разделе",
                () -> mainPage.getActiveElementInfo().shouldHave(text("Булки")));
    }

    @Test
    @DisplayName("Проверяем переход к разделу [Соусы]")
    void jumpToSectionSaucesTest() {
        loginInSite(user.getEmail(), user.getPassword());
        mainPage.clickSaucesButton();
        step("Проверяем, что есть информация о активном разделе",
                () -> mainPage.getActiveElementInfo().shouldHave(text("Соусы")));
    }

    @Test
    @DisplayName("Проверяем переход к разделу [Начинки]")
    void jumpToSectionStuffingTest() {
        loginInSite(user.getEmail(), user.getPassword());
        mainPage.clickStuffingButton();
        step("Проверяем, что есть информация о активном разделе",
                () -> mainPage.getActiveElementInfo().shouldHave(text("Начинки")));
    }

    @AfterEach
    public void finish() {
        closeWindow();
        ApiSteps.sendDelete(pair.getLeft());
    }
}
