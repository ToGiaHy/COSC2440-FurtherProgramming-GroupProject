/**
 * @author Group 11
 */
package org.example;

import io.CartRelatedActions;
import io.ReadProductsFile;
import product.ProductController;
import shoppingcart.ShoppingCartController;
import systemui.UserUI;
import utils.CouponDatabase;


public class Main {
    public static void main(String[] args) {
        ProductController productController = new ProductController();
        ShoppingCartController shoppingCartController = new ShoppingCartController();
        CartRelatedActions cartRelatedActions = new CartRelatedActions(productController, shoppingCartController);
        ReadProductsFile readProductsFile = new ReadProductsFile(productController);
        CouponDatabase couponDatabase = new CouponDatabase();
        UserUI userUI = new UserUI(productController, shoppingCartController, cartRelatedActions, couponDatabase);
        cartRelatedActions.read();
        readProductsFile.read();
        userUI.userUI();
    }
}