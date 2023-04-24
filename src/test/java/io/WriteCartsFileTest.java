package io;

import Product.*;
import ShoppingCart.ShoppingCart;
import ShoppingCart.ShoppingCartManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WriteCartsFileTest {
    public final static Map<String, Product> products = new HashMap<>();

    @Test
    void writeCartsToDatabase() throws IOException {
        ShoppingCart cart = new ShoppingCart(); // TODO


        // Call the method being tested
        WriteCartsFile.writeCartsToDatabase("./src/test/data/testcarts.txt");

        // Check that the file has been written correctly
        String fileContent = Files.readString(Paths.get("./src/test/data/testcarts.txt"));
        String[] lines = fileContent.split("\n");

        // Clean up test data
//        File file = new File("./src/test/data/testcarts.txt");
//        assertTrue(file.delete());
    }

    @Test
    void writeProductsInCartToDatabase() throws IOException {
        // Prepare test data
        products.put("p1", new DigitalProduct("p1", "testing",1,2, TaxType.NORMAL));
        products.put("p2", new PhysicalProduct("p2", "testing",1,2,TaxType.LUXURY,12));
        products.put("p3", new PhysicalProductCanBeGifted("p3", "testing",1,2,TaxType.FREE,12,"Mie is the best shuddup!!!"));
        products.put("p4", new DigitalProductCanBeGifted("p4", "testing",1,2,TaxType.LUXURY,"Mike Wazowski agrees"));

        // Call the metho being tested
        WriteCartsFile.writeProductsInCartToDatabase(products,"./src/test/data/testproductsincart.txt");

        // Check that the file has been written correctly
        String fileContent = Files.readString(Paths.get("./src/test/data/testproductsincart.txt"));
        String[] lines = fileContent.split("\n");
        assertEquals(products.get("p1").toFile(), lines[0]); // TODO
        assertEquals(products.get("p2").toFile(), lines[1]);
        assertEquals(products.get("p3").toFile(), lines[2]);
        assertEquals(products.get("p4").toFile(), lines[3]);

        // Clean up test data
        File file = new File("./src/test/data/testproductsincart.txt");
        assertTrue(file.delete());
    }
}