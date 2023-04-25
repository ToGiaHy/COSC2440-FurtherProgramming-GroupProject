/**
 * @author <Vo Thanh Thong - s3878071>
 */
package ShoppingCart;

import Product.PhysicalProduct;
import Product.Product;
import Product.ProductManager;
import Product.PriceCoupon;
import Product.PercentCoupon;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class ShoppingCart {
    /**
     * Shopping cart attributes
     */
    // Use Set interface
    private final HashMap<String, Integer> PRODUCTS = new HashMap<>();
    private final double BASE = 0.1;
    private static int NEXT_ID = 1;
    private String name;
    private double amount = 0;
//    public double totalWeight;
    private double shippingFee = 0;
    public double totalWeight;
    private final int cartId;
    private final static Map<String, Product> database = ProductManager.PRODUCTS;
    private String coupon = "";
    private Double couponDiscount = (double) 0;

    /**
     * Constructor
     */
    public ShoppingCart() {
        this.cartId = NEXT_ID++;
    }

    public static void resetId() { NEXT_ID = 1; }

    /**
     * Add the product with the given name to the shopping cart
     * <p>
     * Add the product with the given name to the shopping cart;
     * If the quantity available of the product is zero, do nothing
     * and return false;
     * If the product with the given name exists in the cart already,
     * do nothing and return false;
     * otherwise, decrease the quantity available of that product by one,
     * add the product name to the cart, and return true.
     * </p>
     */
    public boolean addItem(String productName, int quantity) {
        if (database.get(productName).getQuantityAvailable() == 0) {
            return false;
        } else if (quantity > database.get(productName).getQuantityAvailable()) {
            return false;
        } else if (this.PRODUCTS.containsKey(productName) & quantity <= database.get(productName).getQuantityAvailable()) {

            int currentQuantity = database.get(productName).getQuantityAvailable();
            database.get(productName).setQuantityAvailable(currentQuantity - quantity);

            this.PRODUCTS.put(productName, this.PRODUCTS.get(productName) + quantity);

            return true;
        } else if (quantity <= database.get(productName).getQuantityAvailable()) {
            int currentQuantity = database.get(productName).getQuantityAvailable();
            database.get(productName).setQuantityAvailable(currentQuantity - quantity);
            PRODUCTS.put(productName, quantity);
            return true;
        }
        return false;
    }

    /**
     * Remove the product with the given name from the shopping cart
     * <p>
     * Remove the product with the given name from the shopping cart;
     * If the product with the given name does not exist in the cart,
     * do nothing and return false;
     * Otherwise, increase the quantity available of that product by one,
     * remove the product name from the cart, and return true.
     * </p>
     */
    public boolean removeItem(String productName) {
        if (!database.containsKey(productName)) {
            return false;
        } else {
            int currentQuantity = database.get(productName).getQuantityAvailable();
            database.get(productName).setQuantityAvailable(currentQuantity + PRODUCTS.get(productName));
            PRODUCTS.remove(productName);
            return true;
        }
    }

    /**
     * Calculate totalWeight
     * This method is used to calculate the total weight of the cart
     */
    private double calculateWeight() {
        double weight = 0;

        for (String product : this.PRODUCTS.keySet()) {
            if (database.get(product) instanceof PhysicalProduct) {
                weight += ((PhysicalProduct) database.get(product)).getWeight()*this.PRODUCTS.get(product);
            }
        }
        return weight;
    }

    /**
     * Calculate total coupon discount
     *
     */
    private double calculateCouponDiscount() {
        double total = 0;

        for (String product : this.PRODUCTS.keySet()) {
            if (database.get(product).getCouponList().containsKey(this.coupon)) {
                if (database.get(product).getCouponList().get(this.coupon) instanceof PriceCoupon priceCoupon) {
                    return priceCoupon.getValue() * this.PRODUCTS.get(product);
                } else if (database.get(product).getCouponList().get(this.coupon) instanceof PercentCoupon percentCoupon) {
                    return (percentCoupon.getValue() * database.get(product).getPrice()) / 100 * this.PRODUCTS.get(product);
                }
            }
        }

        return total;
    }

    /**
     * Calculate and @return total price amount of all products in the cart
     * Note: if the cart contains physical products, the shipping fee will be
     * calculated and added to the total price of the cart
     * Shipping fee = (totalWeight of all physical products) * (base)
     * Base fee = 0.1
     */
    public double cartAmount() {
//        Tax
        double priceWithTax = 0;


//        Total amount
        this.amount = 0;

//        Calculate total amount with tax
        for (String product : this.PRODUCTS.keySet()) {
            priceWithTax = database.get(product).getPrice() +
                    (database.get(product).getPrice()* database.get(product).getTaxType().getPercentage());
            this.amount += priceWithTax * this.PRODUCTS.get(product);
        }

//        Calculate the total shipping fee
        this.shippingFee = calculateWeight() * 0.1;

//        Calculate the total coupon discount
        if (!Objects.equals(getCoupon(), "")) {
            this.couponDiscount = calculateCouponDiscount();
        }

        return this.amount + this.shippingFee - this.couponDiscount;
    }

    /**
     * Getter and Setter methods
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalWeight() {
        return calculateWeight();
    }

    public HashMap<String, Integer> getPRODUCTS() {
        return PRODUCTS;
    }
    public int getId() {
        return cartId;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public Double getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(Double couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    @Override
    public String toString() {
        return getName() + ":" +
                "Products: " + PRODUCTS +
                ", totalWeight: " + getTotalWeight() +
                ", amount: " + cartAmount() +
                ", base: " + BASE +
                ", shipping fee: " + shippingFee +
                ", coupon discount" + couponDiscount;
    }

    public String toFile() {
        return String.format(
                "%d,%s,%.2f,%.2f,%.2f",
                cartId,
                name,
                amount,
                totalWeight,
                shippingFee
        );
    }
}
