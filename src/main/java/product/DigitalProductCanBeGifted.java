/**
 * @author Group 11
 */
package product;

import utils.Coupon;
import utils.TaxType;

import java.util.Map;

public class DigitalProductCanBeGifted extends DigitalProduct implements CanBeGifted {
    /**
     * Digital product gift attributes
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
     */
    public DigitalProductCanBeGifted(String name, String description, int quantityAvailable, double price, TaxType taxType, String message) {
        super(name, description, quantityAvailable, price, taxType);
        this.message = message;
    }

    public DigitalProductCanBeGifted(String name, String description, int quantityAvailable, double price, TaxType taxType, Map<String, Coupon> couponList, String message) {
        super(name, description, quantityAvailable, price, taxType, couponList);
        this.message = message;
    }

    /**
     * String representation of this product
     * <p>
     * This method is called automatically when you use a DigitalProductGift
     * object in places where a String value is required.
     * </p>
     */
    @Override
    public String toString() {
        return String.format("Digital Product Gift Name: %s, Description: %s, Quantity Available: %d, Price: %.2f, Tax Type: %s",
                this.getName(),
                this.getDescription(),
                this.getQuantityAvailable(),
                this.getPrice(),
                this.getTaxType().toString()
        );
    }

    /**
     * Getter and Setter method of interface class
     * <p>
     * This method is used to get or set message for digital product which is used as a gift.
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
