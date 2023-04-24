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
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ReadProductsFile {
    public static final String filename = "./src/main/java/io/products.txt";

    public static Map<String, Product> readProductsToDatabase(String filename) {
        // Read from file
        try {
            Map<String, Product> products = new HashMap<>();
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                while (tokenizer.hasMoreTokens()) {
                    String type = tokenizer.nextToken();
                    String name = tokenizer.nextToken();
                    String description = tokenizer.nextToken();
                    int quantityAvailable = Integer.parseInt(tokenizer.nextToken());
                    double price = Double.parseDouble(tokenizer.nextToken());
                    String taxTypeStr = tokenizer.nextToken();
                    TaxType taxType = TaxType.getType(taxTypeStr);

                    switch (type) {
                        case "DigitalProduct" ->
                            products.put(
                                    name,
                                    new DigitalProduct(
                                            name,
                                            description,
                                            quantityAvailable,
                                            price,
                                            taxType
                                    )
                            );
                        case "PhysicalProduct" -> {
                            double weight = Double.parseDouble(tokenizer.nextToken());
                            products.put(
                                    name,
                                    new PhysicalProduct(
                                            name,
                                            description,
                                            quantityAvailable,
                                            price,
                                            taxType,
                                            weight
                                    )
                            );
                        }
                        case "GiftDigitalProduct" -> {
                            String message = tokenizer.nextToken();
                            products.put(
                                    name,
                                    new DigitalProductCanBeGifted(
                                            name,
                                            description,
                                            quantityAvailable,
                                            price,
                                            taxType,
                                            message
                                    )
                            );
                        }
                        case "GiftPhysicalProduct" -> {
                            double weight = Double.parseDouble(tokenizer.nextToken());
                            String message = tokenizer.nextToken();
                            products.put(
                                    name,
                                    new PhysicalProductCanBeGifted(
                                            name,
                                            description,
                                            quantityAvailable,
                                            price,
                                            taxType,
                                            weight,
                                            message
                                    )
                            );
                        }
                        default -> throw new IOException("Invalid product type: " + type);
                    }
                }
            }
            reader.close();
            return products;
        } catch (IOException e) {
            System.out.println("Error reading database file: " + e.getMessage());
        }

        return null;
    }
}
