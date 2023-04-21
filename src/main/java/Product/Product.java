/**
 * @author <Vo Thanh Thong - s3878071>
 */
package Product;

public abstract class Product {
    /**
     * Product attributes
     */
    private String name;
    private String description;
    private int quantityAvailable;
    private double price;

    private Coupon coupon;
    /**
     * Constructor
     */
    public Product(String name, String description, int quantityAvailable, double price) {
        this.name = name;
        this.description = description;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
    }

    public Product(String name, String description, int quantityAvailable, double price, Coupon coupon) {
        this.name = name;
        this.description = description;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
        this.coupon = coupon;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    /**
     * Getter methods
     */

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public double getPrice() {
        return price;
    }

    /**
     * Setter methods
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public abstract String toString();

    public String toFile() {
        return String.format("%s,%s,%d,%f",this.getName(), this.getDescription(), this.getQuantityAvailable(), this.getPrice());
    }
}
