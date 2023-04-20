package Product;

public class PercentCoupon extends Coupon{
    public PercentCoupon(double discount) {
        super(discount);
    }

    @Override
    public double getDiscount() {
        return 0;
    }

    @Override
    public void setDiscount(double discount) {

    }
}
