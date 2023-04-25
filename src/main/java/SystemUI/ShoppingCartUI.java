/**
 * @author <Vo Thanh Thong - s3878071>
 */

package SystemUI;

import Product.ProductManager;
import ShoppingCart.ShoppingCart;
import ShoppingCart.ShoppingCartManager;

import java.util.Scanner;

public class ShoppingCartUI {
    // todo add Regex
    static Scanner scanner = new Scanner(System.in);

    /**
     * Shopping cart menu
     * <p>
     * This method will show all the option for user can choose by
     * enter a number. Then it will return user input.
     * </p>*/
    private static int menu() {
        System.out.println("#==============================#");
        System.out.println("#===== SHOPPING CART MENU =====#");
        System.out.println("#==============================#");
        System.out.println("1. Add product to current shopping cart");
        System.out.println("2. Remove product from current shopping cart");
        System.out.println("3. Apply coupon to current shopping cart");
        System.out.println("4. Remove coupon from current shopping cart");
        System.out.println("5. Display all products in cart");
        System.out.println("6. Complete cart");
        System.out.println("Please enter a number in the menu to interact with system:");
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     *
     */

    public void CartUI() {
        int userInput = 0;
        ShoppingCart cart = new ShoppingCart();
        // Set name for cart
        cart.setName("Cart " + ShoppingCartManager.getShoppingCarts().size());

        String productName;
        boolean flag = true;

        while (userInput != 6) {

            userInput = menu();

            switch (userInput) {
                case 1 -> {
                    System.out.println("All products in our shop:");
                    ProductManager.displayAllProduct();
                    System.out.println("Added a product to current shopping cart");
//                    Get name of product from user
                    System.out.println("Enter name of product:");
                    productName = scanner.nextLine();

//                    Get quantity of product user want to add to cart
                    System.out.println("Enter quantity: ");
                    int quantity = Integer.parseInt(scanner.nextLine());

                    // Check product exist in product list and can add to the cart
                    if (ProductManager.PRODUCTS.containsKey(productName) && cart.addItem(productName, quantity)) {
                        System.out.println("#--- Added product ---#");
                    } else {
                        System.out.println("#--- This product is not exist, run out of or already in cart ---#");
                    }

                }
                case 2 -> {
                    System.out.println("Removed product from current shopping cart");
//                    Display all product in shopping cart for user read to remove
                    System.out.println("All current products in cart:");
                    cart.displayAllProducts();

//                    Get name of product from user
                    System.out.println("Enter name of product:");
                    productName = scanner.nextLine();

//                    Get quantity of product user want to add to cart
                    System.out.println("Enter quantity: ");
                    int quantity = Integer.parseInt(scanner.nextLine());

                    // Check product exist in product list and can remove from the cart
                    if (ProductManager.PRODUCTS.containsKey(productName) && cart.removeItem(productName, quantity)) {
                        System.out.println("#--- Removed product ---#");
                    } else {
                        System.out.println(ProductManager.PRODUCTS.containsKey(productName));
                        System.out.println(cart.removeItem(productName, quantity));
                        System.out.println("#--- This product is not exist or not in current cart ---#");
                    }
                }
                case 3 -> {
                    System.out.println("Applied coupon to current shopping cart");
                    System.out.println("Please enter coupon: ");
                    String coupon = scanner.nextLine();
                    cart.setCoupon(coupon);
                    cart.cartAmount();
                }
                case 4 -> {
                    System.out.println("Removed coupon from current shopping cart");
                    cart.setCoupon("");
                    cart.cartAmount();
                }
                case 5 -> {
                    System.out.println("Displayed all products in cart");
                    cart.displayAllProducts();
                }
                case 6 -> {
                    System.out.println("Completed cart");
                    System.out.println(cart.cartAmount());
                    UserUI.userUI();
                }
            }

        }
    }

    /**
     * Create new shopping cart
     * <p>
     * This will create new shopping cart and let user add products to cart.
     * After complete all this method will add new cart to cart list of the system.
     * </p>*/
    public static void createNewShoppingCart() {
        ShoppingCart cart = new ShoppingCart();
        // Set name for cart
        cart.setName("Cart " + ShoppingCartManager.getShoppingCarts().size());

        int userInput;
        String productName;
        boolean flag = true;

        userInput = menu();

        while (flag) {

            userInput = menu();

            switch (userInput) {
                case 1 -> {
//                    Get name of product from user
                    System.out.println("Enter name of product:");
                    productName = scanner.nextLine();

//                    Get quantity of product user want to add to cart
                    System.out.println("Enter quantity: ");
                    int quantity = Integer.parseInt(scanner.nextLine());

                    // Check product exist in product list and can add to the cart
                    if (ProductManager.PRODUCTS.containsKey(productName) && cart.addItem(productName, quantity)) {
                        System.out.println("#--- Added product ---#");
                    } else {
                        System.out.println("#--- This product is not exist, run out of or already in cart ---#");
                    }
                }
                case 2 -> {
//                    Get name of product from user
                    System.out.println("Enter name of product:");
                    productName = scanner.nextLine();

//                    Get quantity of product user want to add to cart
                    System.out.println("Enter quantity: ");
                    int quantity = Integer.parseInt(scanner.nextLine());

                    // Check product exist in product list and can remove from the cart
                    if (ProductManager.PRODUCTS.containsKey(productName) && cart.removeItem(productName, quantity)) {
                        System.out.println("#--- Removed product ---#");
                    } else {
                        System.out.println("#--- This product is not exist or not in current cart ---#");
                    }
                }
                case 3 -> {
                    for (String product : cart.getPRODUCTS().keySet()) {
                        System.out.println(ProductManager.PRODUCTS.get(product));
                    }
                }
                case 4 -> {
//                    ShoppingCartManager.getShoppingCarts().add(cart);
                    System.out.println(cart);
                    System.out.println("#=== Thank you for your order! ===#");
                    UserUI.userUI();
                    flag = false;
                }
                default -> {
                    System.out.println("#====================================#");
                    System.out.println("# Please enter a number in the menu! #");
                    System.out.println("#====================================#");
                }
            }

        }

    }

}
