/**
 * @author <Vo Thanh Thong - s3878071>
 */

package org.example;

import Product.*;
import ShoppingCart.ShoppingCartManager;
import SystemUI.ProductUI;
import SystemUI.ShoppingCartUI;
import SystemUI.UserUI;
import io.CartRelatedActions;


public class Main {


    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();
        ShoppingCartManager shoppingCartManager = new ShoppingCartManager();
        CartRelatedActions cartRelatedActions = new CartRelatedActions(productManager, shoppingCartManager);
        CouponDatabase couponDatabase = new CouponDatabase();
        UserUI userUI = new UserUI(productManager, shoppingCartManager, cartRelatedActions, couponDatabase);
    }

}