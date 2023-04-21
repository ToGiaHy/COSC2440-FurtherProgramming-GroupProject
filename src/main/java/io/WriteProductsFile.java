package io;

import java.io.FileWriter;
import java.io.IOException;
import Product.Product;

import static Product.ProductManager.PRODUCTS;


public class WriteProductsFile {
    private static final String filename = "./src/main/java/io/products.txt";

    public static void writeProductsToDatabase() throws IOException {
        try {
            FileWriter writer = new FileWriter(filename, true);

            for (Product product : PRODUCTS.values()) {
                writer.write(product.toFile());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to database file: " + e.getMessage());
        }
    }
}

