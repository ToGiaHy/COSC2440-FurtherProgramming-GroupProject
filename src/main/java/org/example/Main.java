/**
 * @author <Vo Thanh Thong - s3878071>
 */

package org.example;

import Product.ProductManager;
import SystemUI.UserUI;
import io.ReadProductsFile;
import io.WriteProductsFile;


public class Main {

    public static void main(String[] args) {
        ProductManager.PRODUCTS = ReadProductsFile.readProductsToDatabase("./data/products.txt");
        UserUI.userUI();
        WriteProductsFile.writeProductsToDatabase(ProductManager.PRODUCTS, "./data/products.txt");
    }

}