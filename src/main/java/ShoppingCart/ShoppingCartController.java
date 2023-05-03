package ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartController {
    ShoppingCartModel shoppingCartModel;
    ShoppingCartView shoppingCartView;
        public boolean add(ShoppingCart shoppingCart) {
        return this.shoppingCartModel.getShoppingCarts().add(shoppingCart);
    }
        public boolean remove(ShoppingCart shoppingCart) {
        return this.shoppingCartModel.getShoppingCarts().remove(shoppingCart);
    }
    public List<ShoppingCart> shoppingCartList() {
        return shoppingCartModel.getShoppingCarts();
    }
        public ShoppingCart findCartByID(String id) {
        for (ShoppingCart cart : shoppingCartList()) {
            if (cart.getId().equalsIgnoreCase(id)) {
                return cart;
            }
        }
        return null;
    }
}
