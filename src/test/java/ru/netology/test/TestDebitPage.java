package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlUtils;
import ru.netology.page.DebitPage;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDebitPage {


    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
    @BeforeEach
    public void setUp() {
        SqlUtils.clearTables();
        open("http://localhost:8080");
    }

    @Test
    void shouldApprovedOperationUsingApprCard() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getApprovedCardInfo());
        debitPage.checkSuccessNotif();
        assertEquals("APPROVED", SqlUtils.getStatusDebitPurchase());
        assertEquals(1, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldDeclineOperationUsingDeclCard() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getDeclinedCardInfo());
        debitPage.checkFailedNotif();
        assertEquals("DECLINED", SqlUtils.getStatusDebitPurchase());
        assertEquals(1, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenLeaveFieldsEmpty() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getAllEmptyFields());
        debitPage.checkEmptyForm();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenShortCardNumberUsed() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getShotCardNumber());
        debitPage.checkCardNumberFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenUsedEnAlphabCardNumber() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getEnAlphabCardNumber());
        debitPage.checkCardNumberFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenUsedSymbolCardNumber() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getSymbolCardNumber());
        debitPage.checkCardNumberFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenUsedRuAlphabCardNumber() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getRussAlphabCardNumber());
        debitPage.checkCardNumberFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenLeaveCardNumberEmpty() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getEmptyCardNumber());
        debitPage.checkCardNumberFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenLeaveMonthEmpty() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getEmptyMonth());
        debitPage.checkMonthFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenUsedInvalidMonth() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getInvalidMonth());
        debitPage.checkInValMonthFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenUsedEnAlphabMonth() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getEnAlphabMonth());
        debitPage.checkMonthFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldUGetErrorWhenUsedRuAlphabMonth() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getRusAlphabMonth());
        debitPage.checkMonthFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenUsedSymbolMonth() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getSymbMonth());
        debitPage.checkMonthFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenUsedOneDigitMonth() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getOntDigitMonth());
        debitPage.checkMonthFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenLeaveYearEmpty() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getEmptyYear());
        debitPage.checkYearFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenUsedPastYear() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getPastYear());
        debitPage.checkYearFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenUsedEnAlphabYear() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getEnAlphabYear());
        debitPage.checkYearFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenUsedRuAlphabYear() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getRusAlphabYear());
        debitPage.checkYearFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenLeaveOwnerEmpty() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getEmptyCardOwner());
        debitPage.checkOwnerFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenUsedSymbolOwner() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getSymbolsCardOwner());
        debitPage.checkOwnerFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenUsedNumericOwner() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getNumericCardOwner());
        debitPage.checkOwnerFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenLeaveCvcEmpty() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getEmptyCvc());
        debitPage.checkCVCFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenInputIncorrectCardNum() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getIncorrectCardNumber());
        debitPage.checkCardNumberFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenInputIncorrectMonth() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getZeroMonth());
        debitPage.checkMonthFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenInputIncorrectYear() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getIncorrectYear());
        debitPage.checkYearFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenInputIncorrectOwner() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getIncorrectCardOwner());
        debitPage.checkOwnerFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenInputIncorrectCvc() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getTwoDigitCvc());
        debitPage.checkCVCFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    @Test
    void shouldGetErrorWhenInputOneDigitCvc() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getOneDigitCvc());
        debitPage.checkCVCFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }
}
