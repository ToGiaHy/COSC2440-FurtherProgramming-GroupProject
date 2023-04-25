package io;

import ShoppingCart.ShoppingCart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ReadCartsFile {

    public static ArrayList<ShoppingCart> readCartsToDatabase(String cartsFilePath, String productsFilePath) {
        ShoppingCart.resetId();
        ArrayList<ShoppingCart> carts = new ArrayList<>();

        try {
            BufferedReader cartReader = new BufferedReader(new FileReader(cartsFilePath));
            String line;

            while ((line = cartReader.readLine()) != null) {
                ShoppingCart cart = new ShoppingCart();
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                while (tokenizer.hasMoreTokens()) {
                    // Extract cart details
                    int id = Integer.parseInt(tokenizer.nextToken());
                    String name = tokenizer.nextToken();
                    double cartAmount = Double.parseDouble(tokenizer.nextToken().trim());
                    double totalWeight = Double.parseDouble(tokenizer.nextToken().trim());
                    double shippingFee = Double.parseDouble(tokenizer.nextToken().trim());

                }


                // Read products and add to cart's PRODUCTS hashmap
                BufferedReader cartProductReader = new BufferedReader(new FileReader(productsFilePath));
                String productLine;
                while ((productLine = cartProductReader.readLine()) != null) {
                    StringTokenizer productTokenizer = new StringTokenizer(productLine, ",");
                    String productName = productTokenizer.nextToken().trim();
                    if (cart.getPRODUCTS().containsKey(productName)) {
                        continue; // Product already added to cart
                    }
                    int productQuantity = Integer.parseInt(productTokenizer.nextToken().trim());
//                    cart.addProduct(productName, productQuantity);
                }
                carts.add(cart);
                cartProductReader.close();
            }
            cartReader.close();
        } catch (IOException e){
            System.out.println("Error reading database file: " + e.getMessage());
        }

        return null;
    }
}
