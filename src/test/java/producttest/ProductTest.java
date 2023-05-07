package producttest;

import org.junit.jupiter.api.Test;
import product.DigitalProduct;
import product.PhysicalProduct;
import product.PhysicalProductCanBeGifted;
import utils.TaxType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {
    @Test
    public void toStringWithTypeForPhysicalProduct()
    {
        PhysicalProduct product = new PhysicalProduct("Iphone", "this is an iphone", 11, 500.0, TaxType.FREE, 190.0);
        assertEquals( "Physical Product Name: Iphone, Description: this is an iphone, Quantity Available: 11, Price: 500.00, Tax Type: Free, Weight: 190.00", product.toString());
    }
    @Test
    public void toStringWithTypeForDigitalProduct()
    {
        DigitalProduct product = new DigitalProduct("Art work", "this is an art work", 12, 13.0,TaxType.FREE);
        assertEquals( "Digital Product Name: Art work, Description: this is an art work, Quantity Available: 12, Price: 13.00, Tax Type: Free", product.toString());
    }


}
