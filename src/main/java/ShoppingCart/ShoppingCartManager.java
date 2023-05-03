/**
 * @author <Vo Thanh Thong - s3878071>
 */

package ShoppingCart;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShoppingCartManager {
    private final List<ShoppingCart> SHOPPING_CARTS;

    public ShoppingCartManager() {
        SHOPPING_CARTS = new ArrayList<>();
    }

    public List<ShoppingCart> sort(List<ShoppingCart> list) {

        list.sort(Comparator.comparingDouble((ShoppingCart o) -> o.getTotalWeight()));
        return list;
    }

    public List<ShoppingCart> sortCartList() {
        return sort(SHOPPING_CARTS);
    }

    public ShoppingCart findCartByID(String id) {
        for (ShoppingCart cart : SHOPPING_CARTS) {
            if (cart.getId().equalsIgnoreCase(id)) {
                return cart;
            }
        }
        return null;
    }

    public List<ShoppingCart> getShoppingCarts() {
        return SHOPPING_CARTS;
    }

    public void displayAllCarts() {
        sortCartList();
        for (ShoppingCart cart : SHOPPING_CARTS) {
            System.out.println(cart);
        }
    }

    public boolean add(ShoppingCart shoppingCart) {
        return this.SHOPPING_CARTS.add(shoppingCart);
    }

    public boolean remove(ShoppingCart shoppingCart) {
        return this.SHOPPING_CARTS.remove(shoppingCart);
    }
}
