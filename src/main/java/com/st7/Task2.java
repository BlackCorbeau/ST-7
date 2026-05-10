package com.st7;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task2 {

    public static void printMyIp() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://api.ipify.org/?format=json");
            WebElement preElement = driver.findElement(By.tagName("pre"));
            String jsonText = preElement.getText();

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonText);
            String ip = (String) json.get("ip");

            System.out.println("=== Задание №2 ===");
            System.out.println("Ваш публичный IPv4-адрес: " + ip);
            System.out.println();
        } catch (Exception e) {
            System.out.println("Ошибка в Task2: " + e.toString());
        } finally {
            driver.quit();
        }
    }
}