package Product;

public class ProductView {
    ProductController productController;
        public void displayAllProduct() {
        for (String name : productController.productList().keySet()) {
            Product value = productController.productList().get(name);
            System.out.println("#=====#");
            System.out.println(value);
        }
    }
}
