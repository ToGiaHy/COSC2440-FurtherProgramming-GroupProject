/**
 * @author Group 11
 */
package utils;

import java.util.HashSet;
import java.util.Set;

public class CouponDatabase {
    //attribute
    private final Set<String> COUPONS = new HashSet<>();

    //method
    public Set<String> getCOUPONS() {
        return COUPONS;
    }

    public boolean addCode(String code) {
        return this.COUPONS.add(code);
    }

    public void removeCode(String code) {
        this.COUPONS.remove(code);
    }
}
