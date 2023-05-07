/**
 * @author Group 11
 */
package Product;

import java.util.Map;

public class ProductController {
    ProductModel productModel = new ProductModel();
    ProductView productView = new ProductView();

    /**
     * Add a product into the product hashmap
     * Check if product already exists or not
     * @param product
     * @return boolean
     */
    public boolean addProduct(Product product) {
        if(productModel.getPRODUCTS().containsKey(product.getName())){
            return false;
        }
        else {
            productModel.getPRODUCTS().put(product.getName(), product);
            return true;
        }
    }

    /**
     * Remove a product instance from the product hashmap
     * Check if the product exists or not
     * @param productName
     * @return boolean
     */
    public boolean removeProduct(String productName) {
        if(productModel.getPRODUCTS().containsKey(productName)) {
            productModel.getPRODUCTS().remove(productName);
            return true;
        }
        else{
            return false;
        }

    }
    //Calls the ProductView class to display the products stored in the product model
    public void viewProduct(){
        productView.displayAllProduct(productModel);
    }
    //Get the product list from the product model
    public Map<String,Product> productList(){
        return productModel.getPRODUCTS();
    }

}
