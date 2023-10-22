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
public class RegisterPage {

    @As("Поле [Имя]")
    private final SelenideElement nameField = $x("//label[text()='Имя']/..//input[@type='text']");
    @As("Поле [Email]")
    private final SelenideElement emailField = $x("//label[text()='Email']/..//input[@type='text']");
    @As("Поле [Пароль]")
    private final SelenideElement passwordField = $("input[type='password']");
    @As("Кнопка [Зарегестрироваться]")
    private final SelenideElement registerButton = $x("//button[text()='Зарегистрироваться']");
    @As("Кнопка [Войти]")
    private final SelenideElement loginButton = $x("//a[text()='Войти']");
    @As("Сообщение об ошибке")
    private final SelenideElement errorMessage = $("p[class*=error]");

    public RegisterPage() {
        nameField.shouldBe(visible);
        emailField.shouldBe(visible);
        passwordField.shouldBe(visible);
        registerButton.shouldBe(visible);
        loginButton.shouldBe(visible);
    }

    @Step("Заполнить поле [Имя] значением {name}")
    public final RegisterPage fillNameField(String name) {
        nameField.shouldBe(visible).setValue(name);
        return this;
    }

    @Step("Заполнить поле [Email] значением {email} ")
    public final RegisterPage fillEmailField(String email) {
        emailField.shouldBe(enabled).setValue(email);
        return this;
    }

    @Step("Заполнить поле [Пароль] значением {password}")
    public final RegisterPage fillPasswordField(String password) {
        passwordField.shouldBe(enabled).setValue(password);
        return this;
    }

    @Step("Нажать по кнопке [Зарегестрироваться]  неверными данными")
    public final RegisterPage clickRegisterButtonWithInvalidData() {
        registerButton.shouldBe(enabled).click();
        return this;
    }

    @Step("Нажать по кнопке [Зарегестрироваться]")
    public final LoginPage clickRegisterButton() {
        registerButton.shouldBe(enabled).click();
        return new LoginPage();
    }

    @Step("Нажать по кнопке [Войти]")
    public final LoginPage clickLoginButton() {
        loginButton.shouldBe(enabled).click();
        return new LoginPage();
    }
}
