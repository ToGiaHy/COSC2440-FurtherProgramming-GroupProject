/**
 * @author Group 11
 */
package product;

import utils.Coupon;
import utils.TaxType;

import java.util.HashMap;
import java.util.Map;

public abstract class Product {
    /**
     * Product attributes
     */
    private String name;
    private String description;
    private int quantityAvailable;
    private double price;
    private TaxType taxType;

    private Map<String, Coupon> couponList;

    /**
     * Constructor
     */
    public Product(String name, String description, int quantityAvailable, double price, TaxType taxType) {
        this.name = name;
        this.description = description;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
        this.taxType = taxType;
        this.couponList = new HashMap<>();
    }

    public Product(String name, String description, int quantityAvailable, double price, TaxType taxType, Map<String, Coupon> couponList) {
        this.name = name;
        this.description = description;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
        this.taxType = taxType;
        this.couponList = couponList;
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
    public Map<String, Coupon> getCouponList() {
        return couponList;
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

    public void setCouponList(Map<String, Coupon> couponList) {
        this.couponList = couponList;
    }

    @Override
    public abstract String toString();

    // Display a product's coupon list
    public void displayCoupons() {
        for (String keys : this.couponList.keySet()) {
            System.out.println(this.couponList.get(keys));
        }
    }

}
