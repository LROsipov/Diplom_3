package site.stellaburgers.ui.pages;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class RegisterPage {

    @As("Поле [Имя]")
    private final SelenideElement nameField = $x("//label[text()='Имя']/..//input[@type='text']");
    @As("Поле [Email]")
    private final SelenideElement emailField = $x("//label[text()='Email']/..//input[@type='text']");
    @As("Поле [Пароль]")
    private final SelenideElement passwordField = $x("//label[text()='Пароль']/..//input[@type='password']");
    @As("Кнопка [Зарегестрироваться]")
    private final SelenideElement registerButton = $x("//button[text()='Зарегистрироваться']");
    @As("Кнопка [Войти]")
    private final SelenideElement loginButton = $x("//a[text()='Войти']");
    @As("Сообщение об ошибке [Некорректный пароль]")
    private final SelenideElement incorrectPassword = $x("//p[text()='Некорректный пароль']");


    @Step("Заполняем поле [Имя]")
    public final RegisterPage fillNameField(String name) {
        nameField.shouldBe(visible).setValue(name);
        return this;
    }

    @Step("Заполняем поле [Email]")
    public final RegisterPage fillEmailField(String email) {
        emailField.shouldBe(enabled).setValue(email);
        return this;
    }

    @Step("Заполняем поле [Пароль]")
    public final RegisterPage fillPasswordField(String password) {
        passwordField.shouldBe(enabled).setValue(password);
        return this;
    }

    @Step("Кликаем по  кнопке [Зарегестрироваться]")
    public final void clickRegisterButton() {
        registerButton.shouldBe(enabled).click();
    }

    @Step("Кликаем по  кнопке [Войти]")
    public final void clickLoginButton() {
        loginButton.shouldBe(enabled).click();
    }

    @Step("Смотрим на появившуюся ошибку")
    public SelenideElement getErrorMessagePassword() {
        return incorrectPassword;
    }

}
