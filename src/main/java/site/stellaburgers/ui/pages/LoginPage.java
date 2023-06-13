package site.stellaburgers.ui.pages;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    @As("Поле [Email]")
    private final SelenideElement emailField = $x("//label[text()='Email']/..//input[@type='text']");
    @As("Поле [Пароль]")
    private final SelenideElement passwordField = $x("//label[text()='Пароль']/..//input[@type='password']");
    @As("Кнопка [Войти]")
    private final SelenideElement loginButton = $x("//button[text()='Войти']");
    @As("Кнопка  [Зарегестрироваться]")
    private final SelenideElement registerButton = $x("//a[text()='Зарегистрироваться']");
    @As("Кнопка  [Восстановить пароль]")
    private final SelenideElement forgotPasswordButton = $x("//a[text()='Восстановить пароль']");

    @Step("Заполняем поле [Email]")
    public final LoginPage fillEmailField(String email) {
        emailField.shouldBe(visible).setValue(email);
        return this;
    }

    @Step("Заполняем поле [Пароль]")
    public final LoginPage fillPasswordField(String password) {
        passwordField.shouldBe(visible).setValue(password);
        return this;
    }

    @Step("Заполняем форму авторизации")
    public final LoginPage fillLoginFields(String email, String password) {
        emailField.shouldBe(visible).setValue(email);
        passwordField.shouldBe(visible).setValue(password);
        return this;
    }

    @Step("Кликаем по кнопке [Войти]")
    public final void clickLoginButton() {
        loginButton.shouldBe(enabled).click();
    }

    @Step("Кликаем по кнопке [Зарегестрироваться]")
    public final void clickRegisterButton() {
        registerButton.shouldBe(enabled).click();
    }

    @Step("Кликаем по кнопке [Восстановить пароль]")
    public final void clickForgotPasswordButton() {
        forgotPasswordButton.shouldBe(enabled).click();
    }

}
