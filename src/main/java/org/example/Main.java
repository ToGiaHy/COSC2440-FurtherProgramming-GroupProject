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
        productFileActions.readFromFile();
        UserUI.userUI();
        productFileActions.writeToFile();
    }

}