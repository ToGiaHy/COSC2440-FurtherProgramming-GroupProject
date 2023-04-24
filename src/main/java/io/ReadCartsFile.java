package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCartsFile {

    public static void readCartsToDatabase(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = reader.readLine())!= null) {

            }
            reader.close();

        } catch (IOException e){
            System.out.println("Error reading database file: " + e.getMessage());
        }
    }

    public static void readProductsInCartToDatabase(String filename) {

    }
}
