package io;

import Product.DigitalProduct;
import Product.DigitalProductCanBeGifted;
import Product.PhysicalProduct;
import Product.PhysicalProductCanBeGifted;
import Product.Product;
import Product.TaxType;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ReadProductsFileTest {
    private final static Map<String, Product> outputProducts = new HashMap<>();


    @Test
    void readProductsToDatabase() {
        // Prepare test data
        outputProducts.put("p1", new DigitalProduct("p1", "testing",1,2, TaxType.NORMAL));
        outputProducts.put("p2", new PhysicalProduct("p2", "testing",1,2,TaxType.LUXURY,12));
        outputProducts.put("p3", new PhysicalProductCanBeGifted("p3", "testing",1,2,TaxType.FREE,12,"Mie is the best shuddup!!!"));
        outputProducts.put("p4", new DigitalProductCanBeGifted("p4", "testing",1,2,TaxType.LUXURY,"Mike Wazowski agrees"));

        // Call the method being tested
        WriteProductsFile.writeProductsToDatabase(outputProducts, "./src/test/data/testproducts.txt");
        Map<String, Product> inputProducts = ReadProductsFile.readProductsToDatabase("./src/test/data/testproducts.txt");

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
