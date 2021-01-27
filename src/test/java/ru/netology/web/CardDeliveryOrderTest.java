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

    @BeforeEach
    void setup() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldTestSingleNameAndSurnameCurrentDayPlusThreeDays() {
        deliveryDate = LocalDate.now().plusDays(3);
        String formatDate = deliveryDate.format(formatter);
        $("[data-test-id=city] input").setValue("Нижний Новгород");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL,"A"));
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.DELETE));
        $("[data-test-id=date] input").setValue(formatDate);
        $("[data-test-id=name] input").setValue("Круглова Дарья");
        $("[data-test-id=phone] input").setValue("+79000000000");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(visible, 15000);
    }

    @Test
    void shouldTestSingleNameDoubleSurnameCurrentDayPlusSevenDays() {
        deliveryDate = LocalDate.now().plusDays(7);
        String formatDate = deliveryDate.format(formatter);
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL,"A"));
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.DELETE));
        $("[data-test-id=date] input").setValue(formatDate);
        $("[data-test-id=name] input").setValue("Круглова-Серова Дарья");
        $("[data-test-id=phone] input").setValue("+79000000000");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(visible, 15000);
    }

    @Test
    void shouldTestDoubleNameSingleSurnameCurrentDayPlusTenDays() {
        deliveryDate = LocalDate.now().plusDays(10);
        String formatDate = deliveryDate.format(formatter);
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL,"A"));
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.DELETE));
        $("[data-test-id=date] input").setValue(formatDate);
        $("[data-test-id=name] input").setValue("Круглова Мария-Виктория");
        $("[data-test-id=phone] input").setValue("+79000000000");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(visible, 15000);
    }

    @Test
    void shouldTestDoubleNameAndSurnameCurrentDayPlus33Days() {
        deliveryDate = LocalDate.now().plusDays(33);
        String formatDate = deliveryDate.format(formatter);
        $("[data-test-id=city] input").setValue("Барнаул");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL,"A"));
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.DELETE));
        $("[data-test-id=date] input").setValue(formatDate);
        $("[data-test-id=name] input").setValue("Круглова-Серова Мария-Виктория");
        $("[data-test-id=phone] input").setValue("+79000000000");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(visible, 15000);
    }
}
