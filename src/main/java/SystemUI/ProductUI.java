/**
 * @author <Vo Thanh Thong - s3878071>
 */

package SystemUI;
import Product.*;
import java.util.Scanner;


public class ProductUI {
    /**
     * ProductUI attributes
     */
    static Scanner scanner = new Scanner(System.in);

    /**
     * Menu
     * Display all option for user can interact with the system
     */
    private int menu() {
        String userInput = "";

        while (!userInput.matches(Regex.NUM_1_TO_4)) {
            System.out.println("#=======================================#");
            System.out.println("#===== PRODUCT MANAGER =====#");
            System.out.println("#=======================================#");
            System.out.println("1. View all products");
            System.out.println("2. Add a new product");
            System.out.println("3. Edit an exist product");
            System.out.println("4. Back to main menu");
            System.out.println("Please choose interaction by enter a from 1 to 4:");

            userInput = scanner.nextLine();

            if (!userInput.matches(Regex.NUM_1_TO_4)) {
                System.out.println();
                System.out.println("Error: You entered a string or a number out of range from 1 to 4!");
                System.out.println();
            }
        }

        return Integer.parseInt(userInput);
    }

    /**
     * productUI
     * Process the requirement of user input from the menu
     */
    public void productUI() {

        int userInput = 0;

        while (userInput != 4) {

            userInput = menu();

            switch (userInput) {
                case 1 -> {
                    System.out.println("Show all products!");
                    System.out.println();
                }
                case 2 -> {
                    System.out.println("Create a new product!");
                    System.out.println();
                }
                case 3 -> {
                    System.out.println("Edit an exist product!");
                    System.out.println();
                }
                case 4 -> {
                    System.out.println("Back to main menu");
//                    todo Call UserUI right here to back to menu
                }
            }

        }
    }

    /**
     * Create new product
     * <p>
     * Get information from the user and create new product
     * then add to product list of the system
     * </p>
     */
    public static void createNewProduct() {

        System.out.println("#===== CREATE NEW PRODUCT =====#");

        System.out.println("Which type of product you want to create ? (type 1 : Digital or 2 : Physical) ");

        String typeInput = scanner.nextLine();
        while (!typeInput.matches(Regex.NUM_1_OR_2)) {
            System.out.println("Please choose type by enter 1 or 2: ");
            typeInput = scanner.nextLine();
        }
        int type = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter a name of product:");
        String name = scanner.nextLine();
        System.out.println("Enter a description of product:");
        String description = scanner.nextLine();

        System.out.println("Enter the number of product:");
        String quantityInput = scanner.nextLine();
        while (!quantityInput.matches(Regex.INTEGER_NUMBER)) {
            System.out.println("Please choose type by enter a integer number: ");
            quantityInput = scanner.nextLine();
        }
        int quantityAvailable = Integer.parseInt(quantityInput);


        System.out.println("Enter the price for product:");
        String priceInput = scanner.nextLine();
        while (!priceInput.matches(Regex.DOUBLE_NUMBER)) {
            System.out.println("Please choose type by enter a double number: ");
            priceInput = scanner.nextLine();
        }
        double price = Double.parseDouble(priceInput);

//      todo Chưa xử lí nếu weight < 0
        double weight = 0.0;
        if (type == 2) {
            System.out.println("Enter weight of product:");
            String weightInput = scanner.nextLine();
            while (!weightInput.matches(Regex.DOUBLE_NUMBER)) {
                System.out.println("Please choose type by enter a double number: ");
                weightInput = scanner.nextLine();
            }
            weight = Double.parseDouble(scanner.nextLine());
            while (weight <= 0) {
                System.out.println("Weight cannot be 0 or negative number");
                weight = Double.parseDouble(scanner.nextLine());
            }
        }

        System.out.println("Can this product be used as a gift ? Type 'true' or 'false'");
        boolean isGift;
        isGift = Boolean.parseBoolean(scanner.nextLine());

    // todo ask the user the TaxType
        System.out.println("What type of tax ?");
        String taxInput = scanner.nextLine();
        while (!taxInput.matches(Regex.TAX_TYPE)) {
            System.out.println("Please choose type tax type (FREE, NORMAL or LUXURY): ");
            taxInput = scanner.nextLine();
        }
        String tax = taxInput;


//        if (type == 1) {
//            if (isGift) {
//                ProductManager.addProduct(new DigitalProductCanBeGifted(name, description, quantityAvailable, price, TaxType[tax]));
//            }
//            else {
//                ProductManager.addProduct(new DigitalProduct(name, description, quantityAvailable, price));
//            }
//        }
//        else if (type == 2){
//            if (isGift) {
//                ProductManager.addProduct(new PhysicalProductCanBeGifted(name, description, quantityAvailable, price, weight));
//            }
//            else {
//                ProductManager.addProduct(new PhysicalProduct(name, description, quantityAvailable, price, weight));
//            }
//        }

        System.out.println("#======================================#");
        System.out.println("# New Product is created successfully! #");
        System.out.println("#======================================#");
    }

    /**
     * Edit Menu
     *
     */
    public int editMenu() {
        String regex = "[0-4]";
        String userInput = "";

        while (!userInput.matches(regex)) {
            System.out.println("#========================#");
            System.out.println("#===== EDIT PRODUCT =====#");
            System.out.println("#========================#");
            System.out.println("1. Change description current of product");
            System.out.println("2. Change available quantity of product");
            System.out.println("3. Change price of current product");
            System.out.println("4. Change weight of current product");
            System.out.println("5. Back to product manager");
            System.out.println("Please choose interaction by enter a from 1 to 4:");

            userInput = scanner.nextLine();

            if (!userInput.matches(regex)) {
                System.out.println();
                System.out.println("Error: You entered a string or a number out of range from 1 to 4!");
                System.out.println();
            }
        }

        return Integer.parseInt(userInput);
    }

    /**
     * Edit a product
     * <p>
     * Help user can edit the information of a product
     * </p>
     */
    public void editProduct(String productName) {

        Product product = ProductManager.PRODUCTS.get(productName);

        int userInput = 0;

        while (userInput != 5) {
            userInput = editMenu();

            switch (userInput) {
                case 1 -> {
//              todo Change description of the Product
                    System.out.println("Enter product description:");
//                String description = scanner.nextLine();
//                product.setDescription(description);
                }
                case 2 -> {
//              todo Change the quantity of the Product
                    System.out.println("Enter the number of products:");
//                int quantityAvailable = Integer.parseInt(scanner.nextLine());
//                product.setQuantityAvailable(quantityAvailable);
                }
                case 3 -> {
//              todo Change the price of the Product
                    System.out.println("Enter product price:");
//                double price = Double.parseDouble(scanner.nextLine());
//                product.setPrice(price);
                }
                case 4 -> {
//              todo Change weight of Physical Product
                    if (product instanceof DigitalProduct) {
                        System.out.println("This is a digital product, so it does not have weight!");
                    } else if (product instanceof PhysicalProduct currentProduct) {
                        System.out.println("Enter product weight:");
                        double weight = Double.parseDouble(scanner.nextLine());
                        currentProduct.setWeight(weight);
                    }
                }
                default -> {
                    System.out.println();
                    System.out.println("Update product detail successfully!");
                    System.out.println();
//                todo Call edit product again
                }
            }
        }


    }

}
