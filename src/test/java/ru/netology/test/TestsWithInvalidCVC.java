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

public class TestsWithInvalidCVC {
    public DataHelper.ValidCardDetails validCardDetails = DataHelper.getValidCardDetails();
    public DataHelper.InvalidCVC invalidCVC = DataHelper.getInvalidCVC();
    TourPurchasePage tourPurchasePage;

    @BeforeEach
    public void openPage() {
        Selenide.open("http://localhost:8080/");
        tourPurchasePage = new TourPurchasePage();
        SQLRequest.clearDB();
    }

    @Test
    public void shouldFailToPurchaseWithACardWithEmptyCVC() {
        tourPurchasePage.buyTourWithACard(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), invalidCVC.getEmptyCvc());
        String actualResult = $(byXpath("//span[text()='CVC/CVV']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Поле обязательно для заполнения";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithALoanWithEmptyCVC() {
        tourPurchasePage.buyTourWithALoan(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), invalidCVC.getEmptyCvc());
        String actualResult = $(byXpath("//span[text()='CVC/CVV']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Поле обязательно для заполнения";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithACardWithIncorrectFormCvc() {
        tourPurchasePage.buyTourWithACard(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), invalidCVC.getIncorrectFormCvc());
        String actualResult = $(byXpath("//span[text()='CVC/CVV']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверный формат";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithALoanWithIncorrectFormCvc() {
        tourPurchasePage.buyTourWithALoan(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), invalidCVC.getIncorrectFormCvc());
        String actualResult = $(byXpath("//span[text()='CVC/CVV']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверный формат";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithACardWithCvcWithText() {
        tourPurchasePage.buyTourWithACard(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), invalidCVC.getCvcWithText());
        String actualResult = $(byXpath("//span[text()='CVC/CVV']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверный формат";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithALoanWithCvcWithText() {
        tourPurchasePage.buyTourWithALoan(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), invalidCVC.getCvcWithText());
        String actualResult = $(byXpath("//span[text()='CVC/CVV']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверный формат";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }


}
