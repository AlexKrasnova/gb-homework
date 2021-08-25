package ru.alexkrasnova.spring.lesson2.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alexkrasnova.spring.lesson2.cart.Cart;
import ru.alexkrasnova.spring.lesson2.exception.NoSuchProductException;

import java.util.Scanner;

@Component
public class ConsoleApplication {

    private Cart cart;
    private Scanner scanner;

    private final String ADD = "ADD";
    private final String REMOVE = "REMOVE";
    private final String SHOW_CART = "SHOW CART";
    private final String EXIT = "EXIT";

    @Autowired
    public ConsoleApplication(Cart cart) {
        this.cart = cart;
    }

    public void run() {
        scanner = new Scanner(System.in);
        try {
            System.out.println("Список команд:\n" + ADD + " - добавить товар,\n" + REMOVE + " - удалить товар,\n" + SHOW_CART + " - показать корзину,\n" + EXIT + " - выйти.");
            while (true) {
                System.out.println("Введите команду:");
                //final String str= scanner.nextLine();
                switch (scanner.nextLine()) {
                    case(ADD):
                        addProductToCart();
                        break;
                    case(REMOVE):
                        removeProductFormCart();
                        break;
                    case(SHOW_CART):
                        displayCart();
                        break;
                    case(EXIT):
                        return;
                    default:
                        System.out.println("Неверная команда, попробуйте еще раз.");
                    break;
                }
            }
        } finally {
            scanner.close();
        }
    }

    private void displayCart() {
        System.out.println("Ваша корзина:");
        System.out.println(cart);
    }

    private void addProductToCart() {
        System.out.println("Введите id товара:");
        String id = scanner.nextLine();
        try {
            cart.addProductById(id);
            System.out.println("Товар добавлен.");
        } catch (NoSuchProductException e) {
            System.out.println(e.getMessage());
        }


    }

    private void removeProductFormCart() {
        System.out.println("Введите id товара:");
        String id = scanner.nextLine();
        cart.removeProductById(id);
    }
}
