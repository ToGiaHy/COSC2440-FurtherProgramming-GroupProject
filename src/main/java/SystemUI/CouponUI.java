package SystemUI;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import Product.*;

import static Product.CouponDatabase.COUPONLIST;

public class CouponUI {
    public static Coupon createCoupon(){
        Scanner scn = new Scanner(System.in);
        System.out.println("Which type of coupon would you like?(Percent or Price)");
        String option = scn.nextLine();
        System.out.println("Please write the coupon code for the coupon: ");
        String couponCode = scn.nextLine();
        if (COUPONLIST.add(couponCode)) {
            if(option.equalsIgnoreCase("percent")){
                System.out.println("Please write the value for the percentage: ");
                int value = Integer.parseInt(scn.nextLine());
                return new PercentCoupon(couponCode,value);
            }
            else {
                System.out.println("Please write the value for the price: ");
                double value = Double.parseDouble(scn.nextLine());
                return new PriceCoupon(couponCode,value);
            }
        }
        else {
            System.out.println("Coupon code already exists");
            return null;
        }
    }
    public static void editCoupon(Product product){
        Scanner scn = new Scanner(System.in);
        System.out.println("The current product include these coupons");
        Map<String,Coupon> tempCoupon = product.getCouponList();
        for(String keys : tempCoupon.keySet()){
            System.out.println(tempCoupon.get(keys));
        }
        System.out.println("Enter the coupon code that you want to edit: ");
        String keyInput = scn.nextLine();
        if(tempCoupon.containsKey(keyInput)){
            Coupon coupon = product.getCouponList().get(keyInput);
            System.out.println("What type do you want the coupon to be? (Percent or Price)");
            String couponOption = scn.nextLine();
            if(couponOption.equalsIgnoreCase("Percent")){
                System.out.println("Please enter the value for the coupon: (Integer)");
                int value = Integer.parseInt(scn.nextLine());
                PercentCoupon newPercentCoupon = new PercentCoupon(keyInput,value);
                tempCoupon.put(keyInput,newPercentCoupon);
            }
            else{
                System.out.println("Please enter the value for the coupon: (Double)");
                double value = Double.parseDouble(scn.nextLine());
                PriceCoupon newPriceCoupon = new PriceCoupon(keyInput,value);
                tempCoupon.put(keyInput,newPriceCoupon);
            }
            product.setCouponList(tempCoupon);
        }

    }
    public static void removeCoupon(Product product){
        Scanner scn = new Scanner(System.in);
        System.out.println("The current product include these coupons");
        Map<String,Coupon> tempCoupon = product.getCouponList();
        for(String keys : tempCoupon.keySet()){
            System.out.println(tempCoupon.get(keys));
        }
        System.out.println("Enter the coupon code that you want to remove: ");
        String couponCode = scn.nextLine();
        tempCoupon.remove(couponCode);
        product.setCouponList(tempCoupon);
    }
    public static int editCouponMenu(){
        String regex = "[0-4]";
        String userInput = "";
        Scanner scn = new Scanner(System.in);
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
        public static void editCouponList(Product product) {
            int userInput = 0;
            Map<String,Coupon> tempCoupon = product.getCouponList();
            while (userInput != 4) {
                userInput = editCouponMenu();
                switch (userInput) {
                    case 1 -> {
                        for(String keys : product.getCouponList().keySet()){
                            System.out.println(product.getCouponList().get(keys));
                        }
                    }
                    case 2 -> {
                        Coupon coupon = createCoupon();
                        tempCoupon.put(coupon.getCouponCode(),coupon);
                        product.setCouponList(tempCoupon);
                    }
                    case 3 -> {
                        editCoupon(product);
                    }
                    case 4 -> {
                        removeCoupon(product);
                    }
                }
            }
        }
}
