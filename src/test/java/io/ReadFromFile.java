package io;
import org.junit.jupiter.api.Test;
import shoppingcart.*;
import product.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReadFromFile {
    ProductController productController = new ProductController();
    ShoppingCartController shoppingCartController = new ShoppingCartController();
    CartRelatedActions cartRelatedActions = new CartRelatedActions(productController,shoppingCartController);
    ReadProductsFile readProductsFile = new ReadProductsFile(productController);

    /**
     * Assert that the product controller product list has been read from the products.txt
     */
    @Test
    public void readProductsFileTest(){
        readProductsFile.read();
        assertNotNull(productController.productList());
    }
    /**
     * Assert that the shopping cart controller cart list has been read from the carts.txt
     */
    @Test
    public void readCartsFileTest(){
        cartRelatedActions.read();
        assertNotNull(shoppingCartController.shoppingCartList());
    }
}
