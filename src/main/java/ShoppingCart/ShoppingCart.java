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
    private static final double BASE = 0.1;
    private static int NEXT_ID = 1;
    private String name;
    private double amount = 0;
    private LocalDate purchaseDate;
    private double shippingFee = 0;
    private double totalWeight;
    private final String cartId;
    private double totalTax;

    private ProductManager productManager;
    private String coupon = "";
    private double couponDiscount = 0;

    /**
     * Constructor
     */
    public ShoppingCart(ProductManager productManager) {
        this.cartId = "C" + NEXT_ID++;
        this.productManager = productManager;
    }

    public ShoppingCart(String cartId, Map<String, Integer> items, String coupon, ProductManager productManager) {
        this.cartId = cartId;
        this.items = items;
        this.coupon = coupon;
        this.productManager = productManager;
        this.purchaseDate = LocalDate.now();
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
        if (productManager.getPRODUCTS().get(productName).getQuantityAvailable() == 0) {
            return false;
        } else if (quantity > productManager.getPRODUCTS().get(productName).getQuantityAvailable()) {
            return false;
        } else if (this.items.containsKey(productName) & quantity <= productManager.getPRODUCTS().get(productName).getQuantityAvailable()) {

            int currentQuantity = productManager.getPRODUCTS().get(productName).getQuantityAvailable();
            productManager.getPRODUCTS().get(productName).setQuantityAvailable(currentQuantity - quantity);

            this.items.put(productName, this.items.get(productName) + quantity);

            return true;
        } else if (quantity <= productManager.getPRODUCTS().get(productName).getQuantityAvailable()) {
            int currentQuantity = productManager.getPRODUCTS().get(productName).getQuantityAvailable();
            productManager.getPRODUCTS().get(productName).setQuantityAvailable(currentQuantity - quantity);
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
        if (!productManager.getPRODUCTS().containsKey(productName)) {
            return false;
        } else {
            int currentQuantity = productManager.getPRODUCTS().get(productName).getQuantityAvailable();
            int currentCartQuantity = items.get(productName);
            if (quantity == currentCartQuantity) {
                productManager.getPRODUCTS().get(productName).setQuantityAvailable(currentQuantity + items.get(productName));
                items.remove(productName);
                return true;
            } else if (quantity < currentCartQuantity) {
                productManager.getPRODUCTS().get(productName).setQuantityAvailable(currentQuantity + items.get(productName));
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
            if (productManager.getPRODUCTS().get(product) instanceof PhysicalProduct) {
                weight += ((PhysicalProduct) productManager.getPRODUCTS().get(product)).getWeight() * this.items.get(product);
            }
        }
        return weight;
    }

    /**
     * Calculate total coupon discount
     */
    private double calculateCouponDiscount() {
        double total = 0;

        for (String product : this.items.keySet()) {
            if (productManager.getPRODUCTS().get(product).getCouponList().containsKey(this.coupon)) {
                if (productManager.getPRODUCTS().get(product).getCouponList().get(this.coupon) instanceof PriceCoupon priceCoupon) {
                    return priceCoupon.getValue() * this.items.get(product);
                } else if (productManager.getPRODUCTS().get(product).getCouponList().get(this.coupon) instanceof PercentCoupon percentCoupon) {
                    return (percentCoupon.getValue() * productManager.getPRODUCTS().get(product).getPrice()) / 100 * this.items.get(product);
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
        double priceWithTax;
        double tax = 0;


//        Total amount
        this.amount = 0;

//        Calculate total amount with tax
        for (String product : this.items.keySet()) {
            priceWithTax = productManager.getPRODUCTS().get(product).getPrice() +
                    (productManager.getPRODUCTS().get(product).getPrice() * productManager.getPRODUCTS().get(product).getTaxType().getPercentage());
            this.amount += priceWithTax * this.items.get(product);
            tax += productManager.getPRODUCTS().get(product).getPrice()* productManager.getPRODUCTS().get(product).getTaxType().getPercentage();
        }
        setTotalTax(tax);

//        Calculate the total shipping fee
//        this.shippingFee = calculateWeight() * 0.1;
        setShippingFee(calculateWeight() * BASE);

//        Calculate the total coupon discount
        if (!Objects.equals(getCoupon(), "")) {
            setCouponDiscount(calculateCouponDiscount());
        }
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

    public String getId() {
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

    public ProductManager getProductManager() {
        return productManager;
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }


    @Override
    public String toString() {
        return "Cart ID : "+ cartId +
                ", Products: " + items +
                ", totalWeight: " + getTotalWeight();
    }

    /**
     * Display the purchase receipt
     */

    public void displayReceipt() {
        System.out.println("----------------RECEIPT----------------");
        System.out.println("Cart: " + this.cartId);
        System.out.println("Date of purchase: " + this.purchaseDate);
        System.out.println("Items:");
        for (Map.Entry<String, Integer> items : this.getItems().entrySet()) {
            Product product = productManager.getPRODUCTS().get(items.getKey());
            System.out.println("Name: " + items.getKey() + "\t" + "Price: " + product.getPrice() + "\t" + "Tax: " + product.getTaxType() + "Quantity: " + items.getValue());
        }
        System.out.println("Shipping fee: " + this.shippingFee);
        System.out.println("Total amount: " + this.cartAmount());
    }

    public String receiptToFile() {
        return String.format("%s,%s,%s", this.cartId, this.purchaseDate, this.cartAmount());
    }


}
