package ShoppingCart;

import Product.*;
import io.ReadProductsFile;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {
    ProductController productController = new ProductController();
    ReadProductsFile readProductsFile = new ReadProductsFile(productController);
    @Test
    void cartAmountPercentCoupon() {
        readProductsFile.read();
        System.out.println(productController.productList());
        ShoppingCart s1 = new ShoppingCart(productController);
        Product p1 = productController.productList().get("Iphone 12");
        s1.setCoupon("Bonjour");
        s1.addItem("Iphone 12",1);
        System.out.println(s1.cartAmount());
    }
    @Test
    void cartAmountPriceCoupon() {
        ShoppingCart s1 = new ShoppingCart(productController);
        s1.setCoupon("price2");
        s1.addItem("Song",5);
        System.out.println(s1.cartAmount());
    }
    @Test

    void addItem() {
        //expected to return the cart with the product "Ball" by the quantity of 5
        Product p1 = new PhysicalProduct("Ball","Football sporting equipment",10,10.0,TaxType.FREE,10);
        productController.productList().put("Ball",p1);
        ShoppingCart s1 = new ShoppingCart(productController);
        s1.addItem("Ball",5);
        System.out.println(s1);
    }
    @Test
    void quantityNotAccepted(){
        //The Quantity added in is higher than the QuantityAvailable
        //Expected to return empty cart
        Product p1 = new PhysicalProduct("Ball","Football sporting equipment",10,10.0,TaxType.FREE,10);
        productController.productList().put("Ball",p1);
        ShoppingCart s1 = new ShoppingCart(productController);
        s1.addItem("Ball",20);
        System.out.println(s1);
    }
    @Test
    void productNotAvailable(){
        //QuantityAvailable of the product is 0
        //expected to return empty cart
        Product p1 = new PhysicalProduct("Ball","Football sporting equipment",0,10.0,TaxType.FREE,10);
        productController.productList().put("Ball",p1);
        ShoppingCart s1 = new ShoppingCart(productController);
        s1.addItem("Ball",20);
        System.out.println(s1);
    }
    @Test
    void taxInCart() {
        //expected to return the cart with the product "Ball" by the quantity of 5
        Product p1 = new PhysicalProduct("Ball","Football sporting equipment",10,10.0,TaxType.NORMAL,10);
        productController.productList().put("Ball",p1);
        ShoppingCart s1 = new ShoppingCart(productController);
        s1.addItem("Ball",5);
        System.out.println(s1.getTotalWeight());
        System.out.println(s1.getCoupon());
        System.out.println(s1.cartAmount());
    }
}