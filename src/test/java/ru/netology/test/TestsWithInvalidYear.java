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

public class TestsWithInvalidYear {
    public DataHelper.ValidCardDetails validCardDetails = DataHelper.getValidCardDetails();
    public DataHelper.InvalidYear invalidYear = DataHelper.getInvalidYear();
    TourPurchasePage tourPurchasePage;

    @BeforeEach
    public void openPage() {
        Selenide.open("http://localhost:8080/");
        tourPurchasePage = new TourPurchasePage();
        SQLRequest.clearDB();
    }

    @Test
    public void shouldFailToPurchaseWithACardWithEmptyYear(){
        tourPurchasePage.buyTourWithACard(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                invalidYear.getEmptyYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Год']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Поле обязательно для заполнения";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }
    @Test
    public void shouldFailToPurchaseWithALoanWithEmptyYear(){
        tourPurchasePage.buyTourWithALoan(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                invalidYear.getEmptyYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Год']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Поле обязательно для заполнения";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }
    @Test
    public void shouldFailToPurchaseWithACardWithPastYear(){
        tourPurchasePage.buyTourWithACard(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                invalidYear.getPastYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Год']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Истёк срок действия карты";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }
    @Test
    public void shouldFailToPurchaseWithALoanWithPastYear(){
        tourPurchasePage.buyTourWithALoan(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                invalidYear.getPastYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Год']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Истёк срок действия карты";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }
    @Test
    public void shouldFailToPurchaseWithACardWithFutureYear(){
        tourPurchasePage.buyTourWithACard(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                invalidYear.getFutureYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Год']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверно указан срок действия карты";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }
    @Test
    public void shouldFailToPurchaseWithALoanWithFutureYear(){
        tourPurchasePage.buyTourWithALoan(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                invalidYear.getFutureYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Год']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверно указан срок действия карты";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);

    }
    @Test
    public void shouldFailToPurchaseWithACardWithYearWithText(){
        tourPurchasePage.buyTourWithACard(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                invalidYear.getYearWithText(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Год']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверный формат";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }
    @Test
    public void shouldFailToPurchaseWithALoanWithYearWithText(){
        tourPurchasePage.buyTourWithALoan(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                invalidYear.getYearWithText(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        String actualResult = $(byXpath("//span[text()='Год']/..//span[@class = 'input__sub']")).getText();
        String expectedResult = "Неверный формат";
        assertEquals(expectedResult, actualResult);
        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }


}
