/**
 * @author <Vo Thanh Thong - s3878071>
 */

package Product;

import java.util.Map;

public class PhysicalProductCanBeGifted extends PhysicalProduct implements CanBeGifted {
    /**
     * Physical product gift attributes
     */
    private String message;
    /**
     * The constructor is not inherited,
     * but we can access it with "super"
     *
     * @param name              product name
     * @param description       product description
     * @param quantityAvailable the number of products are available to buy
     * @param price             product price
     * @param weight            product weight (Only physical product have weight attribute)
     */
    public PhysicalProductCanBeGifted(String name, String description, int quantityAvailable, double price, TaxType taxType, double weight, Map<String,Coupon> couponList, String message) {

        super(name, description, quantityAvailable, price, taxType, couponList, weight);
        this.message = message;
    }
    /**
     * String representation of this product
     * <p>
     * This method is called automatically when you use a PhysicalProductGift
     * object in places where a String value is required.
     * </p>
     */
    @Override
    public String toString() {
        return String.format("Physical Product Name: %s, Description: %s, Quantity Available: %d, Price: %.2f, Tax Type: %s, Weight: %.2f",
                this.getName(),
                this.getDescription(),
                this.getQuantityAvailable(),
                this.getPrice(),
                this.getTaxType().toString(),
                this.getWeight()
        );
    }
    /**
     * Getter and Setter method of interface class
     * <p>
     * This method is used to get or set message for physical product which is used as a gift.
     * </p>
     */
    @Override
    public void setMessage(String msg) {
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
