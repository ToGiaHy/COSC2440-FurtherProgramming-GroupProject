package io;

import Product.Product;
import Product.DigitalProduct;
import Product.PhysicalProduct;
import Product.PhysicalProductCanBeGifted;
import Product.DigitalProductCanBeGifted;
import Product.TaxType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProductFileActionsTest {
    public final static Map<String, Product> products = new HashMap<>();
    private final static Map<String, Product> outputProducts = new HashMap<>();


    @BeforeEach
    void SetUp() {
        // Prepare test data
        outputProducts.put("p1", new DigitalProduct("p1", "testing",1,2, TaxType.NORMAL));
        outputProducts.put("p2", new PhysicalProduct("p2", "testing",1,2,TaxType.LUXURY,12));
        outputProducts.put("p3", new PhysicalProductCanBeGifted("p3", "testing",1,2,TaxType.FREE,12,"Mie is the best shuddup!!!"));
        outputProducts.put("p4", new DigitalProductCanBeGifted("p4", "testing",1,2,TaxType.LUXURY,"Mike Wazowski agrees"));

    }

    @Test
    void writeToFile() throws IOException {
        // Call the method being tested
        ProductFileActions.writeToFile(outputProducts, "./src/test/data/testproducts.txt");

        // Check that the file has been written correctly
        String fileContent = Files.readString(Paths.get("./src/test/data/testproducts.txt"));
        String[] lines = fileContent.split("\n");
        assertEquals(outputProducts.get("p1").toFile(), lines[0]);
        assertEquals(outputProducts.get("p2").toFile(), lines[1]);
        assertEquals(outputProducts.get("p3").toFile(), lines[2]);
        assertEquals(outputProducts.get("p4").toFile(), lines[3]);

        // Clean up test data
        File file = new File("./src/test/data/testproducts.txt");
        assertTrue(file.delete());
    }

    @Test
    void readFromFile() {
        // Call the method being tested
        ProductFileActions.writeToFile(outputProducts, "./src/test/data/testproducts.txt");
        Map<String, Product> inputProducts = ProductFileActions.readFromFile("./src/test/data/testproducts.txt");

        // Verify products were added to database
        assertNotNull(inputProducts);
        for (Map.Entry<String, Product> productEntry : outputProducts.entrySet()) {
            assertEquals(
                    productEntry.getValue().toFile(),
                    inputProducts.get(productEntry.getKey()).toFile()
            );
        }

        // Clean up test data
        File file = new File("./src/test/data/testproducts.txt");
        assertTrue(file.delete());
    }
}