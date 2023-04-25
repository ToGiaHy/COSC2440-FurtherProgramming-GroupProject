/**
 * @author <Vo Thanh Thong - s3878071>
 */
package Product;


import java.util.HashMap;

public class DigitalProduct extends Product {

    /**
     * The constructor is not inherited,
     * but we can access it with "super"
     * @param name product name
     * @param description product description
     * @param quantityAvailable the number of products are available to buy
     * @param price product price
     */
    public DigitalProduct(String name, String description, int quantityAvailable, double price, TaxType taxType) {
        super(name, description, quantityAvailable, price, taxType);
    }

    public DigitalProduct(String name, String description, int quantityAvailable, double price, TaxType taxType, HashMap<String,Coupon> couponList) {
        super(name, description, quantityAvailable, price, taxType, couponList);
    }



    /**
     * String representation of this product
     * <p>
     * This method is called automatically when you use a DigitalProduct
     * object in places where a String value is required.
     * </p>
     */
    @Override
    public String toString() {
        String name = getName();
        return String.format("DigitalProduct %s ,%s,%d,%.2f,%s,%s",
                this.getName(),
                this.getDescription(),
                this.getQuantityAvailable(),
                this.getPrice(),
                this.getTaxType().toString(),
                this.getCouponList().toString());
    }

    @Override
    public String toFile() {
        return String.format("DigitalProduct,%s,%s,%d,%.2f,%s,%s",
                this.getName(),
                this.getDescription(),
                this.getQuantityAvailable(),
                this.getPrice(),
                this.getTaxType().toString(),
                this.getCouponList().toString()
        );
    }
}
