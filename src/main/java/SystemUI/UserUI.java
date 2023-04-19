/**
 * @author <Vo Thanh Thong - s3878071>
 */

package SystemUI;

import java.util.*;

import Product.*;
import ShoppingCart.ShoppingCart;
import ShoppingCart.ShoppingCartManager;

public class UserUI {
    /**
     * UserUI attributes
     */
    static Scanner scanner = new Scanner(System.in);

    /**
     * Menu
     * Display all option for user can interact with the system
     */
    private static int menu() {
        System.out.println("#===== WELCOME TO SHOPPING SERVICE =====#");
        System.out.println("1. Product manager");
        System.out.println("2. Shopping cart manager");
        System.out.println("3. Select a cart to view details");
        System.out.println("4. Sorting carts");
        System.out.println("5. Print purchase receipts to the screen or a text file");
        System.out.println("Please choose interaction by enter a number:");
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     * Start Menu
     * Process the requirement of user input from the menu
     */
    public static void startMenu() {
        int userInput = menu();
        while (userInput != 5) {
            switch (userInput) {
                case 1:
                    ProductUI.createNewProduct();
                    UserUI.browseMenu(ProductManager.PRODUCTS);
                    break;
                case 2:
                    browseMenu(ProductManager.PRODUCTS);
                    System.out.println("Choose a product by typing a name to edit:");
                    String productName = scanner.nextLine();
                    ProductUI.editProduct(productName);
                    browseMenu(ProductManager.PRODUCTS);
                    break;
                case 3:
                    ShoppingCartUI.createNewShoppingCart();
                    break;
                case 4:
                    for (ShoppingCart cart: ShoppingCartManager.getShoppingCarts()) {
                        System.out.println("#================ " + cart.getName() + " ====================#");
                        System.out.println(cart);
                    }
                    break;
                default:
                    System.out.println("#====================================#");
                    System.out.println("# Please enter a number in the menu! #");
                    System.out.println("#====================================#");
            }

            userInput = menu();;
        }
    }

    /**
     * Browse Menu
     * Display all product in the system
     */
    public static void browseMenu(Map<String, Product> products) {
        for (var entry : products.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}
