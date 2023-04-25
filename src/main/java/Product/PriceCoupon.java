package Product;

public class PriceCoupon extends Coupon{
    double value;

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
        return super.getCouponCode();
    }
}
