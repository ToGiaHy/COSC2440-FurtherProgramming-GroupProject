package io;

import ShoppingCart.ShoppingCart;

import java.io.FileWriter;
import java.io.IOException;

public class WriteCartsFile {
    public static void writeCartsToDatabase(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);

            for (ShoppingCart cart : ShoppingCart.values()) {
                writer.write(cart.toFile() + '\n');
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to database file: " + e.getMessage());
        }
    }

    public static void writeProductsInCartToDatabase() {
    }
}
