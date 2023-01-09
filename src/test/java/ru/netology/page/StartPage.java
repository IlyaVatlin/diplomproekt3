package ru.netology.page;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class StartPage {
    private SelenideElement heading = $$("h2").find(exactText("Путешествие дня"));
    private SelenideElement debitButton = $$("button").find(exactText("Купить"));
    private SelenideElement creditButton = $$("button").find(exactText("Купить в кредит"));

    public StartPage() {
        heading.shouldBe(visible);
    }

    public BuyWithCard openBuyWithCard() {
        debitButton.click();
        return new BuyWithCard();
    }

    public BuyWithCredit openBuyWithCredit() {
        creditButton.click();
        return new BuyWithCredit();
    }
}

