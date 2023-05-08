/**
 * @author Group 11
 */
package systemui;


import product.Product;
import utils.Coupon;
import utils.CouponDatabase;
import utils.PercentCoupon;
import utils.PriceCoupon;

import java.util.Scanner;


public class CouponUI {
    // Attributes
    private CouponDatabase couponDatabase;
    static Scanner scn = new Scanner(System.in);

    // Constructor
    public CouponUI(CouponDatabase couponDatabase) {
        this.couponDatabase = couponDatabase;
    }

    /**
     * Create a new coupon
     *
     * @return a Coupon object
     */
    public Coupon createCoupon() {
        // Get user input
        System.out.println("Which type of coupon would you like?(Percent or Price)");
        String option = scn.nextLine();
        System.out.println("Please write the coupon code for the coupon: ");
        String couponCode = scn.nextLine();
        // Divide the cases: percent coupon or price coupon
        if (this.couponDatabase.addCode(couponCode)) {
            if (option.equalsIgnoreCase("percent")) {
                System.out.println("Please write the value for the percentage: ");
                int value = Integer.parseInt(scn.nextLine());
                return new PercentCoupon(couponCode, value);
            } else {
                System.out.println("Please write the value for the price: ");
                double value = Double.parseDouble(scn.nextLine());
                return new PriceCoupon(couponCode, value);
            }
        } else {
            // Print a message if there is duplicate
            // A new coupon is not created
            System.out.println("Coupon code already exists");
            return null;
        }
    }

    /**
     * Edit a coupon's information
     *
     * @param product a product that contains the coupon
     */
    public void editCoupon(Product product) {
        System.out.println("The current product include these coupons");
        product.displayCoupons();
        // Get the user input
        System.out.println("Enter the coupon code that you want to edit: ");
        String keyInput = scn.nextLine();
        // Check if the input matches a coupon
        if (product.getCouponList().containsKey(keyInput)) {
            System.out.println("What type do you want the coupon to be? (Percent or Price)");
            String couponOption = scn.nextLine();
            if (couponOption.equalsIgnoreCase("Percent")) {
                System.out.println("Please enter the value for the coupon: (Integer)");
                int value = Integer.parseInt(scn.nextLine());
                PercentCoupon newPercentCoupon = new PercentCoupon(keyInput, value);
                product.getCouponList().put(keyInput, newPercentCoupon);
            } else {
                System.out.println("Please enter the value for the coupon: (Double)");
                double value = Double.parseDouble(scn.nextLine());
                PriceCoupon newPriceCoupon = new PriceCoupon(keyInput, value);
                product.getCouponList().put(keyInput, newPriceCoupon);
            }
        }

    }

    /**
     * Remove a coupon from the list
     *
     * @param product a product that contains the coupon
     */
    public void removeCoupon(Product product) {
        System.out.println("The current product include these coupons");
        product.displayCoupons();
        // Get user input
        System.out.println("Enter the coupon code that you want to remove: ");
        String couponCode = scn.nextLine();
        // Remove the coupon from the list and the database
        product.getCouponList().remove(couponCode);
        couponDatabase.removeCode(couponCode);
    }

    /**
     * Menu for editing a coupon
     *
     * @return a number representing the user input
     */
    public int editCouponMenu() {
        String regex = "[0-4]";
        String userInput = "";
        // Validate and display the menu
        while (!userInput.matches(regex)) {
            System.out.println("What do you want to edit about the coupon list of the product");
            System.out.println("#=======================================#");
            System.out.println("#===== COUPON MANAGER =====#");
            System.out.println("#=======================================#");
            System.out.println("1. View all coupons");
            System.out.println("2. Add a new coupon");
            System.out.println("3. Edit an exist coupon");
            System.out.println("4. Remove a coupon");
            userInput = scn.nextLine();
            if (!userInput.matches(regex)) {
                System.out.println();
                System.out.println("Error: You entered a string or a number out of range from 1 to 4!");
                System.out.println();
            }
        }
        return Integer.parseInt(userInput);
    }

    /**
     * Actions related to the menu above
     *
     * @param product the product containing the coupons
     */
    public void editCouponList(Product product) {
        int userInput = 0;
        while (userInput != 4) {
            userInput = editCouponMenu();
            switch (userInput) {
                case 1 -> product.displayCoupons();

                case 2 -> {
                    Coupon coupon = createCoupon();
                    product.getCouponList().put(coupon.getCouponCode(), coupon);
                    couponDatabase.addCode(coupon.getCouponCode());
                }
                case 3 -> editCoupon(product);

                case 4 -> removeCoupon(product);

            }
        }
    }
}
