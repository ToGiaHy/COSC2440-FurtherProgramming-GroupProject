/**
 * @author <Vo Thanh Thong - s3878071>
 */

package org.example;

import Product.Coupon;
import Product.DigitalProduct;
import Product.ProductManager;
import Product.TaxType;
import SystemUI.UserUI;
import io.ReadProductsFile;
import io.WriteProductsFile;


public class Main {

    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();
        productManager.readFromFile();
        UserUI.userUI();
        productManager.writeToFile();
    }

}