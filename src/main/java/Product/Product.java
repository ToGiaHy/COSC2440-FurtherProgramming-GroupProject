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
    private TaxType taxType;

    private Coupon coupon;
    /**
     * Constructor
     */
    public Product(String name, String description, int quantityAvailable, double price, TaxType taxType) {
        this.name = name;
        this.description = description;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
        this.taxType = taxType;
    }

    public Product(String name, String description, int quantityAvailable, double price, TaxType taxType, Coupon coupon) {
        this.name = name;
        this.description = description;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
        this.taxType = taxType;
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
    public TaxType getTaxType() {
        return taxType;
    }

    // Get the product's tax
    public Coupon getCoupon() {
        return coupon;
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

    public void setTaxType(TaxType taxType) {
        this.taxType = taxType;
    }
    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    @Override
    public abstract String toString();
}
