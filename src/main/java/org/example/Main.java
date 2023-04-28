/**
 * @author <Vo Thanh Thong - s3878071>
 */

package org.example;

import Product.*;
import SystemUI.ProductUI;
import SystemUI.ShoppingCartUI;
import SystemUI.UserUI;
import io.ProductFileActions;



public class Main {

    static ProductUI productUI = new ProductUI();
    static ShoppingCartUI cartUI = new ShoppingCartUI();

    public static void main(String[] args) {
//        ProductManager.PRODUCTS = ProductFileActions.readFromFile("./src/main/java/data/products.txt");
        ProductManager.initialProducts();
//        UserUI.userUI();
//        productUI.productUI();
//        ProductFileActions.writeToFile(ProductManager.PRODUCTS, "./src/main/java/data/products.txt");
//        cartUI.CartUI();

        UserUI.userUI();
    }

}