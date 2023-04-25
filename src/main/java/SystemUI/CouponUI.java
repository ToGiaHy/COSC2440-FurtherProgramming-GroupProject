package SystemUI;

import java.util.Scanner;
import Product.*;
public class CouponUI {
    public Coupon createCoupon(){
        Scanner scn = new Scanner(System.in);
        System.out.println("Which type of coupon would you like?(Percent or Price)");
        String option = scn.nextLine();
        if(option.equalsIgnoreCase("percent")){
            System.out.println("Please write the coupon code for the coupon: ");
            String couponCode = scn.nextLine();
            System.out.println("Please write the value for the percentage: ");
            int value = Integer.parseInt(scn.nextLine());
            return new PercentCoupon(couponCode,value);
        } else {
            System.out.println("Please write the coupon code for the coupon: ");
            String couponCode = scn.nextLine();
            System.out.println("Please write the value for the price: ");
            double value = Double.parseDouble(scn.nextLine());
            return new PriceCoupon(couponCode,value);
        }
    }
}
