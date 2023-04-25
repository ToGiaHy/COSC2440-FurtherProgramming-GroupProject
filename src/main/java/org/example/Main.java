/**
 * @author <Vo Thanh Thong - s3878071>
 */

package org.example;

import Product.*;
import SystemUI.UserUI;
import io.ReadProductsFile;
import io.WriteProductsFile;
import java.util.HashMap;


public class Main {

    public static void main(String[] args) {
//        ProductManager.PRODUCTS = ReadProductsFile.readProductsToDatabase("./data/products.txt");
//        UserUI.userUI();
//        WriteProductsFile.writeProductsToDatabase(ProductManager.PRODUCTS, "./data/products.txt");

        PriceCoupon pc1 = new PriceCoupon("pricec1", 5.3);

        System.out.println(pc1);

        PercentCoupon pc2 = new PercentCoupon("percentc1", 10);

        DigitalProduct p1 = new DigitalProduct("Art work", "this is an art work", 12, 13.0, TaxType.LUXURY, new HashMap<String,Coupon>());

        p1.getCouponList().put(pc1.toString(),pc1);
        p1.getCouponList().put(pc2.toString(),pc2);

        HashMap<String,Coupon> tempt = p1.getCouponList();
//        System.out.println(tempt);
        PriceCoupon p =  (PriceCoupon) tempt.get(pc1.toString());
        System.out.println(p.getValue());


    }

}