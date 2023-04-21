package Product;

public class PercentCoupon implements Coupon{
    private double discount;

    public PercentCoupon(double discount) {
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
