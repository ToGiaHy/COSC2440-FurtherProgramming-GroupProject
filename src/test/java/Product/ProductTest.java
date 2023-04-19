/**
 * @author <Vo Thanh Thong - s3878071>
 */
package Product;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

/**
 * Unit test for Student classes.
 */

class ProductTest {
    @Test
    public void toStringWithTypeForPhysicalProduct()
    {
        PhysicalProduct product = new PhysicalProduct("Iphone", "this is an iphone", 11, 500.0, 190.0);
        assertEquals( "PHYSICAL - Iphone", product.toString());
    }

    @Test
    public void toStringWithTypeForPhysicalProductCanBeGifted()
    {
        PhysicalProductCanBeGifted product = new PhysicalProductCanBeGifted("Iphone", "this is an iphone", 11, 500.0, 190.0);
        assertEquals( "PHYSICAL GIFT - Iphone", product.toString());
    }

    @Test
    public void toStringWithTypeForPartTimeStudent()
    {
        DigitalProduct product = new DigitalProduct("Art work", "this is an art work", 12, 13.0);
        assertEquals( "DIGITAL - Art work", product.toString());
    }

    @Test
    public void toStringWithTypeForDigitalProductCanBeGifted()
    {
        DigitalProductCanBeGifted product = new DigitalProductCanBeGifted("Art work", "this is an art work", 12, 13.0);
        assertEquals( "DIGITAL GIFT - Art work", product.toString());
    }
}