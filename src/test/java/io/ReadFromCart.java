package io;

import org.junit.jupiter.api.Test;
import Product.*;
import io.*;
import ShoppingCart.*;
public class ReadFromCart {
    ProductController productController = new ProductController();
    ShoppingCartController shoppingCartController = new ShoppingCartController();
    CartRelatedActions cartRelatedActions = new CartRelatedActions(productController,shoppingCartController);
    @Test
    public void readFromCartTxt(){
        cartRelatedActions.read();
        System.out.println(shoppingCartController.shoppingCartList());

    }
}
