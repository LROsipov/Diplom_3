package site.stellaburgers.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import site.stellaburgers.api.steps.ApiSteps;
import site.stellaburgers.ui.steps.UiSteps;

public abstract class BaseUiTest {
    protected static final ApiSteps apiSteps = new ApiSteps();
    protected static final UiSteps uiSteps = new UiSteps();

    @BeforeAll
    public static void setUpBrowser() {
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false; // false чтобы видеть как автотесты прогоняются в браузере        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    public void clear() {
        Selenide.closeWebDriver();
    }

}