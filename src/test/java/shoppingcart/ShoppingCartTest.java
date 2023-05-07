/**
 * @author Group 11
 */
package shoppingcart;
import utils.TaxType;
import product.*;
import io.ReadProductsFile;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
class ShoppingCartTest {
    ProductController productController = new ProductController();
    ReadProductsFile readProductsFile = new ReadProductsFile(productController);

    /**
     Dell G15 has Free Tax and a percent coupon named Birthday of 10%
     It has a price of 40 which will deduct to 35 if apply the coupon
     */
    @Test
    void cartAmountPercentCouponTest() {
        readProductsFile.read();
        ShoppingCart s1 = new ShoppingCart(productController);
        Product p1 = productController.productList().get("Dell G15");
        s1.setCoupon("Birthday");
        s1.addItem(p1.getName(),1);
        assertEquals(35,s1.getAmount());
    }
    /**
     Iphone 12 has Free Tax and a price coupon named Bonjour of 10
     It has a price of 16.5 which will deduct to 6.5 if apply the coupon
     */
    @Test
    void cartAmountPriceCouponTest() {
        readProductsFile.read();
        ShoppingCart s1 = new ShoppingCart(productController);
        Product p1 = productController.productList().get("Iphone 12");
        s1.setCoupon("Bonjour");
        s1.addItem(p1.getName(),1);
        assertEquals(6.5,s1.getAmount());
    }

    /**
     It will add in the product ball successfully
     */
    @Test
    void addItemTest() {
        //expected to return the cart with the product "Ball" by the quantity of 5
        Product p1 = new PhysicalProduct("Ball","Football sporting equipment",10,10.0,TaxType.FREE,10);
        productController.productList().put("Ball",p1);
        ShoppingCart s1 = new ShoppingCart(productController);
        assertTrue(s1.addItem("Ball",5));
    }

    /**
      It will not add the item in since the quantity added is higher than the quantity available
     */
    @Test
    void quantityNotAccepted(){
        //The Quantity added in is higher than the QuantityAvailable
        //Expected to return empty cart
        Product p1 = new PhysicalProduct("Ball","Football sporting equipment",10,10.0,TaxType.FREE,10);
        productController.productList().put("Ball",p1);
        ShoppingCart s1 = new ShoppingCart(productController);
        s1.addItem("Ball",20);
        assertTrue(s1.getItems().size()<1);
    }

    /**
        We add one item of ball which has a price of 10
        It has a tax of 10%
        Which means the price will increase to 12.0
        The double value having more 0s is because of how Java handle double (Can be ignored)
     */
    @Test
    void taxInCartTest() {
        Product p1 = new PhysicalProduct("Ball","Football sporting equipment",10,10.0,TaxType.NORMAL,10);
        productController.productList().put("Ball",p1);
        ShoppingCart s1 = new ShoppingCart(productController);
        s1.addItem("Ball",1);
        assertEquals(s1.cartAmount(),12.000000014901161);
    }
}