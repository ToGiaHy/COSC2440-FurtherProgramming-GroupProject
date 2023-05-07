/**
 * @author Group 11
 */
package ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartModel {
    private final List<ShoppingCart> SHOPPING_CARTS = new ArrayList<>();
    //Return the shopping cart ArrayList
        public List<ShoppingCart> getShoppingCarts() {
        return SHOPPING_CARTS;
    }

}
