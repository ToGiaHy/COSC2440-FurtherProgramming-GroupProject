package io;

import ShoppingCart.ShoppingCart;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static Product.ProductManager.PRODUCTS;


public class WriteCartsFile {
    public static void writeCartsToDatabase(ArrayList<ShoppingCart> carts, String cartsFilePath, String productsFilePath) {
        try {
            FileWriter cartWriter = new FileWriter(cartsFilePath);
            FileWriter cartProductWriter = new FileWriter(productsFilePath);

            for (ShoppingCart cart : carts) {
                cartWriter.write(cart.toFile() + "\n");
                for (String product : cart.getPRODUCTS().keySet()) {
                    cartProductWriter.write(PRODUCTS.get(product).toFile() + "," + cart.getId() + "\n");
                }
            }

            cartWriter.close();
            cartProductWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing to database file: " + e.getMessage());
        }
    }
}
