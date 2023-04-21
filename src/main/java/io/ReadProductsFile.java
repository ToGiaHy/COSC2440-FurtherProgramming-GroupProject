package io;

import Product.PhysicalProductCanBeGifted;
import Product.PhysicalProduct;
import Product.DigitalProduct;
import Product.DigitalProductCanBeGifted;
import Product.Product;
import Product.TaxType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ReadProductsFile {
    private static final String filename = "./src/main/java/io/products.txt";

    public static void ReadProductsToDatabase() throws IOException {
        // Read from file
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            Product p = null;
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                while (tokenizer.hasMoreTokens()) {
                    String type = tokenizer.nextToken();
                    String name = tokenizer.nextToken();
                    String description = tokenizer.nextToken();
                    int quantityAvailable = Integer.parseInt(tokenizer.nextToken());
                    TaxType taxType = tokenizer.nextToken();
                    double price = Double.parseDouble(tokenizer.nextToken());

                    switch (type) {
                        case "DigitalProduct":
                            p = new DigitalProduct(name, description, quantityAvailable, price, taxType);
                            break;
                        case "PhysicalProduct":
                            double weight = Double.parseDouble(tokenizer.nextToken());
                            p = new PhysicalProduct(name, description, quantityAvailable, price, taxType, weight);
                            break;
                        case "GiftDigitalProduct":
                            String message = tokenizer.nextToken();
                            p = new DigitalProductCanBeGifted(name, description, quantityAvailable, price, taxType, message);
                            break;
                        case "GiftPhysicalProduct":
                            double weight = Double.parseDouble(tokenizer.nextToken());
                            String message = tokenizer.nextToken();
                            p = new PhysicalProductCanBeGifted(name, description, quantityAvailable, price, TaxType, weight, message);
                        default:
                            System.out.println("Invalid product type: " + type);
                            break;
                    }

                }
            }
            System.out.println(p);
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading database file: " + e.getMessage());
        }
    }


}
