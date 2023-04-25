/**
 * @author <Vo Thanh Thong - s3878071>
 */

package ShoppingCart;

import java.util.ArrayList;
import java.util.Comparator;

public class ShoppingCartManager {

    public static ArrayList<ShoppingCart> sort(ArrayList<ShoppingCart> list) {

        list.sort(Comparator.comparingDouble((ShoppingCart o) -> o.getTotalWeight()));
        return list;
    }
    private final static ArrayList<ShoppingCart> SHOPPING_CARTS = new ArrayList<ShoppingCart>();

    public static ArrayList<ShoppingCart> getShoppingCarts() {

        return sort(SHOPPING_CARTS);
    }
    public static ShoppingCart findCartByID(int id) {
        for (ShoppingCart cart: SHOPPING_CARTS) {
            if (cart.getId() == id) {
                return cart;
            }
        }
        return null;
    }


}
