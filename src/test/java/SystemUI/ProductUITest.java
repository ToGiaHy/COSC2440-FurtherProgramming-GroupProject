/**
 * @author <Vo Thanh Thong - s3878071>
 */
package SystemUI;

import Product.*;
import ShoppingCart.ShoppingCart;
import ShoppingCart.ShoppingCartManager;
import org.junit.jupiter.api.Test;

class ProductUITest {

    @Test
    void editProduct() {
        ProductManager.initialProducts();

        PhysicalProduct product1 = new PhysicalProduct("Iphone", "this is an iphone", 11, 500.0, 190.0);
        DigitalProduct product2 = new DigitalProduct("Art work", "this is an art work", 12, 13.0);
        DigitalProduct product3 = new DigitalProduct("Song", "this is a song", 10, 15.0);
        DigitalProductCanBeGifted product4 = new DigitalProductCanBeGifted("NFT", "this is a NFT", 8, 300.0);
        PhysicalProductCanBeGifted product5 = new PhysicalProductCanBeGifted("Desk", "this is a desk", 59, 149.0, 1000.0);

        ShoppingCart cart1 = new ShoppingCart();
        cart1.setName("Test Cart 1");
        System.out.println("Add cart have physical product");
        cart1.addItem(product1.getName());
        ShoppingCartManager.getShoppingCarts().add(cart1);
        for (ShoppingCart cart: ShoppingCartManager.getShoppingCarts()) {
            System.out.println(cart);
        }

        ShoppingCart cart2 = new ShoppingCart();
        cart2.setName("Test Cart 2");
        System.out.println("Add cart have digital product");
        cart2.addItem(product2.getName());
        ShoppingCartManager.getShoppingCarts().add(cart2);
        for (ShoppingCart cart: ShoppingCartManager.getShoppingCarts()) {
            System.out.println(cart);
        }

        ShoppingCart cart3 = new ShoppingCart();
        cart3.setName("Test Cart 3");
        System.out.println("Add cart have digital product");
        cart3.addItem(product3.getName());
        ShoppingCartManager.getShoppingCarts().add(cart3);
        for (ShoppingCart cart: ShoppingCartManager.getShoppingCarts()) {
            System.out.println(cart);
        }

        ShoppingCart cart4 = new ShoppingCart();
        cart4.setName("Test Cart 4");
        System.out.println("Add cart have digital product");
        cart4.addItem(product3.getName());
        ShoppingCartManager.getShoppingCarts().add(cart4);
        for (ShoppingCart cart: ShoppingCartManager.getShoppingCarts()) {
            System.out.println(cart);
        }

        ProductManager.PRODUCTS.get("Iphone").setPrice(10.0);



        System.out.println("===== After Edit Product ====");
        for (ShoppingCart cart: ShoppingCartManager.getShoppingCarts()) {
            System.out.println(cart);
        }

    }
}