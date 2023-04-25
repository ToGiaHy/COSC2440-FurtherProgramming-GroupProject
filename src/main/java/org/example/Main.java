/**
 * @author <Vo Thanh Thong - s3878071>
 */

package org.example;

import Product.*;
import SystemUI.ProductUI;
import SystemUI.ShoppingCartUI;
import SystemUI.UserUI;
import java.util.HashMap;
import io.FileActions;
import io.ProductFileActions;



public class Main {

    static ProductUI productUI = new ProductUI();
    static ShoppingCartUI cartUI = new ShoppingCartUI();

    public static void main(String[] args) {
//        ProductManager.PRODUCTS = ReadProductsFile.readProductsToDatabase("./data/products.txt");
        ProductManager.initialProducts();
//        UserUI.userUI();
//        productUI.productUI();
//        WriteProductsFile.writeProductsToDatabase(ProductManager.PRODUCTS, "./data/products.txt");
        cartUI.CartUI();

        FileActions productFileActions = new ProductFileActions();
        productFileActions.readFromFile("./src/main/java/data/products.txt");
        UserUI.userUI();
        productFileActions.writeToFile("./src/main/java/data/products.txt");
    }

}