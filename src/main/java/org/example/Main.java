/**
 * @author <Vo Thanh Thong - s3878071>
 */

package org.example;

import Product.*;
import ShoppingCart.ShoppingCartController;
import SystemUI.UserUI;
import io.CartRelatedActions;
import io.ReadProductsFile;


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