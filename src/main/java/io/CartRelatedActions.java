package io;

import Product.ProductController;
import ShoppingCart.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class CartRelatedActions implements FileActions {
    private final String CARTS_FILEPATH = "src/main/java/data/carts.txt";
    private final String RECEIPTS_FILEPATH = "src/main/java/data/receipts.txt";
    ProductController productController;
    ShoppingCartController shoppingCartController;

    /**
     * Read the carts from a file and add the carts to the list
     */
    public CartRelatedActions(ProductController productController, ShoppingCartController shoppingCartController) {
        this.productController = productController;
        this.shoppingCartController = shoppingCartController;
    }

    public void read() {
        //todo Cho Map của gift vào nữa
        try {
            BufferedReader cartReader = new BufferedReader(new FileReader(CARTS_FILEPATH));
            String line;
            ShoppingCart cart;
            String id, items, giftMessages, coupon;
            while ((line = cartReader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                while (tokenizer.hasMoreTokens()) {
                    // Extract cart's information
                    id = tokenizer.nextToken();
                    items = tokenizer.nextToken();
                    giftMessages = tokenizer.nextToken();
                    coupon = tokenizer.nextToken();
                    // Convert String to Map
                    Map<String, Integer> itemsMap = Arrays.stream(items.split(","))
                            .map(entry -> entry.split("="))
                            .collect(Collectors.toMap(entry -> entry[0], entry -> Integer.valueOf(entry[1])));
                    Map<String, String> giftMessagesMap = Arrays.stream(giftMessages.split(","))
                            .map(entry -> entry.split("="))
                            .collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));
                    //Create a new cart
                    cart = new ShoppingCart(id, itemsMap, coupon, giftMessagesMap, productController);
                    // Add a new cart to the shopping cart
                    shoppingCartController.add(cart);
                }
            }
            // Exception handling
        } catch (IOException e) {
            System.out.println("Error reading database file: " + e.getMessage());
        }
    }

    public void writeReceipt(String carTId) {
        try {
            FileWriter writer = new FileWriter(RECEIPTS_FILEPATH);
            writer.write(shoppingCartController.findCartByID(carTId).receiptToFile());
        } catch (IOException e) {
            System.out.println("Error writing to database file: " + e.getMessage());
        }
    }

    // Check if a cart id exists in the file
    public boolean exists(String cartId) {
        // Read from file
        try {
            BufferedReader reader = new BufferedReader(new FileReader(RECEIPTS_FILEPATH));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(cartId))
                    return true;
            }

        } catch (IOException e) {
            System.out.println("Error reading database file: " + e.getMessage());
        }
        return false;

    }

}

