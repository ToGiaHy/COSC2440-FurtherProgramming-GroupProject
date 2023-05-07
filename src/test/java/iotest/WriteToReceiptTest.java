/**
 * @author Group 11
 */
package iotest;

import io.CartRelatedActions;
import io.ReadProductsFile;
import org.junit.jupiter.api.Test;
import product.ProductController;
import shoppingcart.ShoppingCartController;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WriteToReceiptTest {
    ShoppingCartController shoppingCartController = new ShoppingCartController();
    ProductController productController = new ProductController();
    CartRelatedActions cartRelatedActions = new CartRelatedActions(productController,shoppingCartController);
    ReadProductsFile readProductsFile = new ReadProductsFile(productController);

    /**
     * Write the C2 CartID to the receipt.txt
     * Checked if the cart has been written or not
     */
    @Test
    public void writeToReceipt(){
       cartRelatedActions.read();
       readProductsFile.read();
       cartRelatedActions.writeReceipt("C2");
       assertTrue(cartRelatedActions.exists("C2"));
   }
}
