package io;

import org.junit.jupiter.api.Test;
import product.*;
import shoppingcart.*;
public class ReadFromFile {
    ProductController productController = new ProductController();
    ShoppingCartController shoppingCartController = new ShoppingCartController();
    CartRelatedActions cartRelatedActions = new CartRelatedActions(productController,shoppingCartController);

    ReadProductsFile readProductsFile = new ReadProductsFile(productController);

    /**
      It will display all the product test data
     */
    @Test
    public void setReadProductsFile(){
        readProductsFile.read();
        productController.viewProduct();
    }
    /**
      It will display all the test cart data
     */
    @Test
    public void readFromCartTxt(){
        readProductsFile.read();
        cartRelatedActions.read();
        shoppingCartController.viewCarts();
    }
}
