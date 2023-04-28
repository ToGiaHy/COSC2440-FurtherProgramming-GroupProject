/**
 * @author <Vo Thanh Thong - s3878071>
 */

package Product;

import java.util.HashMap;
import java.util.Map;

public class ProductManager {
    /**
     * Product manager attributes
     */
    public static Map<String, Product> PRODUCTS = new HashMap<>();


    /**
     * Initial sample product data
     * <p>
     * This method create a initial data product for user can browse all
     * products and interact with system. It add 3 digital products, 2 digital
     * products can be gifted, 3 physical products, 2 physical products can be
     * gifted.
     * </p>
     */

    public static Map<String, Product> getPRODUCTS() {
        return PRODUCTS;
    }

    /**
     * Add a product to PRODUCTS data
     * <p>
     * This method is used to help user can create new product and add to the
     * product data of the system.
     * </p>
     * @param product An instance store information of the product
     */
    public static void addProduct(Product product) {
        PRODUCTS.put(product.getName(), product);
    }

    /**
     * Add a product to PRODUCTS data
     * <p>
     * This method is used to help user can delete a product and remove from the
     * product data of the system.
     * </p>
     * @param product An instance store information of the product
     */
    public static void removeProduct(Product product) {
        PRODUCTS.remove(product);
    }

    public static void displayAllProduct() {
        for (String name: PRODUCTS.keySet()) {
            Product value = PRODUCTS.get(name);
            System.out.println("#=====#");
            System.out.println(value);
        }
    }



}
