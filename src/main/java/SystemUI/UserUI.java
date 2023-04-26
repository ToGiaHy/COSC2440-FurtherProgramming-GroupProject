/**
 * @author <Vo Thanh Thong - s3878071>
 */

package SystemUI;

import java.util.*;

import ShoppingCart.ShoppingCartManager;

public class UserUI {
    /**
     * UserUI attributes
     */
    static Scanner scanner = new Scanner(System.in);
    static ProductUI productUI = new ProductUI();
    static ShoppingCartUI cartUI = new ShoppingCartUI();


    /**
     * Menu
     * Display all option for user can interact with the system
     */
    private static int menu() {

        String userInput = "";

        while (!userInput.matches(Regex.NUM_1_TO_6)) {
            System.out.println("#=======================================#");
            System.out.println("#===== WELCOME TO SHOPPING SERVICE =====#");
            System.out.println("#=======================================#");
            System.out.println("1. Product manager");
            System.out.println("2. Shopping cart manager");
            System.out.println("3. Select a cart to view details");
            System.out.println("4. Sorting carts");
            System.out.println("5. Print purchase receipts to the screen or a text file");
            System.out.println("6. Exit program");
            System.out.println("Please choose interaction by enter a from 1 to 6:");
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
     * userUI
     * Process the requirement of user input from the menu
     */
    public static void userUI() {
        int userInput = 0;

        while (userInput != 6) {

            userInput = menu();

            switch (userInput) {
                case 1 -> {
                    productUI.productUI();
                    System.out.println();
                }
                case 2 -> {
                    cartUI.CartUI();
                    System.out.println();
                }
                case 3 -> {
//                    todo display all cart UI and allow user select to view detail
                    System.out.println("View cart detail");
                    System.out.println();
                }
                case 4 -> {
//                    todo sort all cart in cart list
                    System.out.println("Sorting Cart");
                    ShoppingCartManager.displayAllCarts();
                }
                case 5 -> {
//                   todo display all cart and allow user select cart to print receipt
                    ShoppingCartManager.displayAllCarts();
                    System.out.println("Please enter the cart to print receipt");
                }
                case 6 -> {
                    System.out.println("Thank you for using our services!");
                }
            }

        }
    }

}