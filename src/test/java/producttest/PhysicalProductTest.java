package producttest;

import org.junit.jupiter.api.Test;
import product.PhysicalProduct;
import utils.TaxType;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhysicalProductTest {
    /**
     * check to see if the product instance is equal
     */
    @Test
    public void equalSameProduct() {
        PhysicalProduct product1 = new PhysicalProduct("Iphone", "this is an iphone", 11, 500.0,TaxType.FREE, 190.0);
        PhysicalProduct product2 = product1;
        assertTrue(product1.equals(product2));
    }
    /**
     * Check if the object are equal
     */
    @Test
    public void equalSameObject() {
        PhysicalProduct product1 = new PhysicalProduct("Iphone", "this is an iphone", 11, 500.0, TaxType.FREE, 190.0);
        Object obj = product1;
        assertTrue(product1.equals(obj));
    }
    /**
     * Compare with a String
     */
    @Test
    public void compareWithWrongType() {
        PhysicalProduct product1 = new PhysicalProduct("Iphone", "this is an iphone", 11, 500.0, TaxType.FREE,190.0);
        String other = "hello";
        assertFalse(product1.equals(other));
    }

}
