package io;

import Product.DigitalProduct;
import Product.PhysicalProduct;
import Product.Product;
import Product.TaxType;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ReadProductsFileTest {
    private final static Map<String, Product> products = new HashMap<>();
    @Test
    void readProductsToDatabase() throws IOException {
        ReadProductsFile.ReadProductsToDatabase("./src/test/data/testproducts.txt");

        // Verify products were added to database
//        Map<String, Product> products = ProductDatabase.getInstance().getProducts();
        assertNotNull(products);

        Product p1 = products.get("p1");
        assertNotNull(p1);
        assertEquals("p1", p1.getName());
        assertEquals("testing", p1.getDescription());
        assertEquals(1, p1.getQuantityAvailable());
        assertEquals(2.00, p1.getPrice());
        assertEquals(TaxType.NORMAL, p1.getTaxType());

        Product p2 = products.get("p2");
        assertNotNull(p2);
        assertEquals("p2", p2.getName());
        assertEquals("testing", p2.getDescription());
        assertEquals(1, p2.getQuantityAvailable());
        assertEquals(2.00, p2.getPrice());
        assertEquals(TaxType.LUXURY, p2.getTaxType());
        assertEquals(12.00, ((PhysicalProduct) p2).getWeight());
    }
}
