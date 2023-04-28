/**
 * @author <Vo Thanh Thong - s3878071>
 */
package ShoppingCart;

import Product.PhysicalProduct;
import Product.Product;
import Product.ProductManager;
import Product.PriceCoupon;
import Product.PercentCoupon;

import java.time.LocalDate;
import java.util.*;


public class ShoppingCart {
    /**
     * Shopping cart attributes
     */
    // Use Set interface
    private Map<String, Integer> items = new HashMap<>();
    private final double BASE = 0.1;
    private static int NEXT_ID = 1;
    private String name;
    private double amount = 0;
    private double shippingFee = 0;
    private double totalWeight;
    private final int cartId;
    private double totalTax;
    private final static Map<String, Product> database = ProductManager.PRODUCTS;
    private String coupon = "";
    private double couponDiscount = 0;

    /**
     * Constructor
     */
    public ShoppingCart() {
        this.cartId = NEXT_ID++;
    }

    public ShoppingCart(int cartId, Map<String, Integer> items) {
        this.cartId = cartId;
        this.items = items;
    }

    public static void resetId() {
        NEXT_ID = 1;
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
    public boolean addItem(String productName, int quantity) {
        if (database.get(productName).getQuantityAvailable() == 0) {
            return false;
        } else if (quantity > database.get(productName).getQuantityAvailable()) {
            return false;
        } else if (this.items.containsKey(productName) & quantity <= database.get(productName).getQuantityAvailable()) {

            int currentQuantity = database.get(productName).getQuantityAvailable();
            database.get(productName).setQuantityAvailable(currentQuantity - quantity);

            this.items.put(productName, this.items.get(productName) + quantity);

            return true;
        } else if (quantity <= database.get(productName).getQuantityAvailable()) {
            int currentQuantity = database.get(productName).getQuantityAvailable();
            database.get(productName).setQuantityAvailable(currentQuantity - quantity);
            items.put(productName, quantity);
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
    public boolean removeItem(String productName, int quantity) {
        if (!database.containsKey(productName)) {
            return false;
        } else {
            int currentQuantity = database.get(productName).getQuantityAvailable();
            int currentCartQuantity = items.get(productName);
            if (quantity == currentCartQuantity) {
                database.get(productName).setQuantityAvailable(currentQuantity + items.get(productName));
                items.remove(productName);
                return true;
            } else if (quantity < currentCartQuantity) {
                database.get(productName).setQuantityAvailable(currentQuantity + items.get(productName));
                items.put(productName, currentCartQuantity - quantity);
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Calculate totalWeight
     * This method is used to calculate the total weight of the cart
     */
    private double calculateWeight() {
        double weight = 0;

        for (String product : this.items.keySet()) {
            if (database.get(product) instanceof PhysicalProduct) {
                weight += ((PhysicalProduct) database.get(product)).getWeight() * this.items.get(product);
            }
        }

        setTotalWeight(weight);
        return weight;
    }

    /**
     * Calculate total coupon discount
     */
    private double calculateCouponDiscount() {
        double total = 0;

        for (String product : this.items.keySet()) {
            if (database.get(product).getCouponList().containsKey(this.coupon)) {
                if (database.get(product).getCouponList().get(this.coupon) instanceof PriceCoupon priceCoupon) {
                    return priceCoupon.getValue() * this.items.get(product);
                } else if (database.get(product).getCouponList().get(this.coupon) instanceof PercentCoupon percentCoupon) {
                    return (percentCoupon.getValue() * database.get(product).getPrice()) / 100 * this.items.get(product);
                }
            }
        }

        setCouponDiscount(total);
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
        double tax = 0;


//        Total amount
        this.amount = 0;

//        Calculate total amount with tax
        for (String product : this.items.keySet()) {
            priceWithTax = database.get(product).getPrice() +
                    (database.get(product).getPrice() * database.get(product).getTaxType().getPercentage());
            this.amount += priceWithTax * this.items.get(product);
            tax += database.get(product).getPrice()* database.get(product).getTaxType().getPercentage();
        }
        setTotalTax(tax);

//        Calculate the total shipping fee
//        this.shippingFee = calculateWeight() * 0.1;
        setShippingFee(calculateWeight() * 0.1);

//        Calculate the total coupon discount
        if (!Objects.equals(getCoupon(), "")) {
            setCouponDiscount(calculateCouponDiscount());
        }

        setAmount(this.amount + this.shippingFee - this.couponDiscount);
        return this.amount + this.shippingFee - this.couponDiscount;
    }

    /**
     * Display all product in cart
     */
    public void displayAllProducts() {
        for (String product : items.keySet()) {
            System.out.println(product + ": " + items.get(product));
        }
    }

    /**
     * View detail of cart include total price, tax, shipping fee, coupon discount
     */
    public void viewDetails() {
        System.out.printf("Cart %d, Total Price: %.2f, Total Tax: %.2f, Shipping fee: %.2f, Coupon discount: %.2f \n",
                cartId, amount, totalTax, shippingFee, couponDiscount);
    }


    /**
     * Getter and Setter methods
     */

    public double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }


    public static int getNextId() {
        return NEXT_ID;
    }

    public static void setNextId(int nextId) {
        NEXT_ID = nextId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public void setCouponDiscount(double couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalWeight() {
        return calculateWeight();
    }

    public Map<String, Integer> getItems() {
        return items;
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
        return "Cart ID : "+ cartId +
                ", Products: " + items +
                ", totalWeight: " + getTotalWeight();
    }

    public String toFile() {
        return String.format(
                "%d,%.2f,%.2f,%.2f",
                cartId,
                amount,
                totalWeight,
                shippingFee
        );
    }

    /**
     * Display the purchase receipt
     */

    public void displayReceipt() {
        System.out.println("----------------RECEIPT----------------");
        System.out.println("Cart: " + this.cartId);
        System.out.println("Date of purchase: " + LocalDate.now());
        System.out.println("Items:");
        for (Map.Entry<String, Integer> items : this.getItems().entrySet()) {
            Product product = database.get(items.getKey());
            System.out.println("Name: " + items.getKey() + "\t" + "Price: " + product.getPrice() + "\t" + "Tax: " + product.getTaxType() + "Quantity: " + items.getValue());
        }
        System.out.println("Shipping fee: " + this.shippingFee);
        System.out.println("Total amount: " + this.cartAmount());
    }


}
