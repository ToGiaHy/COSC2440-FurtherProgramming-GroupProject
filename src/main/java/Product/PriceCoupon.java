package Product;

public class PriceCoupon implements Coupon{
    private double discount;

    public PriceCoupon(double discount) {
        this.discount = discount;
    }

    @Override
    public double getDiscount() {
        return 0;
    }

    @Override
    public void setDiscount(double discount) {

    }
}
