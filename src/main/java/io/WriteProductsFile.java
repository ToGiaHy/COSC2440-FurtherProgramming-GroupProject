package io;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import Product.Product;


public class WriteProductsFile {
    public static void writeProductsToDatabase(Map<String, Product> products, String filename) {
        try {
            FileWriter writer = new FileWriter(filename);

            for (Product product : products.values()) {
                writer.write(product.toFile() + '\n');
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to database file: " + e.getMessage());
        }
    }
}
