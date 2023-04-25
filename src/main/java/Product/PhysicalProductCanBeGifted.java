/**
 * @author <Vo Thanh Thong - s3878071>
 */

package Product;

import java.util.HashMap;

public class PhysicalProductCanBeGifted extends PhysicalProduct implements CanBeGifted {
    /**
     * Physical product gift attributes
     */
    private String message;

    /**
     * The constructor is not inherited,
     * but we can access it with "super"
     * @param name product name
     * @param description product description
     * @param quantityAvailable the number of products are available to buy
     * @param price product price
     * @param weight product weight (Only physical product have weight attribute)
     */
    public PhysicalProductCanBeGifted(String name, String description, int quantityAvailable, double price, TaxType taxType, double weight, String message) {
        super(name, description, quantityAvailable, price, taxType, weight);
        this.message = message;
    }

    public PhysicalProductCanBeGifted(String name, String description, int quantityAvailable, double price, TaxType taxType, double weight) {
        super(name, description, quantityAvailable, price, taxType, weight);
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
        return "PHYSICAL GIFT - " + super.getName();
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

    @Override
    public String toFile() {
        return String.format(
                "GiftPhysicalProduct,%s,%s,%d,%.2f,%s,%.2f,%s",
                this.getName(),
                this.getDescription(),
                this.getQuantityAvailable(),
                this.getPrice(),
                this.getTaxType().toString(),
                this.getWeight(),
                this.message
        );
    }
}
