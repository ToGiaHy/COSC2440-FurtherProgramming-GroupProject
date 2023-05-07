/**
 * @author Group 11
 */
package Product;

public class PriceCoupon extends Coupon {
    private double value;
    public PriceCoupon(String couponCode, double value) {
        super(couponCode);
        this.value = value;
    }
    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return super.getCouponCode() + " " + getValue();
    }
}
