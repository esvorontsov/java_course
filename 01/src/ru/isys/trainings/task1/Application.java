package ru.isys.trainings.task1;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите ваше имя...");
        String text = scanner.next();
        System.out.println("Hello, " + text);
        scanner.close();
    }

}
