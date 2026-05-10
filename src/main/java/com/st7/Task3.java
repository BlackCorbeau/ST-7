package com.st7;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class Task3 {

    public static void printForecastAndSave() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        PrintWriter fileWriter = null;
        try {
            String url = "https://api.open-meteo.com/v1/forecast" +
                    "?latitude=56&longitude=44" +
                    "&hourly=temperature_2m,rain" +
                    "&timezone=Europe%2FMoscow" +
                    "&forecast_days=1" +
                    "&wind_speed_unit=ms";

            driver.get(url);
            WebElement preElement = driver.findElement(By.tagName("pre"));
            String jsonText = preElement.getText();

            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(jsonText);
            JSONObject hourly = (JSONObject) root.get("hourly");

            JSONArray timeArray = (JSONArray) hourly.get("time");
            JSONArray tempArray = (JSONArray) hourly.get("temperature_2m");
            JSONArray rainArray = (JSONArray) hourly.get("rain");

            System.out.println("=== Задание №3 ===");
            System.out.printf("%-4s | %-20s | %-11s | %-10s%n", "№", "Дата/время", "Температура", "Осадки (мм)");
            System.out.println("-------------------------------------------------------------");

            // Подготавливаем вывод в файл result/forecast.txt
            java.io.File resultDir = new java.io.File("result");
            if (!resultDir.exists()) resultDir.mkdirs();
            fileWriter = new PrintWriter(new FileWriter("result/forecast.txt"));
            fileWriter.printf("%-4s | %-20s | %-11s | %-10s%n", "№", "Дата/время", "Температура", "Осадки (мм)");
            fileWriter.println("-------------------------------------------------------------");

            for (int i = 0; i < timeArray.size(); i++) {
                String time = (String) timeArray.get(i);
                Number temp = (Number) tempArray.get(i);
                Number rain = (Number) rainArray.get(i);

                System.out.printf("%-4d | %-20s | %-11.1f | %-10.2f%n", i+1, time, temp.doubleValue(), rain.doubleValue());
                fileWriter.printf("%-4d | %-20s | %-11.1f | %-10.2f%n", i+1, time, temp.doubleValue(), rain.doubleValue());
            }

            System.out.println("\nТаблица прогноза также сохранена в файл result/forecast.txt");
        } catch (Exception e) {
            System.out.println("Ошибка в Task3: " + e.toString());
        } finally {
            if (fileWriter != null) fileWriter.close();
            driver.quit();
        }
    }
}