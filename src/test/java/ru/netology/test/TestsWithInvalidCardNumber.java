package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.TourPurchasePage;
import ru.netology.sql.SQLRequest;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsWithInvalidCardNumber {
    public DataHelper.ValidCardDetails validCardDetails = DataHelper.getValidCardDetails();
    public DataHelper.InvalidCardNumbers invalidCardNumbers = DataHelper.getInvalidCardNumber();
    TourPurchasePage tourPurchasePage;

    @BeforeEach
    public void openPage() {
        Selenide.open("http://localhost:8080/");
        tourPurchasePage = new TourPurchasePage();
        SQLRequest.clearDB();
    }

    @Test
    public void shouldFailToPurchaseWithCardWithEmptyCardNumber() {
        tourPurchasePage.buyTourWithACard(invalidCardNumbers.getEmptyCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Номер карты']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Поле обязательно для заполнения";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithALoanWithEmptyCardNumber() {
        tourPurchasePage.buyTourWithALoan(invalidCardNumbers.getEmptyCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Номер карты']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Поле обязательно для заполнения";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithCardWithInvalidCardNumber() {
        tourPurchasePage.buyTourWithACard(DataHelper.getSecondCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        $(withText("Ошибка! Банк отказал в проведении операции.")).
                shouldBe(Condition.visible, Duration.ofSeconds(30));
        val expectedStatus = DataHelper.getSecondCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithALoanWithInvalidCardNumber() {
        tourPurchasePage.buyTourWithALoan(DataHelper.getSecondCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        $(withText("Ошибка! Банк отказал в проведении операции.")).
                shouldBe(Condition.visible, Duration.ofSeconds(30));
        val expectedStatus = DataHelper.getSecondCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithCardWithRandomCardNumber() {
        tourPurchasePage.buyTourWithACard(invalidCardNumbers.getRandomCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        $(withText("Ошибка! Банк отказал в проведении операции.")).
                shouldBe(Condition.visible, Duration.ofSeconds(20));
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithALoanWithRandomCardNumber() {
        tourPurchasePage.buyTourWithALoan(invalidCardNumbers.getRandomCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        $(withText("Ошибка! Банк отказал в проведении операции.")).
                shouldBe(Condition.visible, Duration.ofSeconds(20));
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);

    }

    @Test
    public void shouldFailToPurchaseWithACardWithNumberWithOneDigit() {
        tourPurchasePage.buyTourWithACard(invalidCardNumbers.getCardNumberWithOneDigit(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Номер карты']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверный формат";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithALoanWithNumberWithOneDigit() {
        tourPurchasePage.buyTourWithALoan(invalidCardNumbers.getCardNumberWithOneDigit(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Номер карты']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверный формат";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithACardWithNumberWithFifteenDigits() {
        tourPurchasePage.buyTourWithACard(invalidCardNumbers.getCardNumberWithFifteenDigits(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Номер карты']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверный формат";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithALoanWithNumberWithFifteenDigits() {
        tourPurchasePage.buyTourWithALoan(invalidCardNumbers.getCardNumberWithFifteenDigits(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Номер карты']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверный формат";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);

    }

    @Test
    public void shouldFailToPurchaseWithACardWithNumberWithTextAndChars() {
        tourPurchasePage.buyTourWithACard(invalidCardNumbers.getCardNumberWithTextAndChars(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Номер карты']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверный формат";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithALoanWithNumberWithTextAndChars() {
        tourPurchasePage.buyTourWithALoan(invalidCardNumbers.getCardNumberWithTextAndChars(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Номер карты']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверный формат";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }
}
