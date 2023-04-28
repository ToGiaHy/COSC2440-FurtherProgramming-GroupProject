package io;

import Product.CanBeGifted;
import Product.ProductManager;
import Product.Product;
import ShoppingCart.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class ReadCartsFile implements FileActions {
    /**
     * Read the carts from a file and add the carts to the list
     *
     */
    public void action() {
        try {
            BufferedReader cartReader = new BufferedReader(new FileReader("src/main/java/data/carts.txt"));
            String line;
            ShoppingCart cart;
            int id;
            String items, giftMessages;
            while ((line = cartReader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                while (tokenizer.hasMoreTokens()) {
                    // Extract cart's information
                    id = Integer.parseInt(tokenizer.nextToken());
                    items = tokenizer.nextToken();
                    giftMessages = tokenizer.nextToken();
                    // Convert String to Map
                    Map<String, Integer> itemsMap = Arrays.stream(items.split(","))
                            .map(entry -> entry.split("="))
                            .collect(Collectors.toMap(entry -> entry[0], entry -> Integer.valueOf(entry[1])));
                    Map<String, String> giftMessagesMap = Arrays.stream(giftMessages.split(","))
                            .map(entry -> entry.split("="))
                            .collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));
                    //Create a new cart
                    cart = new ShoppingCart(id, itemsMap);
                    for (String itemName : giftMessagesMap.keySet()) {
                        Product item = ProductManager.getPRODUCTS().get(itemName);
                        if (item instanceof CanBeGifted) {
                            ((CanBeGifted) item).setMessage(giftMessagesMap.get(itemName));
                        }
                    }
                    // Add a new cart to the shopping cart
                    ShoppingCartManager.getShoppingCarts().add(cart);
                }
            }
            // Exception handling
        } catch (IOException e) {
            System.out.println("Error reading database file: " + e.getMessage());
        }
    }
}

