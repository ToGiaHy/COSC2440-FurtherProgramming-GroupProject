package ShoppingCart;

import java.util.Comparator;
import java.util.List;

public class ShoppingCartView {
        public List<ShoppingCart> sort(List<ShoppingCart> list) {

        list.sort(Comparator.comparingDouble((ShoppingCart o) -> o.getTotalWeight()));
        return list;
    }
        public List<ShoppingCart> sortCartList(ShoppingCartModel shoppingCartModel) {
        return sort(shoppingCartModel.getShoppingCarts());
    }

        public void displayAllCarts(ShoppingCartModel shoppingCartModel) {
        List<ShoppingCart> sortedCartList = sortCartList(shoppingCartModel);
        for (ShoppingCart cart : sortedCartList) {
            System.out.println(cart);
        }
    }

}
