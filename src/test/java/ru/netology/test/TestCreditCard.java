package ru.netology.test;

import ru.netology.page.BuyWithCard;
import ru.netology.page.BuyWithCredit;
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


public class TestCreditCard {
    @BeforeAll
    static void setUpAll() {

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUpEach() {
        String url = System.getProperty("sut.url");
        open(url);
//        SQL.clearData();
    }

    @AfterAll
    static void tearDownAll() {

        SelenideLogger.removeListener("allure");
    }


    @Test
    void shouldCreditByCardWithStatusApproved() throws SQLException {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getApprovedCard());
        buyWithCredit.waitNotificationOk();
        String actual = SQL.getCreditStatus();
        assertEquals("APPROVED", actual);
    }

    @Test
    void shouldCreditByCardWithStatusDecline() throws SQLException {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getDeclinedCard());
        buyWithCredit.waitNotificationError();
        assertEquals("DECLINED", SQL.getCreditStatus());
    }

    @Test
    void shouldShortNameInOwnerApproved() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getShortNameInOwnerApprovedCard());
        buyWithCredit.waitNotificationOk();
    }

    @Test
    void shouldShortNameInOwnerDeclined() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getShortNameInOwnerDeclinedCard());
        buyWithCredit.waitNotificationError();
    }

    @Test
    void shouldInvalidFieldMessageEmptyForm() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getEmptyForm());
        buyWithCredit.getInputInvalid();
        assertEquals("???????????????? ????????????", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageInvalidMonthApproved() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getInvalidMonthApprovedCard());
        assertEquals("?????????????? ???????????? ???????? ???????????????? ??????????", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageInvalidMonthDeclined() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getInvalidMonthDeclinedCard());
        assertEquals("?????????????? ???????????? ???????? ???????????????? ??????????", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneMonthApproved() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getBygoneMonthApprovedCard());
        assertEquals("?????????????? ???????????? ???????? ???????????????? ??????????", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneMonthDeclined() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getBygoneMonthDeclinedCard());
        assertEquals("?????????????? ???????????? ???????? ???????????????? ??????????", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageIncompleteField() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getIncompleteField());
        assertEquals("???????????????? ????????????", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldCharactersInFieldOwnerApproved() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getCharactersInFieldOwnerApprovedCard());
        assertEquals("???????????????? ????????????", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldCharactersInFieldOwnerDeclined() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getCharactersInFieldOwnerDeclinedCard());
        assertEquals("???????????????? ????????????", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldOneCharacterInFieldOwnerApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getOneCharacterInFieldOwnerApprovedCard());
        assertEquals("???????????????? ????????????", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldOneCharacterInFieldOwnerDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getOneCharacterInFieldOwnerDeclinedCard());
        assertEquals("???????????????? ????????????", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneYearApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getBygoneYearApprovedCard());
        assertEquals("?????????? ???????? ???????????????? ??????????", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneYearDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getBygoneYearDeclinedCard());
        assertEquals("?????????? ???????? ???????????????? ??????????", buyWithCredit.getInputInvalid());
    }

}

