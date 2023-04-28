package io;

import Product.Product;
import Product.ProductManager;
import ShoppingCart.ShoppingCart;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;


public class WriteReceiptsFile {
    public void displayReceipt() throws IOException {

        FileWriter writer = new FileWriter("src/main/java/data/receipts.txt");
        writer.write("----------------RECEIPT----------------");
        try {
            writer.write("Date of purchase" + LocalDate.now());
            writer.write("Items: ");
            ShoppingCart cart = new ShoppingCart();
            for (Map.Entry<String, Integer> productEntry : cart.getItems().entrySet()) {
                Product product = ProductManager.getPRODUCTS().get(productEntry.getKey());
                writer.write("Name: " + productEntry.getKey() + "\t" + "Price: " + product.getPrice() + "\t" + "Tax: " + product.getTaxType() + "Quantity: " + productEntry.getValue());
            }
        } catch (IOException e) {
            writer.write("Error writing to database file: " + e.getMessage());
        }
    }
}
