/**
 * @author Group 11
 */
package shoppingcart;

import product.PhysicalProduct;
import product.Product;

import utils.PriceCoupon;
import utils.PercentCoupon;
import product.PhysicalProductCanBeGifted;
import product.DigitalProductCanBeGifted;

import java.time.LocalDate;
import java.util.*;

import product.ProductController;

public class ShoppingCart {
    /**
     * Shopping cart attributes
     */
    // Use Set interface
    private Map<String, Integer> items;
    private static final double BASE = 0.1;
    private static int nextId = 1;
    private LocalDate purchaseDate;
    private String cartId;

    private Map<String, String> giftProductList;

    private ProductController productController;
    private String coupon = "";

    /**
     * Constructor
     */
    public ShoppingCart(ProductController productController) {
        this.cartId = "C" + nextId++;
        this.productController = productController;
        this.items = new HashMap<>();
    }

    public ShoppingCart(String cartId, Map<String, Integer> items, String coupon, Map<String, String> giftProductList, ProductController productController) {
        this.cartId = cartId;
        this.items = items;
        this.coupon = coupon;
        this.giftProductList = giftProductList;
        this.productController = productController;
        this.purchaseDate = LocalDate.now();
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
     * Calculate and @return total price amount of all products in the cart
     * Note: if the cart contains physical products, the shipping fee will be
     * calculated and added to the total price of the cart
     * Shipping fee = (totalWeight of all physical products) * (base)
     * Base fee = 0.1
     */
    public double cartAmount() {
        double totalPrice = 0;
        double amount = 0;
//        Calculate total amount with tax
        for (Map.Entry<String, Integer> set : this.items.entrySet()) {
            totalPrice += productController.productList().get(set.getKey()).getPrice() * set.getValue();
        }
//        Calculate the total coupon discount
        if (!Objects.equals(getCoupon(), "")) {
            getCouponDiscount();
        }
        amount += totalPrice + getTotalTax() + getShippingFee() - getCouponDiscount();
        return amount;
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
                cartId, cartAmount(), getTotalTax(), getShippingFee(), getCouponDiscount());
    }


    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    /**
     * Getter and Setter methods
     */


    public double getTotalTax() {
        double tax = 0;
        for (Map.Entry<String, Integer> set : this.items.entrySet()) {
            tax += productController.productList().get(set.getKey()).getPrice() * productController.productList().get(set.getKey()).getTaxType().getPercentage()
                    * set.getValue();
        }
        return tax;
    }

    public double getShippingFee() {
        return BASE * getTotalWeight();
    }

    public Map<String, String> getGiftProductList() {
        return giftProductList;
    }

    public void setGiftProductList(Map<String, String> giftProductList) {
        this.giftProductList = giftProductList;
    }

    /**
     * Calculate total weight of the cart
     *
     * @return the total weight
     */
    public double getTotalWeight() {
        double weight = 0;
        for (String product : this.items.keySet()) {
            if (productController.productList().get(product) instanceof PhysicalProduct) {
                weight += ((PhysicalProduct) productController.productList().get(product)).getWeight() * this.items.get(product);
            }
        }
        return weight;
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

    /**
     * Calculate the coupon discount for a cart
     *
     * @return the coupon discount
     */
    public Double getCouponDiscount() {
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
        return total;
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
        System.out.println("Shipping fee: " + getShippingFee());
        System.out.println("Total amount: " + this.cartAmount());
    }

    public String receiptToFile() {
        return String.format("%s,%s,%.2f\n", this.cartId, this.purchaseDate, this.cartAmount());
    }

    /**
     * Format the product to print the receipt and display to the console log
     *
     * @return String
     */
    public String receiptToFileCustomName() {
        StringBuilder products = new StringBuilder();
        for (Map.Entry<String, Integer> items : this.getItems().entrySet()) {
            Product product = productController.productList().get(items.getKey());
            products.append("Name: ").append(items.getKey()).append("\t").append("Price: ").append(product.getPrice()).append("\t")
                    .append("Tax: ").append(product.getTaxType()).append(" Quantity: ").append(items.getValue()).append("\n");
        }
        return String.format("----------------RECEIPT----------------\nCart: %s\nDate of purchase: %s\nItems: " + products +
                "Shipping fee: %.2f\nTotal amount: %.2f", this.cartId, this.purchaseDate, getShippingFee(), this.cartAmount());
    }

}
