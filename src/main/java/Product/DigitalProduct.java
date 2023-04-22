/**
 * @author <Vo Thanh Thong - s3878071>
 */
package Product;


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
        return "DIGITAL - " + name;
    }

    @Override
    public String toFile() {
        return String.format("DigitalProduct,%s,%s,%d,%.2f",
                this.getName(),
                this.getDescription(),
                this.getQuantityAvailable(),
                this.getPrice()
        );
    }
}
