/**
 * @author <Vo Thanh Thong - s3878071>
 */

package SystemUI;

import Product.ProductManager;
import ShoppingCart.ShoppingCart;
import ShoppingCart.ShoppingCartManager;
import io.CartRelatedActions;


import java.util.Objects;
import java.util.Scanner;

public class ShoppingCartUI {
    // todo add Regex
    static Scanner scanner = new Scanner(System.in);
    private ShoppingCartManager cartManager;
    private CartRelatedActions cartRelatedActions;
    ProductManager productManager;

    // Constructor
    public ShoppingCartUI(ShoppingCartManager cartManager, CartRelatedActions cartRelatedActions, ProductManager productManager) {
        this.cartManager = cartManager;
        this.cartRelatedActions = cartRelatedActions;
        this.productManager = productManager;
    }

    /**
     * Cart Edit Menu
     */
    private int cartEditMenu() {
        String userInput = "";
        while (!userInput.matches(Regex.NUM_1_TO_6)) {
            System.out.println("#===============================#");
            System.out.println("Enter a cart Id (Example: C0");
            String cartID = scanner.nextLine();
            ShoppingCart cart = cartManager.findCartByID(cartID);
            // todo Coi lai phan validation nay dum em
            if (cart == null || cartRelatedActions.exists(cartID)) {
                System.out.println("Cart id does not exist or this cart's receipt has been printed out!");
            }
            System.out.printf("#===== EDITING CART ID: %d ======#\n");
            System.out.println("#===============================#");
            System.out.println("1. Add an item to the current cart");
            System.out.println("2. Remove an existing item from the current cart");
            System.out.println("3. Apply coupon to the current cart");
            System.out.println("4. Remove coupon from the current cart");
            System.out.println("5. Display all products in the current cart");
            System.out.println("6. Complete cart and return shopping cart manager");
            System.out.println("Please enter a number in the menu to interact with system:");
            userInput = scanner.nextLine();
            if (!userInput.matches(Regex.NUM_1_TO_6)) {
                System.out.println();
                System.out.println("Error: You entered a string or a number out of range from 1 to 6!");
                System.out.println();
            }
        }
        return Integer.parseInt(userInput);
    }

    /**
     * Cart Edit UI
     */
    private void cartEditUI(String cartID, UserUI userUI) {
        int userInput = 0;
        String productName;
        // Get cart from cart list to modify
        ShoppingCart cart = cartManager.findCartByID(cartID);
        while (userInput != 6) {
            userInput = cartEditMenu();
            switch (userInput) {
                case 1 -> {
                    System.out.println("All products in our shop:");
                    productManager.displayAllProduct();
                    System.out.println("Added a product to current shopping cart");

                    // Get name of product from user
                    System.out.println("Enter name of product:");
                    productName = scanner.nextLine();

                    // Get quantity of product user want to add to cart
                    System.out.println("Enter quantity: ");
                    int quantity = Integer.parseInt(scanner.nextLine());

                    // Check product exist in product list and can add to the cart
                    if (this.productManager.getPRODUCTS().containsKey(productName) && Objects.requireNonNull(cart).addItem(productName, quantity)) {
                        System.out.println("#--- Added product ---#");
                    } else {
                        System.out.println("#--- This product is not exist, run out of or already in cart ---#");
                    }

                }
                case 2 -> {
                    System.out.println("Removed product from current shopping cart");
                    // Display all product in shopping cart for user read to remove
                    System.out.println("All current products in cart:");
                    assert cart != null;
                    cart.displayAllProducts();

                    // Get name of product from user
                    System.out.println("Enter name of product:");
                    productName = scanner.nextLine();

                    // Get quantity of product user want to add to cart
                    System.out.println("Enter quantity: ");
                    int quantity = Integer.parseInt(scanner.nextLine());

                    // Check product exist in product list and can remove from the cart
                    if (productManager.getPRODUCTS().containsKey(productName) && cart.removeItem(productName, quantity)) {
                        System.out.println("#--- Removed product ---#");
                    } else {
                        System.out.println(this.productManager.getPRODUCTS().containsKey(productName));
                        System.out.println(cart.removeItem(productName, quantity));
                        System.out.println("#--- This product is not exist or not in current cart ---#");
                    }
                }
                case 3 -> {
                    System.out.println("Applied coupon to current shopping cart");
                    System.out.println("Please enter coupon: ");
                    String coupon = scanner.nextLine();
                    assert cart != null;
                    cart.setCoupon(coupon);
                    cart.cartAmount();
                }
                case 4 -> {
                    System.out.println("Removed coupon from current shopping cart");
                    assert cart != null;
                    cart.setCoupon("");
                    cart.cartAmount();
                }
                case 5 -> {
                    System.out.println("Displayed all products in cart");
                    assert cart != null;
                    cart.displayAllProducts();
                }
                case 6 -> {
                    System.out.println("Completed cart");
                    assert cart != null;
                    System.out.println("Total amount of this cart: " + cart.cartAmount());
                    CartManagerUI(userUI);
                }
            }

        }
    }


