package io;

import Product.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class ProductFileActions implements FileActions{
    /**
     * Attributes
     */
    private static final String PRODUCT_FILEPATH = "./src/main/java/data/products.txt";

    /**
     * Write products from PRODUCTS to the file
     *
     */
    public void writeToFile() {
        try {
            FileWriter writer = new FileWriter(PRODUCT_FILEPATH);

            for (Product product : ProductManager.PRODUCTS.values()) {
                writer.write(product.toFile() + '\n');
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to database file: " + e.getMessage());
        }
    }

    /**
     * Read data from the file to PRODUCTS
     */
    public void readFromFile() {
        // Read from file
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PRODUCT_FILEPATH));
            String line;

            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                while (tokenizer.hasMoreTokens()) {
                    String type = tokenizer.nextToken().toUpperCase();
                    String name = tokenizer.nextToken();
                    String description = tokenizer.nextToken();
                    int quantityAvailable = Integer.parseInt(tokenizer.nextToken());
                    double price = Double.parseDouble(tokenizer.nextToken());
                    String taxTypeStr = tokenizer.nextToken();
                    TaxType taxType = TaxType.getType(taxTypeStr);

                    switch (type) {
                        case "DIGITALPRODUCT" -> ProductManager.PRODUCTS.put(
                                name,
                                new DigitalProduct(
                                        name,
                                        description,
                                        quantityAvailable,
                                        price,
                                        taxType
                                )
                        );
                        case "PHYSICALPRODUCT" -> {
                            double weight = Double.parseDouble(tokenizer.nextToken());
                            ProductManager.PRODUCTS.put(
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
                        case "GIFTDIGITALPRODUCT" -> {
                            String message = tokenizer.nextToken();
                            ProductManager.PRODUCTS.put(
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
                        case "GIFTPHYSICALPRODUCT" -> {
                            double weight = Double.parseDouble(tokenizer.nextToken());
                            String message = tokenizer.nextToken();
                            ProductManager.PRODUCTS.put(
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
        } catch (IOException e) {
            System.out.println("Error reading database file: " + e.getMessage());
        }
    }

}
