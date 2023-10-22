package site.stellaburgers.ui.pages;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class LoginPage {
    @As("Поле [Email]")
    private final SelenideElement emailField = $("input[type='text']");
    @As("Поле [Пароль]")
    private final SelenideElement passwordField = $("input[type='password']");
    @As("Кнопка [Войти]")
    private final SelenideElement loginButton = $x("//button[text()='Войти']");
    @As("Кнопка  [Зарегестрироваться]")
    private final SelenideElement registerButton = $x("//a[text()='Зарегистрироваться']");
    @As("Кнопка  [Восстановить пароль]")
    private final SelenideElement forgotPasswordButton = $x("//a[text()='Восстановить пароль']");

    public LoginPage() {
        emailField.shouldBe(visible);
        passwordField.shouldBe(visible);
        loginButton.shouldBe(visible);
        registerButton.shouldBe(visible);
        forgotPasswordButton.shouldBe(visible);
    }

    @Step("Заполнить форму авторизации")
    public final LoginPage fillLoginFields(String email, String password) {
        emailField.shouldBe(visible).setValue(email);
        passwordField.shouldBe(visible).setValue(password);
        return this;
    }

    @Step("Нажать по кнопке [Войти]")
    public final MainPage clickLoginButton() {
        loginButton.shouldBe(enabled).click();
        return new MainPage();
    }

    @Step("Нажать по кнопке [Зарегестрироваться]")
    public final RegisterPage clickRegisterButton() {
        registerButton.shouldBe(enabled).click();
        return new RegisterPage();
    }

    @Step("Нажать по кнопке [Восстановить пароль]")
    public final ForgotPasswordPage clickForgotPasswordButton() {
        forgotPasswordButton.shouldBe(enabled).click();
        return new ForgotPasswordPage();
    }
}
