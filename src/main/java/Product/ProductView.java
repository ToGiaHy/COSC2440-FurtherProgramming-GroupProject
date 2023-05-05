package Product;

public class ProductView {

        public void displayAllProduct(ProductModel productModel) {
        for (String name : productModel.getPRODUCTS().keySet()) {
            Product value = productModel.getPRODUCTS().get(name);
            System.out.println("#=====#");
            System.out.println(value);
        }
    }
}
