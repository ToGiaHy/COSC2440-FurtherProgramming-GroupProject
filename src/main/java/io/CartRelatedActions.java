package io;

import Product.ProductController;
import ShoppingCart.*;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class CartRelatedActions implements FileActions {
    private final String CARTS_FILEPATH = "src/main/java/data/carts.txt";
    private final String RECEIPTS_FILEPATH = "src/main/java/data/receipts.txt";
    private final String USER_RECEIPTS_PATH = "src/main/java/data/userreceipts/";
    ProductController productController;
    ShoppingCartController shoppingCartController;
    /**
     * Read the carts from a file and add the carts to the list
     */
    public CartRelatedActions(ProductController productController, ShoppingCartController shoppingCartController) {
        this.productController = productController;
        this.shoppingCartController = shoppingCartController;
    }

    /**
     * Read each cart based on a line
     * Added into the shoppingCartController ArrayList
     */
    public void read() {
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
                    items = items.substring(1, items.length() - 1);
                    Map<String, Integer> itemsMap = Arrays.stream(items.split(";"))
                            .map(entry -> entry.split("="))
                            .collect(Collectors.toMap(entry -> entry[0], entry -> Integer.valueOf(entry[1])));
                    giftMessages = giftMessages.substring(1, giftMessages.length() - 1);

                    if (giftMessages.length() > 2) {
                        Map<String, String> giftMessagesMap = Arrays.stream(giftMessages.split(";"))
                                .map(entry -> entry.split("="))
                                .collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));

                        //Create a new cart
                        cart = new ShoppingCart(id, itemsMap, coupon, giftMessagesMap, productController);
                        shoppingCartController.add(cart);

                    } else {
                        Map<String, String> emptyMap = new HashMap<>();
                        cart = new ShoppingCart(id, itemsMap, coupon, emptyMap, productController);
                        shoppingCartController.add(cart);
                    }

                }
            }
            cartReader.close();
            // Exception handling
        } catch (IOException e) {
            System.out.println("Error reading database file: " + e.getMessage());
        }
    }
    //  Write receipt to file receipt.txt
    public void writeReceipt(String cartId) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(RECEIPTS_FILEPATH, true));
            writer.write(shoppingCartController.findCartByID(cartId).receiptToFile());
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to database file: " + e.getMessage());
        }
    }
    //  Save receipt file with custom name from user
    public void writeReceiptWithCustomName(String cartId, String fileName) {
        if (fileName.contains(" ")) {
            fileName = fileName.replace(" ", "_");
        }

        String path = String.format(USER_RECEIPTS_PATH + "/%s.txt", fileName);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            writer.write(shoppingCartController.findCartByID(cartId).receiptToFileCustomName());
            writer.close();
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

