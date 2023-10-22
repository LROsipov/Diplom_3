package site.stellaburgers.ui.pages;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class ForgotPasswordPage {
    @As("Поле [Имя]")
    private final SelenideElement emailField = $x("//label[text()='Email']");
    @As("Кнопка [Восстановить]")
    private final SelenideElement restoreButton = $x("//button[text()='Восстановить']");
    @As("Кнопка [Войти]")
    private final SelenideElement loginButton = $x("//a[text()='Войти']");

    public ForgotPasswordPage() {
        emailField.shouldBe(visible);
        restoreButton.shouldBe(visible);
        loginButton.shouldBe(visible);
    }

    @Step("Заполнить поле [Email] значением {email}")
    public final ForgotPasswordPage fillEmailField(String email) {
        emailField.shouldBe(visible).setValue(email);
        return this;
    }

    @Step("Клик по кнопке [Войти]")
    public final LoginPage  clickLoginButton() {
        loginButton.shouldBe(enabled).click();
        return new LoginPage();
    }
}
