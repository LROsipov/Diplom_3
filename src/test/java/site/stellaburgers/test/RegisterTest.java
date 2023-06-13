package site.stellaburgers.test;

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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static site.stellaburgers.api.factory.RandomUser.getRandomUser;
import static site.stellaburgers.api.steps.ApiSteps.sendUserLogin;

@DisplayName("Регистрация")
public class RegisterTest extends BaseUiTest {

    UserJson user;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ProfilePage profilePage;

    @BeforeEach
    void arrangeTestData() {
        mainPage = MainPage.open();
        registerPage = new RegisterPage();
        loginPage = new LoginPage();
        profilePage = new ProfilePage();
        user = getRandomUser();
    }

    @Test
    @DisplayName("Успешная регистрация")
    void successRegisterTest() {
        mainPage.clickLoginButton();
        loginPage.clickRegisterButton();
        registerPage.fillNameField(user.getName())
                .fillEmailField(user.getEmail())
                .fillPasswordField(user.getPassword())
                .clickRegisterButton();
        loginPage.fillLoginFields(user.getEmail(), user.getPassword())
                .clickLoginButton();
        mainPage.clickPersonalAreaButton();
        step("Сравниваем значения поля [Имя] с данными при регистрации",
                () -> assertEquals(profilePage.checkNameField(), user.getName()));
        step("Сравниваем значения поля [Email] с данными при регистрации",
                () -> assertEquals(profilePage.checkEmailField(), user.getEmail()));
    }

    @Test
    @DisplayName("Невозможность регестрации с паролем меньше 6 символов")
    void invalidRegisterTest() {
        mainPage.clickLoginButton();
        loginPage.clickRegisterButton();
        registerPage.fillNameField(user.getName())
                .fillEmailField(user.getEmail())
                .fillPasswordField("12345")
                .clickRegisterButton();
        step("Проверяем появилось ли сообщение об ошибке",
                () -> registerPage.getErrorMessagePassword().shouldBe(visible));
    }

    @AfterEach
    public void finish() {
        closeWindow();
        String token = ApiSteps.takeToken(sendUserLogin(new LoginJson(user.getEmail(), user.getPassword())));
        if (token != null) {
            ApiSteps.sendDelete(token);
        }
    }
}
