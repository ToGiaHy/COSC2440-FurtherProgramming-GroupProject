package io;

import Product.ProductController;
import ShoppingCart.ShoppingCartController;
import org.junit.jupiter.api.BeforeAll;
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
     */
    @Test
    public void writeAReceipt(){
        readProductsFile.read();
        cartRelatedActions.read();
        cartRelatedActions.writeReceipt("C1");
    }
}
