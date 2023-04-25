/**
 * @author <Vo Thanh Thong - s3878071>
 */

package org.example;

import SystemUI.UserUI;
import io.FileActions;
import io.ProductFileActions;



public class Main {
    public static void main(String[] args) {
        FileActions productFileActions = new ProductFileActions();
        productFileActions.readFromFile("./src/main/java/data/products.txt");
        UserUI.userUI();
        productFileActions.writeToFile("./src/main/java/data/products.txt");
    }

}