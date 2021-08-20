package ru.alexkrasnova.spring.lesson2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.alexkrasnova.spring.lesson2.config.ApplicationConfig;
import ru.alexkrasnova.spring.lesson2.ui.ConsoleApplication;

public class MainApp {

    public static void main(String[] args) {

        final ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        final ConsoleApplication consoleApplication = context.getBean(ConsoleApplication.class);
        consoleApplication.run();
    }
}
