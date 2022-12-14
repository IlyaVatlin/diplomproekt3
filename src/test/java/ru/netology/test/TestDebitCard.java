package ru.netology.test;

import ru.netology.page.BuyWithCard;
import ru.netology.page.StartPage;
import com.codeborne.selenide.logevents.SelenideLogger;
import ru.netology.data.DataHelper;
import ru.netology.data.SQL;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDebitCard {
    @BeforeAll
    static void setUpAll() {

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUpEach() throws SQLException {
        String url = System.getProperty("sut.url");
        open(url);
        SQL.clearData();
    }

    @AfterAll
    static void tearDownAll() {

        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldDebitByCardWithApproved() throws SQLException {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val buyWithCard = new BuyWithCard();
        buyWithCard.fillData(DataHelper.getApprovedCard());
        buyWithCard.waitNotificationOk();
        assertEquals("APPROVED", SQL.getDebitStatus());
    }

    @Test
    void shouldDebitByCardWithDecline() throws SQLException {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val buyWithCard = new BuyWithCard();
        buyWithCard.fillData(DataHelper.getDeclinedCard());
        buyWithCard.waitNotificationError();
        assertEquals("DECLINED", SQL.getDebitStatus());
    }

    @Test
    void shouldShortNameInOwnerApproved() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val buyWithCard = new BuyWithCard();
        buyWithCard.fillData(DataHelper.getShortNameInOwnerApprovedCard());
        buyWithCard.waitNotificationOk();
    }

    @Test
    void shouldShortNameInOwnerDeclined() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getShortNameInOwnerDeclinedCard());
        debitPage.waitNotificationError();
    }

    @Test
    void shouldInvalidFieldMessageEmptyForm() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getEmptyForm());
        debitPage.getInputInvalid();
        assertEquals("???????????????? ????????????", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageInvalidMonthApproved() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getInvalidMonthApprovedCard());
        assertEquals("?????????????? ???????????? ???????? ???????????????? ??????????", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageInvalidMonthDeclined() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getInvalidMonthDeclinedCard());
        assertEquals("?????????????? ???????????? ???????? ???????????????? ??????????", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneMonthApproved() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getBygoneMonthApprovedCard());
        assertEquals("?????????????? ???????????? ???????? ???????????????? ??????????", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneMonthDeclined() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getBygoneMonthDeclinedCard());
        assertEquals("?????????????? ???????????? ???????? ???????????????? ??????????", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageIncompleteField() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getIncompleteField());
        assertEquals("???????????????? ????????????", debitPage.getInputInvalid());
    }

    @Test
    void shouldCharactersInFieldOwnerApproved() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getCharactersInFieldOwnerApprovedCard());
        assertEquals("???????????????? ????????????", debitPage.getInputInvalid());
    }

    @Test
    void shouldCharactersInFieldOwnerDeclined() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getCharactersInFieldOwnerDeclinedCard());
        assertEquals("???????????????? ????????????", debitPage.getInputInvalid());
    }

    @Test
    void shouldOneCharacterInFieldOwnerApproved() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getCharactersInFieldOwnerApprovedCard());
        assertEquals("???????????????? ????????????", debitPage.getInputInvalid());
    }

    @Test
    void shouldOneCharacterInFieldOwnerDeclined() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getOneCharacterInFieldOwnerDeclinedCard());
        assertEquals("???????????????? ????????????", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneYearApproved() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getBygoneYearApprovedCard());
        assertEquals("?????????? ???????? ???????????????? ??????????", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneYearDeclined() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getBygoneYearDeclinedCard());
        assertEquals("?????????? ???????? ???????????????? ??????????", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidDebitCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithCard();
        debitPage.fillData(DataHelper.getNonExistentCard());
        assertEquals("????????????! ???????? ?????????????? ?? ???????????????????? ????????????????.", debitPage.getInputInvalid());
    }


    @Test
    void shouldAmountByCardWithApproved() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCard();
        val buyWithCard = new BuyWithCard();
        buyWithCard.fillData(DataHelper.getApprovedCard());
        buyWithCard.waitNotificationOk();
        assertEquals("45000", SQL.getAmountStatus());
    }
}

