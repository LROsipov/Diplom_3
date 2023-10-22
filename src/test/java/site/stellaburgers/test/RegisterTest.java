package site.stellaburgers.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.stellaburgers.api.dto.LoginJson;
import site.stellaburgers.api.dto.UserJson;
import site.stellaburgers.api.steps.ApiSteps;
import site.stellaburgers.ui.pages.LoginPage;
import site.stellaburgers.ui.pages.MainPage;
import site.stellaburgers.ui.pages.ProfilePage;
import site.stellaburgers.ui.pages.RegisterPage;

import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;
import static site.stellaburgers.api.factory.RandomUserData.getRandomUser;
import static site.stellaburgers.api.steps.ApiSteps.sendUserLogin;

@DisplayName("Проверки модуля [Регистрация]")
class RegisterTest extends BaseUiTest {

    UserJson user;
    MainPage mainPage;
    LoginPage loginPage;
    RegisterPage registerPage;
    ProfilePage profilePage;

    @BeforeEach
    void getTestData() {
        user = getRandomUser();
        mainPage = MainPage.open();
    }

    @Test
    @DisplayName("Провекра успешной регистрации")
    void successRegisterTest() {
        registerPage = mainPage.clickLoginButton()
                .clickRegisterButton();
        loginPage = registerPage.fillNameField(user.getName())
                .fillEmailField(user.getEmail())
                .fillPasswordField(user.getPassword())
                .clickRegisterButton();
        profilePage = loginPage.fillLoginFields(user.getEmail(), user.getPassword())
                .clickLoginButton()
                .clickPersonalAreaButtonAfterAuthorization();
        step("Проверяем, что  значения поля [Имя] соответсвует данными при регистрации",
                () -> profilePage.getNameField().shouldHave(Condition.value(user.getName())));
        step("Проверяем, что  значения поля [Email] оответсвует данными при регистраци",
                () -> profilePage.getLoginField().shouldHave(Condition.value(user.getEmail())));
    }

    @Test
    @DisplayName("Проверка невозможности регестрации с паролем меньше 6 символов")
    void invalidRegisterTest() {
        registerPage = mainPage.clickLoginButton().clickRegisterButton();
        registerPage.fillNameField(user.getName())
                .fillEmailField(user.getEmail())
                .fillPasswordField("12345")
                .clickRegisterButtonWithInvalidData();
        step("Проверяем, что появилось сообщение об ошибке",
                () -> registerPage.getErrorMessage().shouldBe(visible));
    }

    @AfterEach
    public void finish() {
        String token = apiSteps.takeToken(sendUserLogin(user));
        if (token != null) {
            apiSteps.sendDelete(token);
        }
    }
}
