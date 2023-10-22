package site.stellaburgers.ui.pages;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class ProfilePage {
    @As("Кнопка [Профиль]")
    private final SelenideElement profileButton= $x("//a[text()='Профиль']");
    @As("Кнопка  [Истрория заказов]")
    private final SelenideElement historyButton = $x("//a[text()='История заказов']");
    @As("Кнопка  [Выход]")
    private final SelenideElement exitButton = $x("//button[text()='Выход']");
    @As("Поле [Имя]")
    private final SelenideElement nameField = $("input[name='Name']");
    @As("Поле [Логин]")
    private final SelenideElement loginField = $x("//input[@type='text' and @name='name']");
    @As("Поле [Пароль]")
    private final SelenideElement passwordField = $("input[type='password']");
    @As("Кнопка [Сохранить]")
    private final SelenideElement saveButton = $x("//button[text()='Сохранить']");
    @As("Кнопка [Отмена]")
    private final SelenideElement cancelButton = $x("//button[text()='Отмена']");
    @As("Информация о разеделе [Личный кабинет]")
    private final SelenideElement personalInfo = $("p[class*=Account]");

    public ProfilePage() {
        profileButton.shouldBe(visible);
        historyButton.shouldBe(visible);
        exitButton.shouldBe(visible);
        nameField.shouldBe(visible);
        loginField.shouldBe(visible);
        passwordField.shouldBe(visible);
        saveButton.shouldBe(visible);
        cancelButton.shouldBe(visible);
        personalInfo.shouldBe(visible);
    }

    @Step("Нажать по кнопке [Выход]")
    public final LoginPage clickExitButton() {
        exitButton.click();
        return new LoginPage();
    }
}
