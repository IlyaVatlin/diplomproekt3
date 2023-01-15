package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.page.TourPurchasePage;
import ru.netology.sql.SQLRequest;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsWithValidData {
    public DataHelper.ValidCardDetails validCardDetails = DataHelper.getValidCardDetails();
    TourPurchasePage tourPurchasePage;
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
    @BeforeEach
    public void openPage() {
        Selenide.open("http://localhost:8080/");
        tourPurchasePage = new TourPurchasePage();
    }

    @AfterEach
    void cleanDB() {
        SQLRequest.clearDB();
    }

    @Test
    public void shouldPurchaseTourWithCard() {
        tourPurchasePage.buyTourWithACard(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        $(withText("Операция одобрена Банком.")).shouldBe(Condition.visible, Duration.ofSeconds(30));

        val expectedStatus = DataHelper.getFirstCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldPurchaseTourWithCardWithOwnerLowercase() {
        tourPurchasePage.buyTourWithACard(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwnerWithLowercase(),
                validCardDetails.getValidCvcOrCvv());
        $(withText("Операция одобрена Банком.")).shouldBe(Condition.visible, Duration.ofSeconds(30));
        val expectedStatus = DataHelper.getFirstCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldPurchaseTourWithCardWithOwnerUppercase() {
        tourPurchasePage.buyTourWithACard(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwnerWithUppercase(),
                validCardDetails.getValidCvcOrCvv());
        $(withText("Операция одобрена Банком.")).shouldBe(Condition.visible, Duration.ofSeconds(30));
        val expectedStatus = DataHelper.getFirstCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldPurchaseTourWithALoan() {
        tourPurchasePage.buyTourWithALoan(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwner(), validCardDetails.getValidCvcOrCvv());
        $(withText("Операция одобрена Банком.")).shouldBe(Condition.visible, Duration.ofSeconds(30));
        val expectedStatus = DataHelper.getFirstCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldPurchaseTourWithALoanWithOwnerLowercase() {
        tourPurchasePage.buyTourWithALoan(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwnerWithLowercase(), validCardDetails.getValidCvcOrCvv());
        $(withText("Операция одобрена Банком.")).shouldBe(Condition.visible, Duration.ofSeconds(30));
        val expectedStatus = DataHelper.getFirstCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldPurchaseTourWithALoanWithOwnerUppercase() {
        tourPurchasePage.buyTourWithALoan(DataHelper.getFirstCardNumber(), validCardDetails.getValidMonth(),
                validCardDetails.getValidYear(), validCardDetails.getValidOwnerWithUppercase(), validCardDetails.getValidCvcOrCvv());
        $(withText("Операция одобрена Банком.")).shouldBe(Condition.visible, Duration.ofSeconds(30));
        val expectedStatus = DataHelper.getFirstCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

}
