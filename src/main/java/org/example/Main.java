/**
 * @author <Vo Thanh Thong - s3878071>
 */

package org.example;

import Product.Product;
import Product.ProductManager;
import SystemUI.*;
public class Main {

    public static void main(String[] args) {
        ProductManager.initialProducts();

        UserUI.startMenu();

    }

}