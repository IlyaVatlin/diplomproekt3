package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private static final String FIRST_CARD_NUMBER = "4444 4444 4444 4441";
    private static final String SECOND_CARD_NUMBER = "4444 4444 4444 4442";
    private static final String FIRST_CARD_STATUS = "APPROVED";
    private static final String SECOND_CARD_STATUS = "DECLINED";
    private static final String EMPTY_CARD_STATUS = null;

    private DataHelper() {
    }

    public static String getFirstCardNumber() {
        return FIRST_CARD_NUMBER;
    }

    public static String getSecondCardNumber() {
        return SECOND_CARD_NUMBER;
    }

    public static String getFirstCardStatus() {
        return FIRST_CARD_STATUS;
    }

    public static String getSecondCardStatus() {
        return SECOND_CARD_STATUS;
    }
    public static String getEmptyCardStatus() {
        return EMPTY_CARD_STATUS;
    }

    @Value
    public static class ValidCardDetails {
        private String validMonth;
        private String validYear;
        private String validOwner;
        private String validOwnerWithLowercase;
        private String validOwnerWithUppercase;
        private String validCvcOrCvv;
    }

    public static ValidCardDetails getValidCardDetails() {
        Faker faker = new Faker(new Locale("en"));
        String validMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String validYear = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String validOwner = faker.name().firstName() + " " + faker.name().lastName();
        String validOwnerWithLowercase = faker.name().firstName().toLowerCase(Locale.ROOT) + " "
                + faker.name().lastName().toLowerCase(Locale.ROOT);
        String validOwnerWithUppercase = faker.name().firstName().toUpperCase(Locale.ROOT) + " "
                + faker.name().lastName().toUpperCase(Locale.ROOT);
        String validCvcOrCvv = faker.number().digits(3);

        return new ValidCardDetails(validMonth, validYear, validOwner, validOwnerWithLowercase,
                validOwnerWithUppercase,validCvcOrCvv);
    }

    @Value
    public static class InvalidCardNumbers {
        private String emptyCardNumber;
        private String randomCardNumber;
        private String cardNumberWithOneDigit;
        private String cardNumberWithFifteenDigits;
        private String cardNumberWithTextAndChars;
    }

    public static InvalidCardNumbers getInvalidCardNumber() {
        Faker faker = new Faker(new Locale("en"));
        String emptyCardNumber = " ";
        String randomCardNumber = faker.business().creditCardNumber();
        String cardNumberWithOneDigit = faker.number().digit();
        String cardNumberWithFifteenDigits = faker.number().digits(15);
        String cardNumberWithTextAndChars = "номер текстом и с !:?*";
        return new InvalidCardNumbers(emptyCardNumber, randomCardNumber, cardNumberWithOneDigit,
                cardNumberWithFifteenDigits, cardNumberWithTextAndChars);
    }

    @Value
    public static class InvalidMonth {
        private String emptyMonth;
        private String monthMoreTwelfth;
        private String zeroMonth;
        private String monthWithText;
    }

    public static InvalidMonth getInvalidMonth() {
        String emptyMonth = " ";
        String monthMoreTwelfth = "13";
        String zeroMonth = "00";
        String monthWithText = "январь";
        return new InvalidMonth(emptyMonth, monthMoreTwelfth, zeroMonth, monthWithText);
    }

    @Value
    public static class InvalidYear {
        private String emptyYear;
        private String pastYear;
        private String futureYear;
        private String yearWithText;
    }

    public static InvalidYear getInvalidYear() {
        String emptyYear = " ";
        String pastYear = LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
        String futureYear = LocalDate.now().plusYears(20).format(DateTimeFormatter.ofPattern("yy"));
        String yearWithText = "двадцать четвертый";
        return new InvalidYear(emptyYear, pastYear, futureYear, yearWithText);
    }

    @Value
    public static class InvalidOwner {
        private String emptyOwner;
        private String ownerWrittenInCyrillic;
        private String ownerWithOnlyFirstName;
        private String ownerWithDigits;
        private String ownerWithSpecialChars;
    }

    public static InvalidOwner getInvalidOwner() {
        Faker fakerRu = new Faker(new Locale("ru"));
        Faker fakerEn = new Faker(new Locale("en"));
        String emptyOwner = " ";
        String ownerWrittenInCyrillic = fakerRu.name().fullName();
        String ownerWithOnlyFirstName = fakerEn.name().firstName();
        String ownerWithDigits = fakerEn.number().digits(3);
        String ownerWithSpecialChars = "!@#$%^";
        return new InvalidOwner(emptyOwner, ownerWrittenInCyrillic, ownerWithOnlyFirstName, ownerWithDigits, ownerWithSpecialChars);
    }

    @Value
    public static class InvalidCVC {
        private String emptyCvc;
        private String incorrectFormCvc;
        private String cvcWithText;
    }

    public static InvalidCVC getInvalidCVC() {
        String emptyCvc = " ";
        String incorrectFormCvc = "12";
        String cvcWithText = "code";
        return new InvalidCVC(emptyCvc, incorrectFormCvc, cvcWithText);
    }
}

