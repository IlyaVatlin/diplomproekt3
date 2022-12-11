package data;

import com.github.javafaker.Faker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Random;

public class DataHelper {
    private DataHelper(){
    }
    private static String getMonth() {
        LocalDate localDate = LocalDate.now();
        int month = localDate.getMonthValue();
        return String.format("%02d", month);
    }

    private static String getBygoneMonth() {
        LocalDate localDate = LocalDate.now();
        int month = localDate.minusMonths(1).getMonthValue();
        return String.format("%02d", month);
    }

    private static String getYear() {
        DateFormat df = new SimpleDateFormat("yy");
        return df.format(Calendar.getInstance().getTime());
    }

    private static String getBygoneYear() {
        LocalDate localDate = LocalDate.now();
        int year = localDate.minusYears(1).getYear();
        return String.format("%02d", year);
    }

    private static String getName() {
        Faker faker = new Faker();
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    private static String getCvc() {
        Random random = new Random();
        int cvc = random.nextInt((1000 - 1));
        return String.format("%03d", cvc);
    }

    private static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    private static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static CardInfo getNonExistentCard() {
        return new CardInfo ("7777 7777 7777 7777", getMonth(), getYear(), getName(), getCvc());
    }

    public static CardInfo getApprovedCard() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), getName(), getCvc());
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo(getDeclinedCardNumber(), getMonth(), getYear(), getName(), getCvc());
    }

    public static CardInfo getShortNameInOwnerApprovedCard() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), "In", getCvc());
    }

    public static CardInfo getShortNameInOwnerDeclinedCard() {
        return new CardInfo(getDeclinedCardNumber(), getMonth(), getYear(), "In", getCvc());
    }

    public static CardInfo getEmptyForm() {
        return new CardInfo();
    }

    public static CardInfo getInvalidMonthApprovedCard() {
        return new CardInfo(getApprovedCardNumber(), "13", getYear(), getName(), getCvc());
    }

    public static CardInfo getInvalidMonthDeclinedCard() {
        return new CardInfo(getDeclinedCardNumber(), "13", getYear(), getName(), getCvc());
    }

    public static CardInfo getBygoneMonthApprovedCard() {
        return new CardInfo(getApprovedCardNumber(), getBygoneMonth(), getYear(), getName(), getCvc());
    }

    public static CardInfo getBygoneMonthDeclinedCard() {
        return new CardInfo(getDeclinedCardNumber(), getBygoneMonth(), getYear(), getName(), getCvc());
    }

    public static CardInfo getIncompleteField() {
        return new CardInfo("4444 4444 4444 444", "1", "2", "A", "11");
    }

    public static CardInfo getCharactersInFieldOwnerApprovedCard() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), "<#%^*&$@>", getCvc());
    }

    public static CardInfo getCharactersInFieldOwnerDeclinedCard() {
        return new CardInfo(getDeclinedCardNumber(), getMonth(), getYear(), "<#%^*&$@>", getCvc());
    }

    public static CardInfo getOneCharacterInFieldOwnerApprovedCard() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), "I", getCvc());
    }

    public static CardInfo getOneCharacterInFieldOwnerDeclinedCard() {
        return new CardInfo(getDeclinedCardNumber(), getMonth(), getYear(), "I", getCvc());
    }

    public static CardInfo getBygoneYearApprovedCard() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getBygoneYear(), getName(), getCvc());
    }

    public static CardInfo getBygoneYearDeclinedCard() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getBygoneYear(), getName(), getCvc());
    }
}
