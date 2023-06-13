package site.stellaburgers.ui.pages;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {

    @As("Кнопка [Профиль]")
    private final SelenideElement profileButton = $x("//a[text()='Профиль']");
    @As("Кнопка  [Истрория заказов]")
    private final SelenideElement historyButton = $x("//a[text()='История заказов']");
    @As("Кнопка  [Выход]")
    private final SelenideElement exitButton = $x("//button[text()='Выход']");
    @As("Поле [Имя]")
    private final SelenideElement nameField = $x("//input[@name='Name']");
    @As("Поле [Логин]")
    private final SelenideElement loginField = $x("//input[@type='text' and @name='name']");
    @As("Поле [Пароль]")
    private final SelenideElement passwordField = $x("//input[@type='password']");
    @As("Кнопка [Сохранить]")
    private final SelenideElement saveButton = $x("//button[text()='Сохранить']");
    @As("Кнопка [Отмена]")
    private final SelenideElement cancelButton = $x("//button[text()='Отмена']");
    @As("Информация о разеделе [Личный кабинет]")
    private final SelenideElement personalInfo = $x("//p[text()='В этом разделе вы можете изменить свои персональные данные']");

    @Step("Смотрим на значение в поле [Имя]")
    public String checkNameField() {
        return nameField.shouldBe(visible).getValue();
    }

    @Step("Смотрим на значение в поле [Email]")
    public String checkEmailField() {
        return loginField.shouldBe(visible).getValue();
    }

    @Step("Кликаем по кнопке [Выход]")
    public void clickExitButton() {
        exitButton.click();
    }

    public SelenideElement getProfileButton() {
        return profileButton;
    }

    public SelenideElement getPersonalInfo() {
        return personalInfo;
    }
}
