package io;

import Product.CanBeGifted;
import Product.ProductManager;
import Product.Product;
import ShoppingCart.ShoppingCart;
import ShoppingCart.ShoppingCartManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ReadCartsFile {


    public static void readCartsToDatabase(String cartsFilePath) {
        ShoppingCart cart;
        try {
            BufferedReader cartReader = new BufferedReader(new FileReader(cartsFilePath));
            String line;

            while ((line = cartReader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                while (tokenizer.hasMoreTokens()) {
                    // Extract cart id
                    int id = Integer.parseInt(tokenizer.nextToken());
                    cart = new ShoppingCart(id);
                    ShoppingCartManager.getShoppingCarts().add(cart);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading database file: " + e.getMessage());
        }
    }


    public static void readCartItemsFile(String productsFilePath) {
        try {
            // Read products and add to cart's PRODUCTS hashmap
            BufferedReader cartProductReader = new BufferedReader(new FileReader(productsFilePath));
            String productLine;
            String productName;
            int cartId;
            int quantity;
            String message = "";
            while ((productLine = cartProductReader.readLine()) != null) {
                StringTokenizer productTokenizer = new StringTokenizer(productLine, ",");
                while (productTokenizer.hasMoreTokens()) {
                    cartId = Integer.parseInt(productTokenizer.nextToken());
                    productName = productTokenizer.nextToken().trim();
                    quantity = Integer.parseInt(productTokenizer.nextToken());
                    if (productTokenizer.hasMoreTokens()) {
                        message = productTokenizer.toString();
                    }
                    ShoppingCart shoppingCart = ShoppingCartManager.findCartByID(cartId);
                    if (shoppingCart != null) {
                        shoppingCart.addItem(productName, quantity);
                        Product product = ProductManager.PRODUCTS.get(productName);
                        if (product instanceof CanBeGifted) {
                            ((CanBeGifted) product).setMessage(message);
                        }

                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading database file: " + e.getMessage());
        }

    }
//    public static ArrayList<ShoppingCart> readCartsToDatabase(String cartsFilePath, String productsFilePath) {
//        ShoppingCart.resetId();
//        ArrayList<ShoppingCart> carts = new ArrayList<>();
//
//        try {
//            BufferedReader cartReader = new BufferedReader(new FileReader(cartsFilePath));
//            String line;
//
//            while ((line = cartReader.readLine()) != null) {
//                ShoppingCart cart = new ShoppingCart();
//                StringTokenizer tokenizer = new StringTokenizer(line, ",");
//                while (tokenizer.hasMoreTokens()) {
//                    // Extract cart details
//                    int id = Integer.parseInt(tokenizer.nextToken());
//                    String name = tokenizer.nextToken();
//                    double cartAmount = Double.parseDouble(tokenizer.nextToken().trim());
//                    double totalWeight = Double.parseDouble(tokenizer.nextToken().trim());
//                    double shippingFee = Double.parseDouble(tokenizer.nextToken().trim());
//
//                }
//
//
//                // Read products and add to cart's PRODUCTS hashmap
//                BufferedReader cartProductReader = new BufferedReader(new FileReader(productsFilePath));
//                String productLine;
//                while ((productLine = cartProductReader.readLine()) != null) {
//                    StringTokenizer productTokenizer = new StringTokenizer(productLine, ",");
//                    String productName = productTokenizer.nextToken().trim();
//                    if (cart.getPRODUCTS().containsKey(productName)) {
//                        continue; // Product already added to cart
//                    }
//                    int productQuantity = Integer.parseInt(productTokenizer.nextToken().trim());
////                    cart.addProduct(productName, productQuantity);
//                }
//                carts.add(cart);
//                cartProductReader.close();
//            }
//            cartReader.close();
//        } catch (IOException e){
//            System.out.println("Error reading database file: " + e.getMessage());
//        }
//
//        return null;
//    }
}
