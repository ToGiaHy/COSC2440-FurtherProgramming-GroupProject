/**
 * @author <Vo Thanh Thong - s3878071>
 */

package SystemUI;

import Product.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class ProductUI {
    /**
     * ProductUI attributes
     */
    private ProductController productController;
    static Scanner scanner = new Scanner(System.in);

    public ProductUI(ProductController productController) {
        this.productController = productController;
    }

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
    public void productUI(CouponUI couponUI, UserUI userUI) {
        int userInput = 0;
        while (userInput != 4) {
            userInput = menu();
            switch (userInput) {
                case 1 -> {
                    System.out.println("Show all products!");
                    productController.viewProduct();
                    System.out.println();
                }
                case 2 -> {
                    System.out.println("Create a new product!");
                    createNewProduct(couponUI);
                    System.out.println();
                }
                case 3 -> {
                    System.out.println("Edit an exist product!");
                    String productName = scanner.nextLine();
                    editProduct(productName, couponUI);
                    System.out.println();
                }
                case 4 -> {
                    System.out.println("Back to main menu");
                    userUI.userUI();
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
    public void createNewProduct(CouponUI couponUI) {
        System.out.println("#===== CREATE NEW PRODUCT =====#");
        // Ask for the product type
        System.out.println("Which type of product you want to create ? (type 1 : Digital or 2 : Physical) ");
        String typeInput = scanner.nextLine();
        while (!typeInput.matches(Regex.NUM_1_OR_2)) {
            System.out.println("Please choose type by enter 1 or 2: ");
            typeInput = scanner.nextLine();
        }
        int type = Integer.parseInt(typeInput);
        // Ask for the name, description, price, tax, and quantity with input validations
        System.out.println("Enter a name of product:");
        String name = scanner.nextLine();
        System.out.println("Enter a description of product:");
        String description = scanner.nextLine();
        System.out.println("Enter the number of product:");
        String quantityInput = scanner.nextLine();
        while (!quantityInput.matches(Regex.INTEGER_NUMBER)) {
            System.out.println("Please enter an integer number: ");
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
        // Check for gift product
        System.out.println("Can this product be used as a gift ? Type 'true' or 'false'");
        String giftInput = scanner.nextLine();
        while (!giftInput.toLowerCase().matches(Regex.TRUE_OR_FALSE)) {
            System.out.println("Please type 'true' or 'false'");
            giftInput = scanner.nextLine();
        }
        boolean isGift;
        isGift = Boolean.parseBoolean(giftInput.toLowerCase());
        System.out.println("What type of tax ?");
        String taxInput = scanner.nextLine();
        while (!taxInput.matches(Regex.TAX_TYPE)) {
            System.out.println("Please choose type tax type (FREE, NORMAL or LUXURY): ");
            taxInput = scanner.nextLine();
        }
        String tax = taxInput;
        TaxType taxType = TaxType.getType(tax);
        // Ask for adding coupons for the product
        System.out.println("Do you want to add coupon for this product ? Type 'true' or 'false'");
        String hasCouponInput = scanner.nextLine();
        while (!hasCouponInput.toLowerCase().matches(Regex.TRUE_OR_FALSE)) {
            System.out.println("Please type 'true' or 'false'");
            hasCouponInput = scanner.nextLine();
        }
        boolean hasCoupon;
        hasCoupon = Boolean.parseBoolean(hasCouponInput.toLowerCase());
        Map<String, Coupon> couponList = new HashMap<>();
        if (hasCoupon) {
            System.out.println("How many coupon do you want to add ? Enter an integer number please: ");
            int numCoupon;
            int count = 1;
            numCoupon = Integer.parseInt(scanner.nextLine());
            while (count <= numCoupon) {
                count++;
                Coupon coupon = couponUI.createCoupon();
                if (coupon instanceof PercentCoupon percentCoupon) {
                    couponList.put(percentCoupon.getCouponCode(), percentCoupon);
                } else {
                    PriceCoupon priceCoupon = (PriceCoupon) coupon;
                    couponList.put(priceCoupon.getCouponCode(), priceCoupon);
                }

            }
        }
        // Create a new product according to the user input and add it to the database
        if (type == 1) {
            if (isGift) {
                productController.addProduct(new DigitalProductCanBeGifted(name, description, quantityAvailable, price, taxType, couponList, ""));
            } else {
                productController.addProduct(new DigitalProduct(name, description, quantityAvailable, price, taxType, couponList));
            }
        } else if (type == 2) {
            if (isGift) {
                productController.addProduct(new PhysicalProductCanBeGifted(name, description, quantityAvailable, price, taxType, weight, couponList, ""));
            } else {
                productController.addProduct(new PhysicalProduct(name, description, quantityAvailable, price, taxType, couponList, weight));
            }
        }
        System.out.println("#======================================#");
        System.out.println("# New Product is created successfully! #");
        System.out.println("#======================================#");
    }

    /**
     * Display the edit menu
     *
     * @return a number representing the user's choice
     */
    public int editMenu() {
        String regex = "[0-7]";
        String userInput = "";
        // Validate the input and display the menu
        while (!userInput.matches(regex)) {
            System.out.println("#========================#");
            System.out.println("#===== EDIT PRODUCT =====#");
            System.out.println("#========================#");
            System.out.println("1. Change description current of product");
            System.out.println("2. Change available quantity of product");
            System.out.println("3. Change price of current product");
            System.out.println("4. Change weight of current product");
            System.out.println("5. Change tax type of current product");
            System.out.println("6. Change the coupons that the current product have");
            System.out.println("7. Back to product manager");
            System.out.println("Please choose interaction by enter a from 1 to 7:");
            userInput = scanner.nextLine();
            if (!userInput.matches(regex)) {
                System.out.println();
                System.out.println("Error: You entered a string or a number out of range from 1 to 7!");
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
    public void editProduct(String productName, CouponUI couponUI) {
        if (productController.productList().containsKey(productName)) {
            Product product = productController.productList().get(productName);
            int userInput = 0;
            while (userInput != 5) {
                userInput = editMenu();
                // Divide the cases
                switch (userInput) {
                    case 1 -> {
                        System.out.println("Enter product description:");
                        String description = scanner.nextLine();
                        product.setDescription(description);
                    }
                    case 2 -> {
                        System.out.println("Enter the number of products:");
                        int quantityAvailable = Integer.parseInt(scanner.nextLine());
                        product.setQuantityAvailable(quantityAvailable);
                    }
                    case 3 -> {
                        System.out.println("Enter product price:");
                        double price = Double.parseDouble(scanner.nextLine());
                        product.setPrice(price);
                    }
                    case 4 -> {
                        if (product instanceof DigitalProduct) {
                            System.out.println("This is a digital product, so it does not have weight!");
                        } else if (product instanceof PhysicalProduct currentProduct) {
                            System.out.println("Enter product weight:");
                            double weight = Double.parseDouble(scanner.nextLine());
                            currentProduct.setWeight(weight);
                        }
                    }
                    case 5 -> {
                        System.out.printf(product.getName() + " current tax type is " + product.getTaxType() + "\n");
                        System.out.println("Please choose type tax type (FREE, NORMAL or LUXURY): ");
                        String taxInput = scanner.nextLine();
                        while (!taxInput.matches(Regex.TAX_TYPE)) {
                            taxInput = scanner.nextLine();
                        }
                        String tax = taxInput;
                        TaxType taxType = TaxType.getType(tax);
                        product.setTaxType(taxType);
                    }
                    case 6 -> couponUI.editCouponList(product);

                    default -> {
                        System.out.println();
                        System.out.println("Update product detail successfully!");
                        System.out.println();
                    }
                }
            }
        } else {
            System.out.println("Product doesn't exist");
        }


    }

}
