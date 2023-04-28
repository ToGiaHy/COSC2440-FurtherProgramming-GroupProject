package io;

import Product.Product;
import Product.ProductManager;
import ShoppingCart.ShoppingCart;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;


public class WriteReceiptsFile implements FileActions{
    public void action() {
        try {
            FileWriter writer = new FileWriter("src/main/java/data/receipts.txt");
            writer.write("----------------RECEIPT----------------");
            writer.write("Date of purchase" + LocalDate.now());
            writer.write("Items: ");
            ShoppingCart cart = new ShoppingCart();
            for (Map.Entry<String, Integer> productEntry : cart.getItems().entrySet()) {
                Product product = ProductManager.getPRODUCTS().get(productEntry.getKey());
                writer.write("Name: " + productEntry.getKey() + "\t" + "Price: " + product.getPrice() + "\t" + "Tax: " + product.getTaxType() + "Quantity: " + productEntry.getValue());
            }
        } catch (IOException e) {
            System.out.println("Error writing to database file: " + e.getMessage());
        }
    }
}
