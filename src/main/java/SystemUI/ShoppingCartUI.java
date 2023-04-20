/**
 * @author <Vo Thanh Thong - s3878071>
 */

package SystemUI;

import Product.ProductManager;
import ShoppingCart.ShoppingCart;
import ShoppingCart.ShoppingCartManager;

import java.util.Scanner;

public class ShoppingCartUI {
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
        System.out.println("3. Display all products in cart");
        System.out.println("4. Complete cart");
        System.out.println("Please enter a number in the menu to interact with system:");
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     *
     */
    public void CartUI() {

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

            switch (userInput) {
                case 1:
//                    UserUI.browseMenu(ProductManager.PRODUCTS);
                    System.out.println("Enter name of product:");
                    productName = scanner.nextLine();

                    // Check product exist in product list and can add to the cart
                    if (ProductManager.PRODUCTS.containsKey(productName) && cart.addItem(productName)) {
                        System.out.println("#--- Added product ---#");
                    } else {
                        System.out.println("#--- This product is not exist, run out of or already in cart ---#");
                    }
                    userInput = menu();
                    break;
                case 2:
                    System.out.println("Enter name of product:");
                    productName = scanner.nextLine();

                    // Check product exist in product list and can remove from the cart
                    if (ProductManager.PRODUCTS.containsKey(productName) && cart.removeItem(productName)) {
                            System.out.println("#--- Removed product ---#");
                    } else {
                        System.out.println("#--- This product is not exist or not in current cart ---#");
                    }
                    userInput = menu();
                    break;
                case 3:
                    for (String product: cart.getPRODUCTS()) {
                        System.out.println(ProductManager.PRODUCTS.get(product));
                    }
                    userInput = menu();
                    break;
                case 4:
                    ShoppingCartManager.getShoppingCarts().add(cart);
                    System.out.println(cart);
                    System.out.println("#=== Thank you for your order! ===#");
                    flag = false;
                    break;
                default:
                    System.out.println("#====================================#");
                    System.out.println("# Please enter a number in the menu! #");
                    System.out.println("#====================================#");
                    userInput = menu();
            }
        }

        UserUI.userUI();
    }

}
