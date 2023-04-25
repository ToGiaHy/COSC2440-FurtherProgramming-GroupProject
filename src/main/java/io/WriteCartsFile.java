package io;

import Product.*;
import ShoppingCart.ShoppingCart;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class WriteCartsFile {
    public static void writeCartsToDatabase(ArrayList<ShoppingCart> carts, String cartsFilePath, String productsFilePath) {
        try {
            FileWriter cartWriter = new FileWriter(cartsFilePath);
            FileWriter cartProductWriter = new FileWriter(productsFilePath);

            for (ShoppingCart cart : carts) {
                cartWriter.write(cart.toFile() + ",");
                for (Map.Entry<String, Integer> productEntry : cart.getPRODUCTS().entrySet()) {
                    Product product = ProductManager.getPRODUCTS().get(productEntry.getKey());
                    if (product instanceof CanBeGifted) {
                        cartProductWriter.write(cart.getId() + "," + productEntry.getKey() + "," + productEntry.getValue() + "," + ((CanBeGifted) product).getMessage() + "\n");
                    } else
                        cartProductWriter.write(cart.getId() + "," + productEntry.getKey() + "," + productEntry.getValue() + "\n");

                }
            }

            cartWriter.close();
            cartProductWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing to database file: " + e.getMessage());
        }
    }
}
