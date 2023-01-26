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
    private BuyWithCard buyWithCard;
    private BuyWithCard debitPage;
    
    @BeforeAll
    static void setUpAll() {

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUpEach() {
        String url = System.getProperty("sut.url");
        open(url);
        SQL.clearTables();
    }

    @AfterAll
    static void tearDownAll() {

        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldDebitByCardWithApproved()  {
        StartPage startPage = new StartPage();
        val buyWithCredit = startPage.openBuyWithCard();
        buyWithCard.fillData(DataHelper.getApprovedCard());
        buyWithCard.waitNotificationOk();
        assertEquals("APPROVED", SQL.getDebitStatus());
    }

    @Test
    void shouldDebitByCardWithDecline()  {
        StartPage startPage = new StartPage();
        val buyWithCredit = startPage.openBuyWithCard();
        buyWithCard.fillData(DataHelper.getDeclinedCard());
        buyWithCard.waitNotificationError();
        assertEquals("DECLINED", SQL.getDebitStatus());
    }

    @Test
    void shouldShortNameInOwnerApproved() {
        StartPage startPage = new StartPage();
        val buyWithCredit = startPage.openBuyWithCard();
        buyWithCard.fillData(DataHelper.getShortNameInOwnerApprovedCard());
        buyWithCard.waitNotificationOk();
    }

    @Test
    void shouldShortNameInOwnerDeclined() {
        StartPage startPage = new StartPage();
        val buyWithCredit = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getShortNameInOwnerDeclinedCard());
        debitPage.waitNotificationError();
    }

    @Test
    void shouldInvalidFieldMessageEmptyForm() {
        StartPage startPage = new StartPage();
        val buyWithCredit = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getEmptyForm());
        assertEquals(0, SQL.getRowsDebitPurchase());
    }

    @Test
    void shouldInvalidFieldMessageInvalidMonthApproved() {
        StartPage startPage = new StartPage();
        val buyWithCredit = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getInvalidMonthApprovedCard());
        assertEquals(0, SQL.getRowsDebitPurchase());
    }

    @Test
    void shouldInvalidFieldMessageInvalidMonthDeclined() {
        StartPage startPage = new StartPage();
        val buyWithCredit = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getInvalidMonthDeclinedCard());
        assertEquals(0, SQL.getRowsDebitPurchase());
    }

    @Test
    void shouldInvalidFieldMessageBygoneMonthApproved() {
        StartPage startPage = new StartPage();
        val buyWithCredit = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getBygoneMonthApprovedCard());
        assertEquals(0, SQL.getRowsDebitPurchase());
    }

    @Test
    void shouldInvalidFieldMessageBygoneMonthDeclined() {
        StartPage startPage = new StartPage();
        val buyWithCredit = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getBygoneMonthDeclinedCard());
        assertEquals(0, SQL.getRowsDebitPurchase());
    }

    @Test
    void shouldInvalidFieldMessageIncompleteField() {
        StartPage startPage = new StartPage();
        val buyWithCredit = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getIncompleteField());
        assertEquals(0, SQL.getRowsDebitPurchase());
    }

    @Test
    void shouldCharactersInFieldOwnerApproved() {
        StartPage startPage = new StartPage();
        val buyWithCredit = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getCharactersInFieldOwnerApprovedCard());
        assertEquals(0, SQL.getRowsDebitPurchase());
    }

    @Test
    void shouldCharactersInFieldOwnerDeclined() {
        StartPage startPage = new StartPage();
        val buyWithCredit = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getCharactersInFieldOwnerDeclinedCard());
        assertEquals(0, SQL.getRowsDebitPurchase());
    }

    @Test
    void shouldOneCharacterInFieldOwnerApproved() {
        StartPage startPage = new StartPage();
        val buyWithCredit = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getCharactersInFieldOwnerApprovedCard());
        assertEquals(0, SQL.getRowsDebitPurchase());
    }

    @Test
    void shouldOneCharacterInFieldOwnerDeclined() {
        StartPage startPage = new StartPage();
        val buyWithCredit = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getOneCharacterInFieldOwnerDeclinedCard());
        assertEquals(0, SQL.getRowsDebitPurchase());
    }

    @Test
    void shouldInvalidFieldMessageBygoneYearApproved() {
        StartPage startPage = new StartPage();
        val buyWithCredit = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getBygoneYearApprovedCard());
        assertEquals(0, SQL.getRowsDebitPurchase());
    }

    @Test
    void shouldInvalidFieldMessageBygoneYearDeclined() {
        StartPage startPage = new StartPage();
        val buyWithCredit = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getBygoneYearDeclinedCard());
        assertEquals(0, SQL.getRowsDebitPurchase());
    }

    @Test
    void shouldInvalidDebitCard() {
        StartPage startPage = new StartPage();
        val buyWithCredit = startPage.openBuyWithCard();
        debitPage.fillData(DataHelper.getNonExistentCard());
        assertEquals(0, SQL.getRowsDebitPurchase());
    }


    @Test
    void shouldAmountByCardWithApproved() {
        StartPage startPage = new StartPage();
        val buyWithCredit = startPage.openBuyWithCard();
        buyWithCard.fillData(DataHelper.getApprovedCard());
        buyWithCard.waitNotificationOk();
        assertEquals("45000", SQL.getAmountStatus());
    }
}
