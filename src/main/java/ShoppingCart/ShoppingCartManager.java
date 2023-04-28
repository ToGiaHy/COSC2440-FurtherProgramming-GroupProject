/**
 * @author <Vo Thanh Thong - s3878071>
 */

package ShoppingCart;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShoppingCartManager {
    public final static List<ShoppingCart> SHOPPING_CARTS = new ArrayList<ShoppingCart>();

    public static List<ShoppingCart> sort(List<ShoppingCart> list) {

        list.sort(Comparator.comparingDouble((ShoppingCart o) -> o.getTotalWeight()));
        return list;
    }

    public static List<ShoppingCart> sortCartList() {
        return sort(SHOPPING_CARTS);
    }
    public static ShoppingCart findCartByID(int id) {
        for (ShoppingCart cart : SHOPPING_CARTS) {
            if (cart.getId() == id) {
                return cart;
            }
        }
        return null;
    }

    public static List<ShoppingCart> getShoppingCarts() {
        return SHOPPING_CARTS;
    }

    public static void displayAllCarts() {
        sortCartList();
        for (ShoppingCart cart : SHOPPING_CARTS) {
            System.out.println(cart);
        }
    }
}
