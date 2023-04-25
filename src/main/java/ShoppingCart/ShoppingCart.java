/**
 * @author <Vo Thanh Thong - s3878071>
 */
package ShoppingCart;

import Product.PhysicalProduct;
import Product.Product;
import Product.ProductManager;

import java.util.HashMap;
import java.util.Map;


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
                weight += ((PhysicalProduct) database.get(product)).getWeight();
            }
        }
        return weight;
    }

    /**
     * Calculate total coupon discount
     *
     */
//    private double calculateCouponDiscount() {
//
//    }

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
            priceWithTax = database.get(product).getPrice() -
                    (database.get(product).getPrice()* database.get(product).getTaxType().getPercentage());
            this.amount += priceWithTax ;
        }

//        Calculate the total shipping fee
        this.shippingFee = calculateWeight() * 0.1;

//        Calculate the total coupon discount
        if (getCoupon() != "") {
//            todo Calculate coupon
        }

        return this.amount + this.shippingFee;
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

    @Override
    public String toString() {
        return getName() + ":" +
                "Products: " + PRODUCTS +
                ", totalWeight: " + getTotalWeight() +
                ", amount: " + cartAmount() +
                ", base: " + BASE +
                ", shipping fee: " + shippingFee;
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
