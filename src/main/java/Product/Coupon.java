package Product;

public abstract class Coupon {
    protected double discount;

    public Coupon(double discount) {
        this.discount = discount;
    }

    public abstract double getDiscount();

    public abstract void setDiscount(double discount);
}
