package io;

import product.ProductController;
import shoppingcart.ShoppingCartController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WriteToReceipt {
    ProductController productController = new ProductController();
    ShoppingCartController shoppingCartController = new ShoppingCartController();
    ReadProductsFile readProductsFile = new ReadProductsFile(productController);
    CartRelatedActions cartRelatedActions = new CartRelatedActions(productController,shoppingCartController);
    @BeforeEach
    public void read(){
        readProductsFile.read();
        cartRelatedActions.read();
    }
    /**
     * Write a receipt of a cart from the test data
     * Then find out if the cart is already printed to the receipt
     */
    @Test
    public void writeAReceipt(){
        cartRelatedActions.writeReceipt("C1");
        if(cartRelatedActions.exists("C1"))
        {
            System.out.println("cart exists");
        }

    }
}
