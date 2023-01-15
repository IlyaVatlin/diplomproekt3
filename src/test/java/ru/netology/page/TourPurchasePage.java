package ru.netology.page;

import com.codeborne.selenide.Selectors;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class TourPurchasePage {
    public void buyTourWithACard(String  cardNumber, String month, String year, String owner, String cvcOrCvv){
        $(Selectors.byText("Купить")).click();
        $(".input__control[placeholder='0000 0000 0000 0000']").setValue(cardNumber);
        $(".input__control[placeholder='08']").setValue(month);
        $(".input__control[placeholder='22']").setValue(year);
        $(byXpath("//span[text()='Владелец']/..//input[@class='input__control']")).setValue(owner);
        $(".input__control[placeholder='999']").setValue(cvcOrCvv);
        $(withText("Продолжить")).click();
    }
    public void buyTourWithALoan(String  cardNumber, String month, String year, String owner, String cvcOrCvv){
        $(Selectors.byText("Купить в кредит")).click();
        $(".input__control[placeholder='0000 0000 0000 0000']").setValue(cardNumber);
        $(".input__control[placeholder='08']").setValue(month);
        $(".input__control[placeholder='22']").setValue(year);
        $(byXpath("//span[text()='Владелец']/..//input[@class='input__control']"))
                .setValue(owner);
        $(".input__control[placeholder='999']").setValue(cvcOrCvv);
        $(withText("Продолжить")).click();
    }

}
