/**
 * @author Group 11
 */
package producttest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import product.DigitalProduct;
import utils.TaxType;

public class DigitalProductTest {
    /**
     * check to see if the product instance is equal
     */
    @Test
    public void equalSameProduct() {
        DigitalProduct product1 = new DigitalProduct("Art work", "this is an art work", 12, 13.0, TaxType.FREE);
        DigitalProduct product2 = product1;
        assertTrue(product1.equals(product2));
    }

    /**
     * Check if the object are equal
     */
    @Test
    public void equalSameObject() {
        DigitalProduct product1 = new DigitalProduct("Art work", "this is an art work", 12, 13.0,TaxType.FREE);
        Object obj = product1;
        assertTrue(product1.equals(obj));
    }

    /**
     * Compare with a String
     */
    @Test
    public void compareWithWrongType() {
        DigitalProduct product1 = new DigitalProduct("Art work", "this is an art work", 12, 13.0,TaxType.FREE);
        String other = "hello";
        assertFalse(product1.equals(other));
    }
}
