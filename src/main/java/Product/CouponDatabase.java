package Product;

import java.util.HashSet;

public class CouponDatabase {
    //attribute
    public static final HashSet<String> COUPONLIST = new HashSet<>();

    //method

    public void addCode(String code){
        COUPONLIST.add(code);
    }


}
