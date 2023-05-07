/**
 * @author <Vo Thanh Thong - s3878071>
 */

package SystemUI;

import Product.CouponDatabase;
import Product.ProductController;

import ShoppingCart.ShoppingCartController;

import io.CartRelatedActions;

import java.util.*;

public class UserUI {
    /**
     * UserUI attributes
     */
    static Scanner scn = new Scanner(System.in);

    private ProductUI productUI;
    private ShoppingCartUI cartUI;
    private CouponUI couponUI;

    public UserUI(ProductController productController, ShoppingCartController cartController, CartRelatedActions cartRelatedActions, CouponDatabase couponDatabase) {
        productUI = new ProductUI(productController);
        cartUI = new ShoppingCartUI(cartController, cartRelatedActions, productController);
        couponUI = new CouponUI(couponDatabase);
    }

    /**
     * Menu
     * Display all option for user can interact with the system
     */
    private static int menu() {
        String userInput = "";
        while (!userInput.matches(Regex.NUM_1_TO_3)) {
            System.out.println("#=======================================#");
            System.out.println("#===== WELCOME TO SHOPPING SERVICE =====#");
            System.out.println("#=======================================#");
            System.out.println("1. Product manager");
            System.out.println("2. Shopping cart manager");
            System.out.println("3. Exit program");
            System.out.println("Please choose interaction by enter a from 1 to 3:");
            userInput = scn.nextLine();
            if (!userInput.matches(Regex.NUM_1_TO_3)) {
                System.out.println();
                System.out.println("Error: You entered a string or a number out of range from 1 to 3!");
                System.out.println();
            }
        }

        return Integer.parseInt(userInput);
    }

    /**
     * userUI
     * Process the requirement of user input from the menu
     */
    public void userUI() {
        int userInt;
        do {
            userInt = menu();

            switch (userInt) {
                case 1 -> {
                    productUI.productUI(couponUI, this);
                    System.out.println();
                }
                case 2 -> {
                    cartUI.CartManagerUI(this);
                    System.out.println();
                }
                case 3 -> {
                    System.out.println("#===============================#");
                    System.out.println("Thank you for using our services!");
                    System.out.println("#===============================#");
                }
            }
        }
        while (userInt != 3);
    }
}
