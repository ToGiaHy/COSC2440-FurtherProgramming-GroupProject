/**
 * @author <Vo Thanh Thong - s3878071>
 */

package ShoppingCart;

import Product.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    /**
     * Test addItem function
     * <p>
     * After add product to cart, this test method will print cart
     * for developer can check the result.
     * </p>*/
    @Test
    void addItem() {
        ProductManager.initialProducts();
        PhysicalProduct product1 = new PhysicalProduct("Iphone", "this is an iphone", 11, 500.0, 190.0);
        DigitalProduct product2 = new DigitalProduct("Art work", "this is an art work", 12, 13.0);
        ShoppingCart cart = new ShoppingCart();
        cart.setName("Test Cart");
        cart.addItem(product1.getName());
        cart.addItem(product2.getName());
        System.out.println(cart);

    }

    /**
     * Test removeItem function
     * <p>
     * After remove product to cart, this test method will print cart
     * for developer can check the result.
     * </p>*/
    @Test
    void removeItem() {
        ProductManager.initialProducts();
        PhysicalProduct product1 = new PhysicalProduct("Iphone", "this is an iphone", 11, 500.0, 190.0);
        DigitalProduct product2 = new DigitalProduct("Art work", "this is an art work", 12, 13.0);
        ShoppingCart cart = new ShoppingCart();
        cart.setName("Test Cart");
        cart.addItem(product1.getName());
        cart.addItem(product2.getName());

        System.out.println("==== Before Remove ====");
        System.out.println(cart);

        cart.removeItem(product1.getName());

        System.out.println("==== After Remove ====");
        System.out.println(cart);
    }

    /**
     * Calculate the total amount of cart
     * <p>
     * Return true if cartAmount was calculated right
     * Return false if cartAmount was calculated wrong
     * </p>*/
    @Test
    void cartAmount() {
        ProductManager.initialProducts();
        PhysicalProduct product1 = new PhysicalProduct("Iphone", "this is an iphone", 11, 500.0, 190.0);
        DigitalProduct product2 = new DigitalProduct("Art work", "this is an art work", 12, 13.0);
        ShoppingCart cart = new ShoppingCart();
        cart.setName("Test Cart");
        cart.addItem(product1.getName());
        cart.addItem(product2.getName());

        assertEquals(cart.cartAmount(), 532.0);
    }
}