/**
 * @author Group 11
 */
package shoppingcart;

import java.util.Comparator;
import java.util.List;

public class ShoppingCartView {
        //Sort the shopping cart based on their weights
        public List<ShoppingCart> sort(List<ShoppingCart> list) {

        list.sort(Comparator.comparingDouble((ShoppingCart o) -> o.getTotalWeight()));
        return list;
    }
    //Sort the entire cart list based on weight
        public List<ShoppingCart> sortCartList(ShoppingCartModel shoppingCartModel) {
        return sort(shoppingCartModel.getShoppingCarts());
    }
//display all carts after sorting the cart list
        public void displayAllCarts(ShoppingCartModel shoppingCartModel) {
        List<ShoppingCart> sortedCartList = sortCartList(shoppingCartModel);
        for (ShoppingCart cart : sortedCartList) {
            System.out.println(cart);
        }
    }

}
