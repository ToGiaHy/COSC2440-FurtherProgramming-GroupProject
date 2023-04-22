package io;

import Product.DigitalProduct;
import Product.PhysicalProduct;
import Product.Product;
import Product.TaxType;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WriteProductsFileTest {
    public final static Map<String, Product> products = new HashMap<>();

    @Test
    void writeProductsToDatabase() throws IOException {
        // Prepare test data
        products.put("p1", new DigitalProduct("p1", "testing",1,2, TaxType.NORMAL));
        products.put("p2", new PhysicalProduct("p2", "testing",1,2,TaxType.LUXURY,12));

        // Call the method being tested
        WriteProductsFile.writeProductsToDatabase(products, "./src/test/data/testproducts.txt");

        // Check that the file has been written correctly
        String fileContent = Files.readString(Paths.get("./src/test/data/testproducts.txt"));
        String[] lines = fileContent.split("\n");
        assertEquals("p1,testing,1,2,0.10", lines[0]);
        assertEquals("p2,testing,1,2,0.2,12", lines[1]);

        // Clean up test data
        File file = new File("./src/test/data/testproducts.txt");
        assertTrue(file.delete());
    }
}