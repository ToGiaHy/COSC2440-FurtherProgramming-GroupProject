package Product;

import java.util.HashMap;
import java.util.Map;

public class ProductModel {
    /**
     * Product manager attributes
     */
    private final Map<String, Product> PRODUCTS = new HashMap<>();
    public ProductModel() {
    }

    public Map<String, Product> getPRODUCTS() {
        return PRODUCTS;
    }

}
