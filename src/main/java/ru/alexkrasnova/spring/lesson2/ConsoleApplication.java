package ru.alexkrasnova.spring.lesson2;

import java.util.Scanner;

public class ConsoleApplication {
    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите команду");
            final String str = scanner.nextLine();
            if ("ADD".equals(str)) {
                System.out.println("Введите id товара");
            }
            if ("EXIT".equals(str)) {
                return;
            }
        }

    }
}
