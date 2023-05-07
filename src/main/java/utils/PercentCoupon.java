/**
 * @author Group 11
 */
package utils;

public class PercentCoupon extends Coupon {
    private int value;
    public PercentCoupon(String couponCode, int value) {
        super(couponCode);
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return super.getCouponCode() + " " + value + "%";
    }
}
