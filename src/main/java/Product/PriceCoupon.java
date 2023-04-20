package Product;

public class PriceCoupon extends Coupon{
    public PriceCoupon(double discount) {
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
