/**
 * @author <Vo Thanh Thong - s3878071>
 */

package Product;

import java.util.Map;

public class PhysicalProduct extends Product{
    /**
     * Physical product attributes
     */
    private double weight;

    /**
     * The constructor is not inherited,
     * but we can access it with "super"
     * @param name product name
     * @param description product description
     * @param quantityAvailable the number of products are available to buy
     * @param price product price
     * @param weight product weight (Only physical product have weight attribute)
     */
    public PhysicalProduct(String name, String description, int quantityAvailable, double price, TaxType taxType, double weight) {
        super(name, description, quantityAvailable, price, taxType);
        this.weight = weight;
    }

    public PhysicalProduct(String name, String description, int quantityAvailable, double price, TaxType taxType, Map<String,Coupon> couponList, double weight) {
        super(name, description, quantityAvailable, price, taxType, couponList);
        this.weight = weight;
    }

    /**
     * Getter methods
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Setter methods
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    /**
     * String representation of this product
     * <p>
     * This method is called automatically when you use a PhysicalProduct
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
                this.weight
        );
    }

}
