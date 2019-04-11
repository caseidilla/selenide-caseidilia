package com.codet.caseidilia.selenide;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.jupiter.api.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.swing.*;
import java.util.Random;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Tests {
    String loginReg = "prod-test-loupa" + new Random().nextInt();
//    String login = "prod-test-loupa1241228565";
    String login = "poupa";


    private void init() {
        // very generic. such development wow
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Dropbox\\8sem\\masl\\selenium-caseidilia\\src\\main\\resources\\chrome\\chromedriver.exe");
        System.setProperty("selenide.browser", "Chrome");
    }

    //Проверяет элементы и валидацию на странице логина
    @Test
    @Order(0)
    public void loginPageCheckItems() {
        init();
        open("http://localhost:4200/");
        $(By.xpath("//*[@placeholder=\"Login\"]")).click();
        $(By.xpath("//*[@placeholder=\"Password\"]")).click();
        $(By.xpath("/html/body/app-root/ng-component")).click();
        $(By.xpath("//*[contains(text(), 'Please enter login')]")).shouldBe(Condition.visible);
        $(By.xpath("//*[contains(text(), 'Please enter password')]")).shouldBe(Condition.visible);
        $(By.xpath("/html/body/app-root/ng-component/mat-card/div/form/button[1]")).shouldBe(Condition.visible);
        $(By.xpath("/html/body/app-root/ng-component/mat-card/div/form/button[2]")).shouldBe(Condition.visible);
    }

    //Проверяет элементы и валидацию на странице регистрации
    @Test
    @Order(1)
    public void registerPageCheckItems() {
        $(By.xpath("/html/body/app-root/ng-component/mat-card/div/form/button[2]")).click();
        $(By.xpath("//*[@placeholder=\"Login\"]")).click();
        $(By.xpath("//*[@placeholder=\"Secret code\"]")).click();
        $(By.xpath("//*[@placeholder=\"Password\"]")).click();
        $(By.xpath("//*[@placeholder=\"Confirm your password\"]")).click();
        $(By.xpath("/html/body/app-root/ng-component")).click();
        $(By.xpath("//*[contains(text(), 'Please enter login')]")).shouldBe(Condition.visible);
        $(By.xpath("//*[contains(text(), 'Please enter secret')]")).shouldBe(Condition.visible);
        $(By.xpath("//*[contains(text(), 'Please enter password')]")).shouldBe(Condition.visible);
        $(By.xpath("/html/body/app-root/ng-component/mat-card/div/form/button[1]")).shouldBe(Condition.visible);
        $(By.xpath("/html/body/app-root/ng-component/mat-card/div/form/button[2]")).shouldBe(Condition.visible);
    }

    //Проверяет регистрацию (существующий пользователь, неверный код)
    @Test
    @Order(2)
    public void registerPageCheckRegister() {
        open("http://localhost:4200/registration");
        $(By.xpath("//*[@placeholder=\"Login\"]")).setValue("loupa");
        $(By.xpath("//*[@placeholder=\"Secret code\"]")).setValue("a");
        $(By.xpath("//*[@placeholder=\"Password\"]")).setValue("111");
        $(By.xpath("//*[@placeholder=\"Confirm your password\"]")).setValue("111");
        $(By.xpath("/html/body/app-root/ng-component/mat-card/div/form/button[1]")).click();

        $(By.xpath("//*[@placeholder=\"Login\"]")).setValue("loupa" + new Random().nextInt());
        $(By.xpath("//*[@placeholder=\"Secret code\"]")).setValue("aaa");
        $(By.xpath("//*[@placeholder=\"Password\"]")).setValue("111");
        $(By.xpath("//*[@placeholder=\"Confirm your password\"]")).setValue("111");
        $(By.xpath("/html/body/app-root/ng-component/mat-card/div/form/button[1]")).click();
    }

    //Проверяет регистрацию (корректная)
    @Test
    @Order(3)
//    @Disabled
    public void registerUser() {
        open("http://localhost:4200/registration");
        $(By.xpath("//*[@placeholder=\"Login\"]")).setValue(loginReg);
        $(By.xpath("//*[@placeholder=\"Secret code\"]")).setValue("a");
        $(By.xpath("//*[@placeholder=\"Password\"]")).setValue("111");
        $(By.xpath("//*[@placeholder=\"Confirm your password\"]")).setValue("111");
        $(By.xpath("/html/body/app-root/ng-component/mat-card/div/form/button[1]")).click();
    }

    //Проверяет Авторизацию (корректная)
    @Test
    @Order(3)
//    @Disabled
    public void authorizUser() {
        open("http://localhost:4200/login");
//        $(By.xpath("/html/body/app-root/ng-component/mat-card/div/form/button[2]")).click();
        $(By.xpath("//*[@placeholder=\"Login\"]")).setValue(login);
        $(By.xpath("//*[@placeholder=\"Password\"]")).setValue("111");
        $(By.xpath("/html/body/app-root/ng-component/mat-card/div/form/button[1]")).click();
    }

    //Проверяет элементы основной страницы
    @Test
    @Order(4)
    public void mainPageCheckItems() {
        open("http://localhost:4200/messenger?login=" + login);
        $(By.xpath("/html/body/app-root/app-messenger/div/app-contact-list/button")).shouldBe(Condition.visible); //меню
        $(By.xpath("/html/body/app-root/app-messenger/div/app-contact-list/mat-list")).shouldBe(Condition.visible); //список диалогов
        $(By.xpath("/html/body/app-root/app-messenger/div/app-dialog/app-message-list/mat-list")).shouldBe(Condition.visible); //чат
        $(By.xpath("//*[@placeholder=\"Enter message...\"]")).shouldBe(Condition.visible); //поле ввода сообщения
        $(By.xpath("/html/body/app-root/app-messenger/div/app-dialog/app-new-message/button")).shouldBe(Condition.visible); //кнопка send
        $(By.xpath("/html/body/app-root/app-messenger/div/app-contact-list/mat-list/mat-list-item[1]/div/div[2]")).shouldBe(Condition.visible); //диалог с Лупой
        $(By.xpath("/html/body/app-root/app-messenger/div/app-contact-list/mat-list/mat-list-item[1]/div/button")).shouldBe(Condition.visible); //кнопка с замочком
        $(By.xpath("/html/body/app-root/app-messenger/div/app-contact-list/mat-list/mat-list-item[1]/div/div[2]/button")).shouldBe(Condition.visible); //кнопка Change
    }

    //Проверяет элементы меню
    @Test
    @Order(5)
    public void menuCheckItems() {
        open("http://localhost:4200/messenger?login=" + login);
        $(By.xpath("/html/body/app-root/app-messenger/div/app-contact-list/button")).click();
        $(By.xpath("//*[contains(text(), 'Settings')]")).click();
        $(By.xpath("//*[contains(text(), 'Change pin')]")).shouldBe(Condition.visible);
        $(By.xpath("//*[contains(text(), 'Invite')]")).shouldBe(Condition.visible);
        $(By.xpath("//*[contains(text(), 'Show hidden dialogs')]")).shouldBe(Condition.visible);
        $(By.xpath("//*[contains(text(), 'Create new dialog')]")).shouldBe(Condition.visible);
        $(By.xpath("//*[contains(text(), 'Help')]")).shouldBe(Condition.visible);
        $(By.xpath("//*[contains(text(), 'Log out')]")).shouldBe(Condition.visible);

        $(By.xpath("//*[contains(text(), 'Change pin')]")).click();
        $(By.xpath("//*[@placeholder=\"Old pin\"]")).setValue("111");
        $(By.xpath("//*[@placeholder=\"New pin\"]")).setValue("111");
        $(By.xpath("//*[contains(text(), 'Ok')]")).click();

        $(By.xpath("/html/body/app-root/app-messenger/div/app-contact-list/button")).click();
        $(By.xpath("//*[contains(text(), 'Invite')]")).click();
        $(By.xpath("//*[contains(text(), 'Generate')]")).click();
        $(By.xpath("/html")).pressEscape();


        $(By.xpath("/html/body/app-root/app-messenger/div/app-contact-list/button")).click();
        $(By.xpath("//*[contains(text(), 'Show hidden dialogs')]")).click();
        $(By.xpath("//input")).setValue("111");
        $(By.xpath("//*[contains(text(), 'Ok')]")).click();

        $(By.xpath("/html/body/app-root/app-messenger/div/app-contact-list/button")).click();
        $(By.xpath("//*[contains(text(), 'Create new dialog')]")).click();
        $(By.xpath("//input")).setValue("behindloupa");
        $(By.xpath("//*[contains(text(), 'Create')]")).click();

        $(By.xpath("/html/body/app-root/app-messenger/div/app-contact-list/button")).click();
        $(By.xpath("//*[contains(text(), 'Help')]")).click();
        $(By.xpath("/html")).pressEscape();

        $(By.xpath("/html/body/app-root/app-messenger/div/app-contact-list/button")).click();
        $(By.xpath("//*[contains(text(), 'Log out')]")).click();
    }

    //Проверяет изменение названия диалога, скрытие диалогов, отправка сообщений
    @Test
    @Order(6)
    public void checkDialog() {
        open("http://localhost:4200/messenger?login=" + login);
        $(By.xpath("//*[contains(text(), 'Change')]")).click();
        $(By.xpath("//input")).setValue("loupa");
        $(By.xpath("//*[contains(text(), 'Ok')]")).click();
        $(By.xpath("//*[contains(text(), 'loupa')]")).shouldBe(Condition.visible);

        $(By.xpath("/html/body/app-root/app-messenger/div/app-contact-list/button")).click();
        $(By.xpath("//*[contains(text(), 'Show hidden dialogs')]")).click();
        $(By.xpath("//input")).setValue("111");
        $(By.xpath("//*[contains(text(), 'Ok')]")).click();

        $(By.xpath("//*[contains(text(), 'behindloupa')]")).click();

        $(By.xpath("//*[@placeholder=\"Enter message...\"]")).setValue("Hi behindloupa"); //поле ввода сообщения
        $(By.xpath("/html/body/app-root/app-messenger/div/app-dialog/app-new-message/button")).click(); //кнопка send

        $(By.xpath("//*[contains(text(), 'loupa')]")).click();

        $(By.xpath("/html/body/app-root/app-messenger/div/app-contact-list/button")).click();
        $(By.xpath("//*[contains(text(), 'Hide dialogs')]")).click();
    }
}