    /**
     * Shopping cart manager menu
     * <p>
     * This method will show all the option for user can choose by
     * enter a number. Then it will return user input.
     * </p>
     */
    private int menu() {
        String userInput = "";

        while (!userInput.matches(Regex.NUM_1_TO_5)) {
            System.out.println("#=================================#");
            System.out.println("#===== SHOPPING CART MANAGER =====#");
            System.out.println("#=================================#");
            System.out.println("1. Create a new shopping cart");
            System.out.println("2. Remove an exist shopping cart");
            System.out.println("3. Edit an exist shopping cart");
            System.out.println("4. Choose cart and view details");
            System.out.println("5. Print receipts of a cart");
            System.out.println("6. Return to main menu");
            System.out.println("Please enter a number in the menu to interact with system:");

            userInput = scanner.nextLine();

            if (!userInput.matches(Regex.NUM_1_TO_5)) {
                System.out.println();
                System.out.println("Error: You entered a string or a number out of range from 1 to 4!");
                System.out.println();
            }
        }

        return Integer.parseInt(userInput);
    }

    /**
     *
     */

    public void CartManagerUI(UserUI userUI) {
        int userInput = 0;

        while (userInput != 6) {
            userInput = menu();
            switch (userInput) {
                case 1 -> {
                    ShoppingCart cart = new ShoppingCart(productManager);
                    cartManager.getShoppingCarts().add(cart);
                    String cartId = "C" + (cartManager.getShoppingCarts().size() - 1);
                    cartEditUI(cartId, userUI);
                }
                case 2 -> {
                    cartManager.displayAllCarts();
                    System.out.println("Enter an id of cart which you want to remove: ");
                    ShoppingCart cart = cartManager.findCartByID(scanner.nextLine());
                    if (cartManager.remove(cart)) {
                        System.out.println("Remove the cart successfully");
                    } else
                        System.out.println("Cart does not exist");
                }
                case 3 -> {
                    cartManager.displayAllCarts();
                    System.out.println("Enter an id of cart which you want to edit: ");
                    String cartId = scanner.nextLine();
                    cartEditUI(cartId, userUI);
                }
                case 4 -> viewCartDetails();

                case 5 -> {
                    cartManager.displayAllCarts();
                    System.out.println("Enter a cart id to print the receipt: ");
                    String cartId = scanner.nextLine();
                    ShoppingCart cart = cartManager.findCartByID(cartId);
                    cart.displayReceipt();
                    cartRelatedActions.writeReceipt(cartId);
                }
                case 6 -> userUI.userUI();

            }
        }
    }

    /**
     * Display all cart and allow user choose cart to view details
     */
    public void viewCartDetails() {
        cartManager.displayAllCarts();
        System.out.println("Enter the id of cart to view detail: ");
        String id = scanner.nextLine();
        Objects.requireNonNull(cartManager.findCartByID(id)).viewDetails();
    }


}
