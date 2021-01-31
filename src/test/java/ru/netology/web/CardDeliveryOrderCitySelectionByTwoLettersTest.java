package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class CardDeliveryOrderCitySelectionByTwoLettersTest {
    private LocalDate deliveryDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public void selectDatePlusDaysFromCurrent(int days) {
        deliveryDate = LocalDate.now().plusDays(days);
        String formatDate = deliveryDate.format(formatter);
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL,"A"));
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.DELETE));
        $("[data-test-id=date] input").setValue(formatDate);
    }

    @BeforeEach
    void setup() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldTestFirstCapitalLetterSecondLowercaseLetter() {
        $("[data-test-id=city] input").setValue("Но");
        $$(".popup span").find(exactText("Новосибирск")).click();
        selectDatePlusDaysFromCurrent(5);
        $("[data-test-id=name] input").setValue("Круглова Дарья");
        $("[data-test-id=phone] input").setValue("+79000000000");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(visible, 15000);
    }

    @Test
    void shouldTestBothLettersLowercase() {
        $("[data-test-id=city] input").setValue("ка");
        $$(".popup span").find(exactText("Калуга")).click();
        selectDatePlusDaysFromCurrent(5);
        $("[data-test-id=name] input").setValue("Круглова Дарья");
        $("[data-test-id=phone] input").setValue("+79000000000");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(visible, 15000);
    }

    @Test
    void shouldTestBothLettersCapital() {
        $("[data-test-id=city] input").setValue("ЛО");
        $$(".popup span").find(exactText("Вологда")).click();
        selectDatePlusDaysFromCurrent(5);
        $("[data-test-id=name] input").setValue("Круглова Дарья");
        $("[data-test-id=phone] input").setValue("+79000000000");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(visible, 15000);
    }

    @Test
    void shouldTestFirstLowercaseLetterSecondCapitalLetter() {
        $("[data-test-id=city] input").setValue("оМ");
        $$(".popup span").find(exactText("Омск")).click();
        selectDatePlusDaysFromCurrent(5);
        $("[data-test-id=name] input").setValue("Круглова Дарья");
        $("[data-test-id=phone] input").setValue("+79000000000");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(visible, 15000);
    }
}
