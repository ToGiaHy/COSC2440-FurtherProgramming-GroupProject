package io;

import Product.Product;
import ShoppingCart.ShoppingCart;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class WriteCartsFile {
    static ShoppingCart cart = new ShoppingCart();
    public static void writeCartsToDatabase(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(cart.toFile() + "\n");
            bufferedWriter.close();
            writer.close();

        } catch (IOException e) {
            System.out.println("Error writing to database file: " + e.getMessage());
        }
    }

    public static void writeProductsInCartToDatabase(Map<String, Product> products, String filename) {
        try {
            FileWriter writer = new FileWriter(filename);

            for (Product product : products.values()) {
                writer.write(cart.getId() + ",");
                writer.write(product.toFile() + '\n');
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to database file: " + e.getMessage());
        }
    }
}
