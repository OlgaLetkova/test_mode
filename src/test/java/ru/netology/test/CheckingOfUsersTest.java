package ru.netology.test;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CheckingOfUsersTest {

    @Test
    void shouldCheckOfUserAuthorization() {
        DataGenerator.generateUser("IvanMarkov", "leto38.15", "active");
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[name='login']").setValue("IvanMarkov");
        form.$("[name='password']").setValue("leto38.15");
        form.$(".button, [data-test-id='action-login']").click();
    }

    @Test
    void shouldCheckStatusOfUser() {
        DataGenerator.generateUser("AlexPetrov", "agent07", "blocked");
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[name='login']").setValue("AlexPetrov");
        form.$("[name='password']").setValue("agent07");
        form.$(".button, [data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldHave(text("Ошибка! " + "Пользователь заблокирован"));
    }

    @Test
    void shouldCheckInvalidLogin() {
        DataGenerator.generateUser("YuriiCet", "west", "active");
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[name='login']").setValue("ValeraPushkin");
        form.$("[name='password']").setValue("west");
        form.$(".button, [data-test-id='action-login']").click();
        $("[data-test-id='error-notification']")
                .shouldHave(text("Ошибка! " + "Неверно указан логин или пароль"));
    }
}
