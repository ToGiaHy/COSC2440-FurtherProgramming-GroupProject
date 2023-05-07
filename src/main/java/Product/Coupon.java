/**
 * @author Group 11
 */
package Product;

public abstract class Coupon {
    private String couponCode;

    public Coupon(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    @Override
    public abstract String toString();
}
