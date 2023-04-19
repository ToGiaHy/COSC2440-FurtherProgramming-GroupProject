/**
 * @author <Vo Thanh Thong - s3878071>
 */

package SystemUI;
import Product.*;
import java.util.Scanner;


public class ProductUI {
    static Scanner scanner = new Scanner(System.in);

    /**
     * Create new product
     * <p>
     * Get information from the user and create new product
     * then add to product list of the system
     * </p>
     */
    public static void createNewProduct() {
        double weight = 0;
        System.out.println("#===== CREATE NEW PRODUCT =====#");

        System.out.println("Which type of product you want to create ? (type 1 : Digital or 2 : Physical) ");
        int type = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter a name of product:");
        String name = scanner.nextLine();
        System.out.println("Enter a description of product:");
        String description = scanner.nextLine();
        System.out.println("Enter the number of product:");
        int quantityAvailable = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the price for product:");
        double price = Double.parseDouble(scanner.nextLine());

        if (type == 2) {
            System.out.println("Enter weight of product:");
            weight = Double.parseDouble(scanner.nextLine());
            while (weight <= 0) {
                System.out.println("Weight cannot be 0 or negative number");
                weight = Double.parseDouble(scanner.nextLine());
            }
        }

        System.out.println("Can this product be used as a gift ? Type 'true' or 'false'");
        boolean isGift;
        isGift = Boolean.parseBoolean(scanner.nextLine());

        if (type == 1) {
            if (isGift) {
                ProductManager.addProduct(new DigitalProductCanBeGifted(name, description, quantityAvailable, price));
            }
            else {
                ProductManager.addProduct(new DigitalProduct(name, description, quantityAvailable, price));
            }
        }
        else if (type == 2){
            if (isGift) {
                ProductManager.addProduct(new PhysicalProductCanBeGifted(name, description, quantityAvailable, price, weight));
            }
            else {
                ProductManager.addProduct(new PhysicalProduct(name, description, quantityAvailable, price, weight));
            }
        }

        System.out.println("#======================================#");
        System.out.println("# New Product is created successfully! #");
        System.out.println("#======================================#");
    }

    /**
     * Edit a product
     * <p>
     * Help user can edit the information of a product
     * </p>
     */
    public static void editProduct(String productName) {

        Product product = ProductManager.PRODUCTS.get(productName);

        System.out.println("#===== EDIT PRODUCT =====#");
        System.out.println("1. Change description current of product");
        System.out.println("2. Change available quantity of product");
        System.out.println("3. Change price of current product");
        System.out.println("4. Change weight of current product");

        System.out.println("Enter a number in the menu to interact with system:");
        int userInput = Integer.parseInt(scanner.nextLine());
        switch (userInput) {
            case 1:
                System.out.println("Enter product description:");
                String description = scanner.nextLine();
                product.setDescription(description);
                break;
            case 2:
                System.out.println("Enter the number of products:");
                int quantityAvailable = Integer.parseInt(scanner.nextLine());
                product.setQuantityAvailable(quantityAvailable);
                break;
            case 3:
                System.out.println("Enter product price:");
                double price = Double.parseDouble(scanner.nextLine());
                product.setPrice(price);
                break;
            case 4:
                if (product instanceof DigitalProduct) {
                    System.out.println("This is a digital product, so it does not have weight!");
                } else if (product instanceof PhysicalProduct currentProduct) {
                    System.out.println("Enter product weight:");
                    double weight = Double.parseDouble(scanner.nextLine());
                    currentProduct.setWeight(weight);
                }
                break;
            default:
                System.out.println("#====================================#");
                System.out.println("# Please enter a number in the menu! #");
                System.out.println("#====================================#");
                editProduct(productName);
        }

    }

}
