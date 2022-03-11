package ru.netology;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {

    @Test
    public void shouldSendForm() {
        open("http://localhost:9999");

        SelenideElement form = $("form");
        form.$(By.cssSelector("[data-test-id='name'] input")).setValue("Василий Иванов-Петров");
        form.$(By.cssSelector("[data-test-id='phone'] input")).setValue("+79253004455");
        form.$(By.cssSelector(".checkbox__box")).click();
        form.$(By.cssSelector("button")).click();
        $(By.cssSelector("[data-test-id='order-success']")).shouldHave(Condition.text("Ваша заявка успешно отправлена! " +
                "Наш менеджер свяжется с вами в ближайшее время"));
    }

    @Test
    public void shouldCheckNameLatin() {
        open("http://localhost:9999");

        SelenideElement form = $("form");
        form.$(By.cssSelector("[data-test-id='name'] input")).setValue("Ivanov");
        form.$(By.cssSelector("[data-test-id='phone'] input")).setValue("+79253004455");
        form.$(By.cssSelector(".checkbox__box")).click();
        form.$(By.cssSelector("button")).click();
        $(By.cssSelector("[data-test-id=\"name\"].input_invalid .input__sub")).shouldHave(Condition.text("Допустимы только русские " +
                "буквы, пробелы"));
    }

    @Test
    public void shouldCheckNameEmpty() {
        open("http://localhost:9999");

        SelenideElement form = $("form");
        form.$(By.cssSelector("[data-test-id='name'] input")).setValue("");
        form.$(By.cssSelector("[data-test-id='phone'] input")).setValue("+79253004455");
        form.$(By.cssSelector(".checkbox__box")).click();
        form.$(By.cssSelector("button")).click();
        $(By.cssSelector("[data-test-id=\"name\"].input_invalid .input__sub")).shouldHave(Condition.text("обязательно для заполнения"));
    }


    @Test
    public void shouldCheckPhoneEmpty() {
        open("http://localhost:9999");

        SelenideElement form = $("form");
        form.$(By.cssSelector("[data-test-id='name'] input")).setValue("Василий Иванов-Петров");
        form.$(By.cssSelector("[data-test-id='phone'] input")).setValue("");
        form.$(By.cssSelector(".checkbox__box")).click();
        form.$(By.cssSelector("button")).click();
        $(By.cssSelector("[data-test-id=\"phone\"].input_invalid .input__sub")).shouldHave(Condition.text("обязательно для заполнения"));
    }

    @Test
    public void shouldCheckPhoneNotFull() {
        open("http://localhost:9999");

        SelenideElement form = $("form");
        form.$(By.cssSelector("[data-test-id='name'] input")).setValue("Василий Иванов-Петров");
        form.$(By.cssSelector("[data-test-id='phone'] input")).setValue("+7 ");
        form.$(By.cssSelector(".checkbox__box")).click();
        form.$(By.cssSelector("button")).click();
        $(By.cssSelector("[data-test-id=\"phone\"].input_invalid .input__sub ")).shouldHave(Condition.text("Должно быть 11 цифр"));
    }

    @Test
    public void shouldCheckBox() {
        open("http://localhost:9999");

        SelenideElement form = $("form");
        form.$(By.cssSelector("[data-test-id='name'] input")).setValue("Василий Иванов-Петров");
        form.$(By.cssSelector("[data-test-id='phone'] input")).setValue("+78985236547");
        form.$(By.cssSelector("button")).click();
        $(By.cssSelector(".input_invalid .checkbox__text")).shouldBe(Condition.text(" с условиями обработки и использования "));
    }

}
