package ru.alexkrasnova.spring.lesson1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductsServlet", urlPatterns = {"/products"})
public class ProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Молоко", 89));
        products.add(new Product(2, "Сырок глазированный", 43));
        products.add(new Product(3, "Сливки", 120));
        products.add(new Product(4, "Сыр", 300));
        products.add(new Product(5, "Маслины", 109));
        products.add(new Product(6, "Овощи по-деревенски", 59));
        products.add(new Product(7, "Кефир", 84));
        products.add(new Product(8, "Сметана", 75));
        products.add(new Product(9, "Фарш телячий", 259));
        products.add(new Product(10, "Батон нарезной", 40));

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        StringBuilder stringBuilder = new StringBuilder();
        for(Product product : products){
            stringBuilder.append(product.toString()).append("<br>");
        }
        resp.getWriter().write(stringBuilder.toString());
    }
}
