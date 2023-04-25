package ShoppingCart;

import Product.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    @Test
    void addItem() {
        //expected to add in product "Ball"
        Product p1 = new PhysicalProduct("Ball","Ball for football",10,20,TaxType.FREE,10);
        ShoppingCart s1 = new ShoppingCart();
        ProductManager.PRODUCTS.put("Ball",p1);
        s1.addItem("Ball",5);
        System.out.println(s1);
    }
    @Test
    void quantityHigher(){
        //expected to return an empty cart
        Product p1 = new DigitalProduct("Fifa","Game",10,20,TaxType.FREE);
        ShoppingCart s1 = new ShoppingCart();
        ProductManager.PRODUCTS.put("Fifa",p1);
        s1.addItem("Fifa",20);
        System.out.println(s1);
    }
    @Test
    void productNotAvailable(){
        //expected to return an empty cart
        Product p1 = new DigitalProduct("Fifa","Game",0,20,TaxType.FREE);
        ShoppingCart s1 = new ShoppingCart();
        ProductManager.PRODUCTS.put("Fifa",p1);
        s1.addItem("Fifa",20);
        System.out.println(s1);
    }
}