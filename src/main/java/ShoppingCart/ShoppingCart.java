/**
 * @author <Vo Thanh Thong - s3878071>
 */
package ShoppingCart;

import Product.PhysicalProduct;
import Product.ProductManager;

import java.util.HashSet;
import java.util.Set;

public class ShoppingCart {
    /**
     * Shopping cart attributes
     */
    // Use Set interface
    private final Set<String> PRODUCTS = new HashSet<String>();
    private final double BASE = 0.1;
    private String name;
    private double amount = 0;
    public double totalWeight;
    private double shippingFee = 0;

    /**
     * Constructor
     */
    public ShoppingCart() {
    }

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
    public boolean addItem(String productName){
        if (ProductManager.PRODUCTS.get(productName).getQuantityAvailable() == 0) {
            return false;
        } else if (this.PRODUCTS.contains(productName)) {
            return false;
        } else if (this.PRODUCTS.add(productName)) {
            int currentQuantity = ProductManager.PRODUCTS.get(productName).getQuantityAvailable();
            ProductManager.PRODUCTS.get(productName).setQuantityAvailable(currentQuantity - 1);
            PRODUCTS.add(productName);
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
        if (!ProductManager.PRODUCTS.containsKey(productName)) {
            return false;
        }
        if (this.PRODUCTS.remove(productName)) {
            int currentQuantity = ProductManager.PRODUCTS.get(productName).getQuantityAvailable();
            ProductManager.PRODUCTS.get(productName).setQuantityAvailable(currentQuantity + 1);
            PRODUCTS.remove(productName);
            return true;
        }
        return false;
    }

    /**
     * Calculate totalWeight
     * This method is used to calculate the total weight of the cart
     */
    private double calculateWeight() {
        double weight = 0;

        for (String product : this.PRODUCTS) {
            if (ProductManager.PRODUCTS.get(product) instanceof PhysicalProduct) {
                weight += ((PhysicalProduct) ProductManager.PRODUCTS.get(product)).getWeight();
            }
        }
        return weight;
    }

    /**
     * Calculate and @return total price amount of all products in the cart
     * Note: if the cart contains physical products, the shipping fee will be
     * calculated and added to the total price of the cart
     * Shipping fee = (totalWeight of all physical products) * (base)
     * Base fee = 0.1
     */
    public double cartAmount() {
        this.amount = 0;
        for (String product: this.PRODUCTS) {
            this.amount += ProductManager.PRODUCTS.get(product).getPrice();
        }

        this.shippingFee = calculateWeight() * 0.1;
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

    public Set<String> getPRODUCTS() {
        return PRODUCTS;
    }

    @Override
    public String toString() {
        return  getName() + ":" +
                "Products: " + PRODUCTS +
                ", totalWeight: " + getTotalWeight() +
                ", amount: " + cartAmount() +
                ", base: " + BASE +
                ", shipping fee: " + shippingFee;
    }
}
