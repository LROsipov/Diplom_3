package site.stellaburgers.ui.pages;

import com.codeborne.selenide.As;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/";
    @As("Кнопка [Войти в аккаунт]")
    private final SelenideElement loginButton = $x("//button[text()='Войти в аккаунт']");
    @As("Кнопка [Личный кабинет]")
    private final SelenideElement personalAreaButton = $x("//p[text()='Личный Кабинет']");
    @As("Кнопка [Констурктор]")
    private final SelenideElement constructorButton = $x("//p[text()='Конструктор']");
    @As("Кнопка [Булки]")
    private final SelenideElement bunButton = $x("//span[text()='Булки']");
    @As("Кнопка [Соусы]")
    private final SelenideElement saucesButton = $x("//span[text()='Соусы']");
    @As("Кнопка [Начинки]")
    private final SelenideElement stuffingButton = $x("//span[text()='Начинки']");
    @As("Текст [Соберите бургер]")
    private final SelenideElement assembleBurger = $x("//h1[text()='Соберите бургер']");
    @As("Лого сайта")
    private final SelenideElement logoSite = $x("//div[contains(@class, 'AppHeader_header')]");
    @As("Активный элемент")
    private final SelenideElement activeElement = $x("//div[contains(@class, 'tab_tab_type_current')]/span[contains(@class, 'text_type_main-default')]");


    public MainPage() {
        loginButton.shouldBe(visible);
        personalAreaButton.shouldBe(visible);
        constructorButton.shouldBe(visible);
        bunButton.shouldBe(visible);
        saucesButton.shouldBe(visible);
        stuffingButton.shouldBe(visible);
    }
    @Step("Открываем главную страницу сайта")
    public static MainPage open() {
        return Selenide.open(URL, MainPage.class);
    }

    @Step("Кликаем по кнопке [Войти в аккаунт]")
    public final void clickLoginButton() {
        loginButton.shouldBe(enabled).click();
    }

    @Step("Кликаем по кнопке [Личный кабинет]")
    public final void clickPersonalAreaButton() {
        personalAreaButton.shouldBe(enabled).click();
    }

    @Step("Кликаем по кнопке [Конструктор]")
    public final MainPage clickConstructorButton() {
        constructorButton.shouldBe(enabled).click();
        return this;
    }

    @Step("Кликаем по кнопке [Булки]")
    public final MainPage clickBunButton() {
        bunButton.shouldBe(enabled).click();
        return this;
    }

    @Step("Кликаем по кнопке [Соусы]")
    public final MainPage clickSaucesButton() {
        saucesButton.shouldBe(enabled).click();
        return this;
    }

    @Step("Кликаем по кнопке [Начинки]")
    public final MainPage clickStuffingButton() {
        stuffingButton.shouldBe(enabled).click();
        return this;
    }

    @Step("Кликаем по лого сайта")
    public final MainPage clickLogo() {
        logoSite.shouldBe(visible).click();
        return this;
    }

    public SelenideElement getConstructorInfo() {
        return assembleBurger;
    }

    public SelenideElement getActiveElementInfo() {
        return activeElement;
    }


}
