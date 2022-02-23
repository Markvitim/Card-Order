package ru.netology;


import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

public class CardOrderTest {

    @Test
    public void shouldSendForm() {
        open("http://localhost:9999");

        SelenideElement form = $("form");
        form.$(By.cssSelector("[data-test-id='name'] input")).setValue("Василий Иванов-Петров");
        form.$(By.cssSelector("[data-test-id='phone'] input")).setValue("+79253004455");
        form.$(By.cssSelector(".checkbox__box")).click();
        form.$(By.cssSelector("button")).click();
        String actual = $(By.className("Success_successBlock__2L3Cw")).getText();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        assertEquals(expected, actual.trim());
    }
    @Test
    public void shouldCheckName() {
        open("http://localhost:9999");

        SelenideElement form = $("form");
        form.$(By.cssSelector("[data-test-id='name'] input")).setValue("Ivanov");
        form.$(By.cssSelector("[data-test-id='phone'] input")).setValue("+79253004455");
        form.$(By.cssSelector(".checkbox__box")).click();
        form.$(By.cssSelector("button")).click();
        String actual = $(By.cssSelector(".input_type_text .input__sub")).getText();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        assertEquals(expected, actual.trim());
    }
    @Test
    public void shouldCheckPhone() {
        open("http://localhost:9999");

        SelenideElement form = $("form");
        form.$(By.cssSelector("[data-test-id='name'] input")).setValue("Василий Иванов-Петров");
        form.$(By.cssSelector("[data-test-id='phone'] input")).setValue(" ");
        form.$(By.cssSelector(".checkbox__box")).click();
        form.$(By.cssSelector("button")).click();
        String actual = $(By.cssSelector(".input_type_tel  .input__sub")).getText();
        String expected = "Поле обязательно для заполнения";
        assertEquals(expected, actual.trim());
    }
}
