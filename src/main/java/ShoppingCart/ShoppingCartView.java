package ShoppingCart;

import java.util.Comparator;
import java.util.List;

public class ShoppingCartView {
    ShoppingCartController shoppingCartController;
        public List<ShoppingCart> sort(List<ShoppingCart> list) {

        list.sort(Comparator.comparingDouble((ShoppingCart o) -> o.getTotalWeight()));
        return list;
    }
        public List<ShoppingCart> sortCartList() {
        return sort(shoppingCartController.shoppingCartList());
    }

        public void displayAllCarts() {
        sortCartList();
        for (ShoppingCart cart : shoppingCartController.shoppingCartList()) {
            System.out.println(cart);
        }
    }

}
