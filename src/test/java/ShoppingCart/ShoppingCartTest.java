package ShoppingCart;

import Product.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    @Test
    void addItem() {
        //expected to return the cart with the product "Ball" by the quantity of 5
        Product p1 = new PhysicalProduct("Ball","Football sporting equipment",10,10.0,TaxType.FREE,10);
        ProductManager.PRODUCTS.put("Ball",p1);
        ShoppingCart s1 = new ShoppingCart();
        s1.addItem("Ball",5);
        System.out.println(s1);
    }
    @Test
    void quantityNotAccepted(){
        //The Quantity added in is higher than the QuantityAvailable
        //Expected to return empty cart
        Product p1 = new PhysicalProduct("Ball","Football sporting equipment",10,10.0,TaxType.FREE,10);
        ProductManager.PRODUCTS.put("Ball",p1);
        ShoppingCart s1 = new ShoppingCart();
        s1.addItem("Ball",20);
        System.out.println(s1);
    }
    @Test
    void productNotAvailable(){
        //QuantityAvailable of the product is 0
        //expected to return empty cart
        Product p1 = new PhysicalProduct("Ball","Football sporting equipment",0,10.0,TaxType.FREE,10);
        ProductManager.PRODUCTS.put("Ball",p1);
        ShoppingCart s1 = new ShoppingCart();
        s1.addItem("Ball",20);
        System.out.println(s1);
    }
}