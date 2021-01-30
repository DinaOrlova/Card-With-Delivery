package ru.netology.web;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneOffset;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class CardDeliveryOrderDateSelectionByCalendarTest {
    private LocalDate deliveryDate;

    @BeforeEach
    void setup() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldTestSelectionByCalendar() {
        deliveryDate = LocalDate.now().plusDays(7);
        long seconds = deliveryDate.atStartOfDay().toInstant(ZoneOffset.ofHours(3)).getEpochSecond();
        long millis = seconds * 1000;
        String value = Long.toString(millis);

        System.out.println(value);

        $("[data-test-id=city] input").setValue("Нижний Новгород");
        $("[data-test-id=date] button").click();

        SelenideElement dataDay = $("tbody");
        ElementsCollection returnDate = dataDay.$$("[data-day]");

        for (SelenideElement e : returnDate) {
            if (e.has(attribute("data-day", value))) {
                e.click();
            }
        }
//        $(".calendar__arrow_direction_right").click();

        $("[data-test-id=name] input").setValue("Круглова Дарья");
        $("[data-test-id=phone] input").setValue("+79000000000");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(visible, 15000);
    }
}
