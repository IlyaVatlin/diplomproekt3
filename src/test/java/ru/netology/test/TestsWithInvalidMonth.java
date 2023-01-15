package ru.netology.test;

import com.codeborne.selenide.Selenide;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.TourPurchasePage;
import ru.netology.sql.SQLRequest;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsWithInvalidMonth {
    public DataHelper.ValidCardDetails validCardDetails = DataHelper.getValidCardDetails();
    public DataHelper.InvalidMonth invalidMonth = DataHelper.getInvalidMonth();
    TourPurchasePage tourPurchasePage;

    @BeforeEach
    public void openPage() {
        Selenide.open("http://localhost:8080/");
        tourPurchasePage = new TourPurchasePage();
        SQLRequest.clearDB();
    }

    @Test
    public void shouldFailToPurchaseWithACardWithEmptyMonth() {
        tourPurchasePage.buyTourWithACard(DataHelper.getFirstCardNumber(), invalidMonth.getEmptyMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Месяц']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Поле обязательно для заполнения";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithALoanWithEmptyMonth() {
        tourPurchasePage.buyTourWithALoan(DataHelper.getFirstCardNumber(), invalidMonth.getEmptyMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Месяц']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Поле обязательно для заполнения";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithACardWithMonthMoreTwelfth() {
        tourPurchasePage.buyTourWithACard(DataHelper.getFirstCardNumber(), invalidMonth.getMonthMoreTwelfth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Месяц']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверно указан срок действия карты";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithALoanWithMonthMoreTwelfth() {
        tourPurchasePage.buyTourWithALoan(DataHelper.getFirstCardNumber(), invalidMonth.getMonthMoreTwelfth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Месяц']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверно указан срок действия карты";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithACardWithZeroMonth() {
        tourPurchasePage.buyTourWithACard(DataHelper.getFirstCardNumber(), invalidMonth.getZeroMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Месяц']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверно указан срок действия карты";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithALoanWithZeroMonth() {
        tourPurchasePage.buyTourWithALoan(DataHelper.getFirstCardNumber(), invalidMonth.getZeroMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Месяц']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверно указан срок действия карты";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithACardWithMonthWithText() {
        tourPurchasePage.buyTourWithACard(DataHelper.getFirstCardNumber(), invalidMonth.getMonthWithText(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Месяц']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверный формат";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithALoanWithMonthWithText() {
        tourPurchasePage.buyTourWithALoan(DataHelper.getFirstCardNumber(), invalidMonth.getMonthWithText(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Месяц']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверный формат";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }
}
