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

public class TestsWithInvalidOwner {
    public DataHelper.ValidCardDetails validCardDetails = DataHelper.getValidCardDetails();
    public DataHelper.InvalidOwner invalidOwner = DataHelper.getInvalidOwner();
    TourPurchasePage tourPurchasePage;

    @BeforeEach
    public void openPage() {
        Selenide.open("http://localhost:8080/");
        tourPurchasePage = new TourPurchasePage();
        SQLRequest.clearDB();
    }

    @Test
    public void shouldFailToPurchaseWithACardWithEmptyOwner() {
        tourPurchasePage.buyTourWithACard(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), invalidOwner.getEmptyOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Владелец']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Поле обязательно для заполнения";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithALoanWithEmptyOwner() {
        tourPurchasePage.buyTourWithALoan(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), invalidOwner.getEmptyOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Владелец']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Поле обязательно для заполнения";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithACardWithOwnerWrittenInCyrillic() {
        tourPurchasePage.buyTourWithACard(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), invalidOwner.getOwnerWrittenInCyrillic(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Владелец']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверно указан владелец";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithALoanWithOwnerWrittenInCyrillic() {
        tourPurchasePage.buyTourWithALoan(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), invalidOwner.getOwnerWrittenInCyrillic(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Владелец']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверно указан владелец";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithACardWithOwnerWithOnlyFirstName() {
        tourPurchasePage.buyTourWithACard(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), invalidOwner.getOwnerWithOnlyFirstName(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Владелец']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверно указан владелец";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithALoanWithOwnerWithOnlyFirstName() {
        tourPurchasePage.buyTourWithALoan(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), invalidOwner.getOwnerWithOnlyFirstName(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Владелец']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверно указан владелец";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithACardWithOwnerWithDigits() {
        tourPurchasePage.buyTourWithACard(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), invalidOwner.getOwnerWithDigits(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Владелец']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверный формат";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithALoanWithOwnerWithDigits() {
        tourPurchasePage.buyTourWithALoan(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), invalidOwner.getOwnerWithDigits(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Владелец']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверный формат";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithACardWithSpecialChars() {
        tourPurchasePage.buyTourWithACard(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), invalidOwner.getOwnerWithSpecialChars(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Владелец']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверный формат";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithALoanWithSpecialChars() {
        tourPurchasePage.buyTourWithALoan(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), invalidOwner.getOwnerWithSpecialChars(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Владелец']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверный формат";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

}
