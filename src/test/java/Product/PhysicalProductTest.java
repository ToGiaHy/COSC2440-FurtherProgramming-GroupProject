/**
 * @author <Vo Thanh Thong - s3878071>
 */

package Product;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

/**
 * Unit test for Physical Product
 */
class PhysicalProductTest {
    @Test
    public void equalSameProduct() {
        PhysicalProduct product1 = new PhysicalProduct("Iphone", "this is an iphone", 11, 500.0, 190.0);
        PhysicalProduct product2 = product1;
        assertTrue(product1.equals(product2));
    }

    @Test
    public void equalSameObject() {
        PhysicalProduct product1 = new PhysicalProduct("Iphone", "this is an iphone", 11, 500.0, 190.0);
        Object obj = product1;
        assertTrue(product1.equals(obj));
    }

    @Test
    public void compareWithWrongType() {
        PhysicalProduct product1 = new PhysicalProduct("Iphone", "this is an iphone", 11, 500.0, 190.0);
        String other = "hello";
        assertFalse(product1.equals(other));
    }

}