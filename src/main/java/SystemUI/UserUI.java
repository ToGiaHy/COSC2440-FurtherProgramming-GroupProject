/**
 * @author <Vo Thanh Thong - s3878071>
 */

package SystemUI;

import java.util.*;

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

        while (!userInput.matches(Regex.NUM_1_TO_3)) {
            System.out.println("#=======================================#");
            System.out.println("#===== WELCOME TO SHOPPING SERVICE =====#");
            System.out.println("#=======================================#");
            System.out.println("1. Product manager");
            System.out.println("2. Shopping cart manager");
            System.out.println("3. Exit program");
            System.out.println("Please choose interaction by enter a from 1 to 3:");
            userInput = scanner.nextLine();
            if (!userInput.matches(Regex.NUM_1_TO_3)) {
                System.out.println();
                System.out.println("Error: You entered a string or a number out of range from 1 to 3!");
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

        while (userInput != 3) {

            userInput = menu();

            switch (userInput) {
                case 1 -> {
                    productUI.productUI();
                    System.out.println();
                }
                case 2 -> {
                    cartUI.CartManagerUI();
                    System.out.println();
                }
                case 3 -> {
                    System.out.println("#===============================#");
                    System.out.println("Thank you for using our services!");
                    System.out.println("#===============================#");
                }
            }

        }
    }

}