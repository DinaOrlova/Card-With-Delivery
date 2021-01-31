package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrderTest {
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
    void shouldTestSingleNameAndSurnameCurrentDayPlusThreeDays() {
        $("[data-test-id=city] input").setValue("Нижний Новгород");
        selectDatePlusDaysFromCurrent(3);
        $("[data-test-id=name] input").setValue("Круглова Дарья");
        $("[data-test-id=phone] input").setValue("+79000000000");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(visible, 15000);
    }

    @Test
    void shouldTestSingleNameDoubleSurnameCurrentDayPlusSevenDays() {
        $("[data-test-id=city] input").setValue("Казань");
        selectDatePlusDaysFromCurrent(7);
        $("[data-test-id=name] input").setValue("Круглова-Серова Дарья");
        $("[data-test-id=phone] input").setValue("+79000000000");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(visible, 15000);
    }

    @Test
    void shouldTestDoubleNameSingleSurnameCurrentDayPlusTenDays() {
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        selectDatePlusDaysFromCurrent(10);
        $("[data-test-id=name] input").setValue("Круглова Мария-Виктория");
        $("[data-test-id=phone] input").setValue("+79000000000");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(visible, 15000);
    }

    @Test
    void shouldTestDoubleNameAndSurnameCurrentDayPlus33Days() {
        $("[data-test-id=city] input").setValue("Барнаул");
        selectDatePlusDaysFromCurrent(33);
        $("[data-test-id=name] input").setValue("Круглова-Серова Мария-Виктория");
        $("[data-test-id=phone] input").setValue("+79000000000");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(visible, 15000);
    }
}
