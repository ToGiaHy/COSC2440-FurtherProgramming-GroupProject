/**
 * @author Group 11
 */
package Product;

import java.util.HashMap;
import java.util.Map;

public class ProductModel {
    /**
     * Product model attributes
     */
    private final Map<String, Product> PRODUCTS = new HashMap<>();
    public ProductModel() {
    }
    //Return the product map
    public Map<String, Product> getPRODUCTS() {
        return PRODUCTS;
    }

}
