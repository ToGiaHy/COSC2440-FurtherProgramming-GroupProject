/**
 * @author Group 11
 */
package ShoppingCart;

import java.util.List;

public class ShoppingCartController {
    ShoppingCartModel shoppingCartModel = new ShoppingCartModel();
    ShoppingCartView shoppingCartView = new ShoppingCartView();

    /**
     * add the shopping cart instance to the shopping cart list
     * @param shoppingCart
     * @return the shopping cart model
     */
    public boolean add(ShoppingCart shoppingCart) {
        return this.shoppingCartModel.getShoppingCarts().add(shoppingCart);
    }

    /**
     * Remove the shopping cart from the shopping cart arraylist
     * @param shoppingCart
     * @return the shopping cart model
     */
    public boolean remove(ShoppingCart shoppingCart) {
        return this.shoppingCartModel.getShoppingCarts().remove(shoppingCart);
    }
    //calls the displayAllCarts method from the ShoppingCartView class
    public void viewCarts(){
        shoppingCartView.displayAllCarts(shoppingCartModel);
    }
    //Return the shopping carts arraylist from the ShoppingCartModel
    public List<ShoppingCart> shoppingCartList() {
        return shoppingCartModel.getShoppingCarts();
    }
    //Search and return cart by their ID
    public ShoppingCart findCartByID(String id) {
        for (ShoppingCart cart : shoppingCartList()) {
            if (cart.getId().equalsIgnoreCase(id)) {
                return cart;
            }
        }
        return null;
    }
}
