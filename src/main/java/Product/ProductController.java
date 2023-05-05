package Product;

import java.util.Map;

public class ProductController {
    ProductModel productModel = new ProductModel();
    ProductView productView = new ProductView();
    public boolean addProduct(Product product) {
        if(productModel.getPRODUCTS().containsKey(product.getName())){
            return false;
        }
        else {
            productModel.getPRODUCTS().put(product.getName(), product);
            return true;
        }
    }
    public boolean removeProduct(String productName) {
        if(productModel.getPRODUCTS().containsKey(productName)){
            productModel.getPRODUCTS().remove(productName);
            return true;
        }
        else{
            return false;
        }

    }
    public void viewProduct(){
        productView.displayAllProduct(productModel);
    }
    public Map<String,Product> productList(){
        return productModel.getPRODUCTS();
    }

}
