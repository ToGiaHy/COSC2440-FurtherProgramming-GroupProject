/**
 * @author <Vo Thanh Thong - s3878071>
 */
package Product;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

/**
 * Unit test for Physical Product
 */
class DigitalProductTest {
    @Test
    public void equalSameProduct() {
        DigitalProduct product1 = new DigitalProduct("Art work", "this is an art work", 12, 13.0);
        DigitalProduct product2 = product1;
        assertTrue(product1.equals(product2));
    }

    @Test
    public void equalSameObject() {
        DigitalProduct product1 = new DigitalProduct("Art work", "this is an art work", 12, 13.0);
        Object obj = product1;
        assertTrue(product1.equals(obj));
    }

    @Test
    public void compareWithWrongType() {
        DigitalProduct product1 = new DigitalProduct("Art work", "this is an art work", 12, 13.0);
        String other = "hello";
        assertFalse(product1.equals(other));
    }
}