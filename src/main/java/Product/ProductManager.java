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
    public static Map<String, Product> PRODUCTS = new HashMap<String, Product>();

    /**
     * Initial sample product data
     * <p>
     * This method create a initial data product for user can browse all
     * products and interact with system. It add 3 digital products, 2 digital
     * products can be gifted, 3 physical products, 2 physical products can be
     * gifted.
     * </p>
     */
    public static Map<String, Product> initialProducts() {
        PRODUCTS.put("Song", new DigitalProduct("Song", "this is a song", 10, 15.0, TaxType.FREE));
        PRODUCTS.put("Picture", new DigitalProduct("Picture", "this is a picture", 5, 20.0, TaxType.NORMAL));
        PRODUCTS.put("Art work", new DigitalProduct("Art work", "this is an art work", 12, 13.0, TaxType.LUXURY));
        PRODUCTS.put("NFT", new DigitalProductCanBeGifted("NFT", "this is a NFT", 8, 300.0, TaxType.LUXURY, ""));
        PRODUCTS.put("GenShin Impact", new DigitalProductCanBeGifted("GenShin Impact", "this is a game", 50, 250.0, TaxType.NORMAL,""));
        PRODUCTS.put("Iphone", new PhysicalProduct("Iphone", "this is an iphone", 11, 500.0, TaxType.LUXURY, 190.0));
        PRODUCTS.put("Macbook", new PhysicalProduct("Macbook", "this is a macbook", 99, 2000.0, TaxType.LUXURY,490.0));
        PRODUCTS.put("Bicycle", new PhysicalProduct("Bicycle", "this is a bicycle", 11, 400.0, TaxType.NORMAL,2000.0));
        PRODUCTS.put("Desk", new PhysicalProductCanBeGifted("Desk", "this is a desk", 59, 149.0, TaxType.FREE, 1000.0,"This is a gift message"));
        PRODUCTS.put("Chair", new PhysicalProductCanBeGifted("Chair", "this is a chair", 70, 59.0, TaxType.FREE, 400.0, "This is a gift message"));

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
}
