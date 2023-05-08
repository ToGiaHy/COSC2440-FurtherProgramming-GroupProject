/**
 * @author Group 11
 */
package systemui;

import io.CartRelatedActions;
import product.ProductController;
import shoppingcart.ShoppingCart;
import shoppingcart.ShoppingCartController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class ShoppingCartUI {
    static Scanner scanner = new Scanner(System.in);
    private final ShoppingCartController cartController;
    private final CartRelatedActions cartRelatedActions;
    ProductController productController;

    // Constructor
    public ShoppingCartUI(ShoppingCartController cartController, CartRelatedActions cartRelatedActions, ProductController productController) {
        this.cartController = cartController;
        this.cartRelatedActions = cartRelatedActions;
        this.productController = productController;
    }

    /**
     * Cart Edit Menu
     */
    private int cartEditMenu(String cartID) {
        String userInput = "";
        while (!userInput.matches(Regex.NUM_1_TO_8)) {
            System.out.printf("#===== EDITING CART ID: %s ======#\n", cartID);
            System.out.println("#===============================#");
            System.out.println("1. Add an item to the current cart");
            System.out.println("2. Remove an existing item from the current cart");
            System.out.println("3. Apply coupon to the current cart");
            System.out.println("4. Remove coupon from the current cart");
            System.out.println("5. Display all products in the current cart");
            System.out.println("6. Complete cart and return shopping cart manager");
            System.out.println("7. Write messages for gift products");
            System.out.println("8. Print out the messages of the gift products");
            System.out.println("Please enter a number in the menu to interact with system:");
            userInput = scanner.nextLine();
            if (!userInput.matches(Regex.NUM_1_TO_8)) {
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
        ShoppingCart cart = cartController.findCartByID(cartID);

        while (userInput != 8) {
            userInput = cartEditMenu(cartID);
            switch (userInput) {
                case 1 -> {
                    System.out.println("All products in our shop:");
                    productController.viewProduct();
                    System.out.println("Added a product to current shopping cart");

                    // Get name of product from user
                    System.out.println("Enter name of product:");
                    productName = scanner.nextLine();

                    // Get quantity of product user want to add to cart
                    System.out.println("Enter quantity: ");
                    int quantity = Integer.parseInt(scanner.nextLine());

                    // Check product exist in product list and can add to the cart
                    if (this.productController.productList().containsKey(productName) && Objects.requireNonNull(cart).addItem(productName, quantity)) {
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
                    if (productController.productList().containsKey(productName) && cart.removeItem(productName, quantity)) {
                        System.out.println("#--- Removed product ---#");
                    } else {
                        System.out.println(this.productController.productList().containsKey(productName));
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
                case 7 -> {
                    if (!cart.getGiftProductList().isEmpty()) {
                        System.out.println(cart.getGiftProductList());
                        System.out.println("Please specify the gift product you want to edit: ");
                        String giftProductInput = scanner.nextLine();
                        if (cart.getGiftProductList().containsKey(giftProductInput)) {
                            System.out.println("Please write a message: ");
                            String message = scanner.nextLine();
                            cart.getGiftProductList().put(giftProductInput, message);
                        } else {
                            System.out.println("Gift product does not exist or wrong input");
                        }
                    } else {
                        System.out.println("There is no gift product available in the cart");
                    }
                }
                case 8 -> {
                    if (!cart.getGiftProductList().isEmpty()) {
                        System.out.println(cart.getGiftProductList());
                    } else {
                        System.out.println("There is no gift product available in the cart");
                    }
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

        while (!userInput.matches(Regex.NUM_1_TO_6)) {
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

            if (!userInput.matches(Regex.NUM_1_TO_6)) {
                System.out.println();
                System.out.println("Error: You entered a string or a number out of range from 1 to 6!");
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
                    ShoppingCart cart = new ShoppingCart(productController);
                    Map<String, String> giftTemp = new HashMap<>();
                    System.out.println("Pick a cart ID number: ");
                    int cartNumber = Integer.parseInt(scanner.nextLine());
                    String cartID = "C" + cartNumber;
                    if (cartController.findCartByID(cartID) != null) {
                        System.out.println("Cart ID already exists");
                    } else {
                        cart.setCartId(cartID);
                        cart.setGiftProductList(giftTemp);
                        cartController.shoppingCartList().add(cart);
                        cartController.viewCarts();
                        cartEditUI(cart.getId(), userUI);
                    }
                }
                case 2 -> {
                    cartController.viewCarts();
                    System.out.println("Enter an id of cart which you want to remove: ");
                    ShoppingCart cart = cartController.findCartByID(scanner.nextLine());
                    if (cartController.remove(cart)) {
                        System.out.println("Remove the cart successfully");
                    } else
                        System.out.println("Cart does not exist");
                }
                case 3 -> {
                    cartController.viewCarts();
                    System.out.println("Enter an id of cart which you want to edit: ");
                    String cartID = scanner.nextLine();

                    while (cartRelatedActions.exists(cartID) || cartController.findCartByID(cartID) == null) {
                        System.out.println("Error: Cart id does not exist or this cart's receipt has been printed out!");
                        System.out.println("Enter an id of cart which you want to edit: ");
                        cartID = scanner.nextLine();
                    }
                    cartEditUI(cartID, userUI);
                }
                case 4 -> viewCartDetails();


                case 5 -> {
                    cartController.viewCarts();
                    System.out.println("Enter a cart id to print the receipt: ");
                    String cartID = scanner.nextLine();
                    System.out.println("Enter the name to save file receipt: ");
                    String fileName = scanner.nextLine();

                    while (cartRelatedActions.exists(cartID) || cartController.findCartByID(cartID) == null) {
                        System.out.println("Error: Cart id does not exist or this cart's receipt has been printed out!");
                        System.out.println("Enter an id of cart which you want to print receipt: ");
                        cartID = scanner.nextLine();
                    }

                    ShoppingCart cart = cartController.findCartByID(cartID);
                    cart.displayReceipt();

                    cartRelatedActions.writeReceipt(cartID);
                    cartRelatedActions.writeReceiptWithCustomName(cartID, fileName);
                }
                case 6 -> System.out.println();
            }
        }
    }

    /**
     * Display all cart and allow user choose cart to view details
     */
    public void viewCartDetails() {
        cartController.viewCarts();
        System.out.println("Enter the id of cart to view detail: ");
        String id = scanner.nextLine();
        Objects.requireNonNull(cartController.findCartByID(id)).viewDetails();
    }


}
