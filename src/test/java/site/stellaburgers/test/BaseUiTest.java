package site.stellaburgers.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.response.Response;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import site.stellaburgers.api.dto.LoginJson;
import site.stellaburgers.api.dto.UserJson;
import site.stellaburgers.api.factory.RandomUser;
import site.stellaburgers.api.steps.ApiSteps;
import site.stellaburgers.ui.pages.LoginPage;
import site.stellaburgers.ui.pages.MainPage;

public abstract class BaseUiTest {


    public static void start() {
        //Для запуска в Яндекс браузере System.setProperty("selenide.browser", "src\main\resources\yandexdriver.exe");
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = true; // false чтобы видеть как автотесты прогоняются в браузере
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeAll
    public static void setUp() {
        start();
    }



    @Step("Генерируем данные для логина")
    public static Pair<String, LoginJson> generateLoginUser() {
        UserJson userJson = RandomUser.getRandomUser();
        ApiSteps apiSteps = new ApiSteps();
        Response response = apiSteps.sendUserRegistration(userJson);
        LoginJson loginJson = LoginJson.from(userJson);
        String token = ApiSteps.takeToken(response);
        Pair<String, LoginJson> pair = Pair.of(token, loginJson);
        return pair;
    }

    @Step("Закрываем браузер")
    void closeWindow() {
        SelenideLogger.removeListener("AllureSelenide");
        Selenide.closeWindow();
    }

    @Step("Логинемся на сайте")
    public void loginInSite(String email, String password) {
        MainPage mainPage = new MainPage();
        LoginPage loginPage = new LoginPage();
        mainPage.clickLoginButton();
        loginPage.fillLoginFields(email, password)
                .clickLoginButton();
    }
}