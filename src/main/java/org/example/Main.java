/**
 * @author <Vo Thanh Thong - s3878071>
 */

package org.example;

import Product.*;
import ShoppingCart.ShoppingCartController;
import SystemUI.UserUI;
import io.CartRelatedActions;


public class Main {


    public static void main(String[] args) {
        ProductController productController = new ProductController();
        ShoppingCartController shoppingCartController = new ShoppingCartController();
        CartRelatedActions cartRelatedActions = new CartRelatedActions(productController, shoppingCartController);
        CouponDatabase couponDatabase = new CouponDatabase();
        UserUI userUI = new UserUI(productController, shoppingCartController, cartRelatedActions, couponDatabase);
    }

}