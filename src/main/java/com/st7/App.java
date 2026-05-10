package com.st7;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("https://www.calculator.net/password-generator.html");

            WebElement passwordField = webDriver.findElement(By.id("generated-password"));
            WebElement generateButton = webDriver.findElement(By.id("generate"));

            generateButton.click();
            Thread.sleep(500);

            String password = passwordField.getAttribute("value");
            System.out.println("=== Задание №1 ===");
            System.out.println("Сгенерированный пароль: " + password);
            System.out.println();

            Task2.printMyIp();
            Task3.printForecastAndSave();

        } catch (Exception e) {
            System.out.println("Ошибка при выполнении: " + e.toString());
        } finally {
            webDriver.quit();
        }
    }
}