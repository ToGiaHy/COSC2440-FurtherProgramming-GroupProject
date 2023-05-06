/**
 * @author <Vo Thanh Thong - s3878071>
 */
package ShoppingCart;

import Product.PhysicalProduct;
import Product.Product;

import Product.PriceCoupon;
import Product.PercentCoupon;
import Product.CanBeGifted;
import Product.PhysicalProductCanBeGifted;
import Product.DigitalProductCanBeGifted;

import java.time.LocalDate;
import java.util.*;

import Product.ProductController;

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
    private String cartId;
    private double totalTax;

    private Map<String, String> giftProductList;

    ProductController productController = new ProductController();
    private String coupon = "";
    private double couponDiscount = 0;

    /**
     * Constructor
     */
    public ShoppingCart(ProductController productController) {
        this.cartId = "C" + NEXT_ID++;
        this.productController = productController;
    }

    public ShoppingCart(String cartId, Map<String, Integer> items, String coupon, Map<String, String> giftProductList, ProductController productController) {
        this.cartId = cartId;
        this.items = items;
        this.coupon = coupon;
        this.giftProductList = giftProductList;
        this.productController = productController;
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
        if (productController.productList().get(productName).getQuantityAvailable() == 0) {
            return false;
        } else if (quantity > productController.productList().get(productName).getQuantityAvailable()) {
            return false;
        } else if (this.items.containsKey(productName) & quantity <= productController.productList().get(productName).getQuantityAvailable()) {

            int currentQuantity = productController.productList().get(productName).getQuantityAvailable();
            productController.productList().get(productName).setQuantityAvailable(currentQuantity - quantity);

            this.items.put(productName, this.items.get(productName) + quantity);
            if (productController.productList().get(productName) instanceof PhysicalProductCanBeGifted || productController.productList().get(productName) instanceof DigitalProductCanBeGifted) {
                giftProductList.put(productName, "");
            }
            return true;
        } else if (quantity <= productController.productList().get(productName).getQuantityAvailable()) {
            int currentQuantity = productController.productList().get(productName).getQuantityAvailable();
            productController.productList().get(productName).setQuantityAvailable(currentQuantity - quantity);
            items.put(productName, quantity);
            if (productController.productList().get(productName) instanceof PhysicalProductCanBeGifted || productController.productList().get(productName) instanceof DigitalProductCanBeGifted) {
                giftProductList.put(productName, "");
            }
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
        if (!productController.productList().containsKey(productName)) {
            return false;
        } else {
            int currentQuantity = productController.productList().get(productName).getQuantityAvailable();
            int currentCartQuantity = items.get(productName);
            if (quantity == currentCartQuantity) {
                productController.productList().get(productName).setQuantityAvailable(currentQuantity + items.get(productName));
                items.remove(productName);
                return true;
            } else if (quantity < currentCartQuantity) {
                productController.productList().get(productName).setQuantityAvailable(currentQuantity + items.get(productName));
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
            if (productController.productList().get(product) instanceof PhysicalProduct) {
                weight += ((PhysicalProduct) productController.productList().get(product)).getWeight() * this.items.get(product);
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
            if (productController.productList().get(product).getCouponList().containsKey(this.coupon)) {
                if (productController.productList().get(product).getCouponList().get(this.coupon) instanceof PriceCoupon priceCoupon) {
                    return priceCoupon.getValue() * this.items.get(product);
                } else if (productController.productList().get(product).getCouponList().get(this.coupon) instanceof PercentCoupon percentCoupon) {
                    return (percentCoupon.getValue() * productController.productList().get(product).getPrice()) / 100 * this.items.get(product);
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
            priceWithTax = productController.productList().get(product).getPrice() +
                    (productController.productList().get(product).getPrice() * productController.productList().get(product).getTaxType().getPercentage());
            this.amount += priceWithTax * this.items.get(product);
            tax += productController.productList().get(product).getPrice() * productController.productList().get(product).getTaxType().getPercentage();
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
        cartAmount();
        System.out.printf("Cart %s, Total Price: %.2f, Total Tax: %.2f, Shipping fee: %.2f, Coupon discount: %.2f \n",
                cartId, amount, totalTax, shippingFee, couponDiscount);
    }


    public void setCartId(String cartId) {
        this.cartId = cartId;
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

    public Map<String, String> getGiftProductList() {
        return giftProductList;
    }

    public void setGiftProductList(Map<String, String> giftProductList) {
        this.giftProductList = giftProductList;
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

    public ProductController getProductController() {
        return productController;
    }

    public void setProductController(ProductController productController) {
        this.productController = productController;
    }

    @Override
    public String toString() {
        return "Cart ID : " + cartId +
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
            Product product = productController.productList().get(items.getKey());
            System.out.println("Name: " + items.getKey() + "\t" + "Price: " + product.getPrice() + "\t" + "Tax: " + product.getTaxType() + " Quantity: " + items.getValue());
        }
        System.out.println("Shipping fee: " + this.shippingFee);
        System.out.println("Total amount: " + this.cartAmount());
    }

    public String receiptToFile() {
        return String.format("%s,%s,%.2f\n", this.cartId, this.purchaseDate, this.cartAmount());
    }

    public String receiptToFileCustomName() {
        StringBuilder products = new StringBuilder();
        for (Map.Entry<String, Integer> items : this.getItems().entrySet()) {
            Product product = productController.productList().get(items.getKey());
            products.append("Name: ").append(items.getKey()).append("\t").append("Price: ").append(product.getPrice()).append("\t")
                    .append("Tax: ").append(product.getTaxType()).append(" Quantity: ").append(items.getValue()).append("\n");
        }
        return String.format("----------------RECEIPT----------------\nCart: %s\nDate of purchase: %s\nItems: " + products +
                "Shipping fee: %.2f\nTotal amount: %.2f", this.cartId, this.purchaseDate, this.shippingFee, this.cartAmount());
    }

}
