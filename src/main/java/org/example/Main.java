/**
 * @author <Vo Thanh Thong - s3878071>
 */

package org.example;

import Product.*;
import SystemUI.ProductUI;
import SystemUI.ShoppingCartUI;
import SystemUI.UserUI;
import io.ReadProductsFile;
import io.WriteProductsFile;
import java.util.HashMap;


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

    }

}