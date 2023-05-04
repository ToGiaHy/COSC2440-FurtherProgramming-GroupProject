package ShoppingCart;

import java.util.Comparator;
import java.util.List;

public class ShoppingCartView {
    ShoppingCartModel shoppingCartModel = new ShoppingCartModel();
        public List<ShoppingCart> sort(List<ShoppingCart> list) {

        list.sort(Comparator.comparingDouble((ShoppingCart o) -> o.getTotalWeight()));
        return list;
    }
        public List<ShoppingCart> sortCartList() {
        return sort(shoppingCartModel.getShoppingCarts());
    }

        public void displayAllCarts() {
        sortCartList();
        for (ShoppingCart cart : shoppingCartModel.getShoppingCarts()) {
            System.out.println(cart);
        }
    }

}
