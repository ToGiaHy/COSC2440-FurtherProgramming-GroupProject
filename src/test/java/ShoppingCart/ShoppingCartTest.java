package ShoppingCart;

import Product.*;
import org.junit.jupiter.api.Test;


import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    @Test
    void cartAmountPercentCoupon() {
        ShoppingCart s1 = new ShoppingCart();
        ProductManager.initialProducts();
        s1.setCoupon("price1");
        s1.addItem("Song",5);
        System.out.println(s1.cartAmount());
        System.out.println(s1.getCouponDiscount());
    }
    @Test
    void cartAmountPriceCoupon() {
        ShoppingCart s1 = new ShoppingCart();
        ProductManager.initialProducts();
        s1.setCoupon("price2");
        s1.addItem("Song",5);
        System.out.println(s1.cartAmount());
        System.out.println(s1.getCouponDiscount());
    }
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