/**
 * @author <Vo Thanh Thong - s3878071>
 */

package SystemUI;

import Product.ProductManager;
import ShoppingCart.ShoppingCart;
import ShoppingCart.ShoppingCartManager;

import java.util.Objects;
import java.util.Scanner;

public class ShoppingCartUI {
    // todo add Regex
    static Scanner scanner = new Scanner(System.in);

    /**
     * Cart Edit Menu
     */
    private int cartEditMenu() {
        String userInput = "";

        while (!userInput.matches(Regex.NUM_1_TO_6)) {
            System.out.println("#===============================#");
//            todo get id of current cart to edit
            System.out.printf("#===== EDITING CART ID: %d ======#\n");
            System.out.println("#===============================#");
            System.out.println("1. Add product to current cart");
            System.out.println("2. Remove an exist product from current cart");
            System.out.println("3. Apply coupon to current cart");
            System.out.println("4. Remove coupon from current cart");
            System.out.println("5. Display all products in current cart");
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
    private void cartEditUI(int cartID) {
        int userInput = 0;
        String productName;

        // Get cart from cart list to modify
        ShoppingCart cart = ShoppingCartManager.findCartByID(cartID);

        while (userInput != 6) {

            userInput = cartEditMenu();

            switch (userInput) {
                case 1 -> {
                    System.out.println("All products in our shop:");
                    ProductManager.displayAllProduct();
                    System.out.println("Added a product to current shopping cart");

                    // Get name of product from user
                    System.out.println("Enter name of product:");
                    productName = scanner.nextLine();

                    // Get quantity of product user want to add to cart
                    System.out.println("Enter quantity: ");
                    int quantity = Integer.parseInt(scanner.nextLine());

                    // Check product exist in product list and can add to the cart
                    if (ProductManager.PRODUCTS.containsKey(productName) && Objects.requireNonNull(cart).addItem(productName, quantity)) {
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
                    CartManagerUI();
                }
            }

        }
    }


    /**
     * Shopping cart manager menu
     * <p>
     * This method will show all the option for user can choose by
     * enter a number. Then it will return user input.
     * </p>*/
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
            System.out.println("5. Print purchase of a cart");
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

    public void CartManagerUI() {
        int userInput = 0;

        while (userInput != 6) {
            userInput = menu();
            switch (userInput) {
                case 1 -> {
                    ShoppingCart cart = new ShoppingCart();
                    ShoppingCartManager.SHOPPING_CARTS.add(cart);
                    cartEditUI(ShoppingCartManager.SHOPPING_CARTS.size() - 1);
                }
                case 2 -> {
                    ShoppingCartManager.displayAllCarts();
                    System.out.println("Enter an id of cart which you want to remove: ");
                    ShoppingCartManager.SHOPPING_CARTS.remove(Integer.parseInt(scanner.nextLine()));
                }
                case 3 -> {
                    ShoppingCartManager.displayAllCarts();
                    System.out.println("Enter an id of cart which you want to edit: ");
                    cartEditUI(Integer.parseInt(scanner.nextLine()));
                }
                case 4 -> {
                    viewCartDetails();
                }
                case 5 -> {
                    ShoppingCartManager.displayAllCarts();
//                    todo print purchase
                }
                case 6 -> {
                    UserUI.userUI();
                }
            }
        }
    }

    /**
     * Display all cart and allow user choose cart to view details
     */
    public void viewCartDetails() {
        ShoppingCartManager.displayAllCarts();
        System.out.println("Enter the id of cart to view detail: ");
        int id = Integer.parseInt(scanner.nextLine());
        Objects.requireNonNull(ShoppingCartManager.findCartByID(id)).viewDetails();
    }



//  TODO   (OLD VERSION NOT USE ANYMORE, WILL BE DELETED)
    /**
     * Create new shopping cart (OLD VERSION NOT USE ANYMORE, WILL BE DELETED)
     * <p>
     * This will create new shopping cart and let user add products to cart.
     * After complete all this method will add new cart to cart list of the system.
     * </p>*/
    public void createNewShoppingCart() {
        ShoppingCart cart = new ShoppingCart();
        // Set name for cart
        cart.setName("Cart " + ShoppingCartManager.sortCartList().size());

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
                    for (String product : cart.getItems().keySet()) {
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
