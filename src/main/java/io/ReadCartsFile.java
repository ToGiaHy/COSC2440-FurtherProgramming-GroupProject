package io;

import ShoppingCart.ShoppingCart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCartsFile {

    public static ArrayList<ShoppingCart> readCartsToDatabase(String cartsFilePath, String productsFilePath) {
        ShoppingCart.resetId();
        ArrayList<ShoppingCart> carts = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(cartsFilePath));
            String line;

            while ((line = reader.readLine()) != null) {
                ShoppingCart cart = new ShoppingCart();
                carts.add(cart);
            }

            reader.close();
        } catch (IOException e){
            System.out.println("Error reading database file: " + e.getMessage());
        }

        return null;
    }
}
