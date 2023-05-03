package Product;

import java.util.Map;

public class ProductController {
    ProductModel productModel;
    ProductView productView;
    public void addProduct(Product product) {
        productModel.getPRODUCTS().put(product.getName(), product);
    }
    public void removeProduct(Product product) {
        productModel.getPRODUCTS().remove(product);
    }
    public void viewProduct(){
        productView.displayAllProduct();
    }
    public Map<String,Product> productList(){
        return productModel.getPRODUCTS();
    }

}
