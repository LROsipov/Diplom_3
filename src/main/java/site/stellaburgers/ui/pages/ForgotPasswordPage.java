package site.stellaburgers.ui.pages;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ForgotPasswordPage {
    @As("Поле [Имя]")
    private final SelenideElement emailField = $x("//label[text()='Email']");
    @As("Кнопка [Восстановить]")
    private final SelenideElement restoreButton = $x("//button[text()='Восстановить']");
    @As("Кнопка [Войти]")
    private final SelenideElement loginButton = $x("//a[text()='Войти']");

    @Step("Заполняем поле [Email]")
    public final ForgotPasswordPage fillEmailField(String email) {
        emailField.shouldBe(visible).setValue(email);
        return this;
    }

    @Step("Клик по кнопке [Восстановить]")
    public final void clickRestoreButton() {
        restoreButton.shouldBe(enabled).click();
    }

    @Step("Клик по кнопке [Войти]")
    public final void clickLoginButton() {
        loginButton.shouldBe(enabled).click();
    }
}
