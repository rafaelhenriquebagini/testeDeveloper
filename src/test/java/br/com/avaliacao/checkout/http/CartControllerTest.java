
package br.com.avaliacao.checkout.http;

import br.com.avaliacao.checkout.model.Cart;
import br.com.avaliacao.checkout.model.CartItem;
import br.com.avaliacao.checkout.model.Produto;
import static junit.framework.TestCase.assertEquals;
import org.junit.Test;

public class CartControllerTest {   
    
    public CartControllerTest() {
    }
    
    @Test
    public void addItemToCart() {
        Cart cart = new Cart();
        Produto p1 = new Produto("123456", "ProdutoUm", "MarcaUm", 11.00);
        Produto p2 = new Produto("234567", "ProdutoDois", "MarcaDois", 12.00);
        Produto p3 = new Produto("345678", "ProdutoTres", "MarcaTres", 13.00);
        CartController cartController = new CartController();

        CartItem cartItem1 = new CartItem(p1, 1);
        cart = cartController.addItemToCart(cart, "1", cartItem1);
        assertEquals(cart.getItems().get(0), cartItem1);
        
        CartItem cartItem2 = new CartItem(p2, 1);
        cart = cartController.addItemToCart(cart, "1", cartItem2);
        assertEquals(cart.getItems().get(1), cartItem2);
        
        cart = cartController.addItemToCart(cart, "1", cartItem1);
        assertEquals(cart.getItems().get(0).getQuantity(), Integer.valueOf(2));

        CartItem cartItem3 = new CartItem(p3, 1);
        cart = cartController.addItemToCart(cart, "1", cartItem3);
        assertEquals(cart.getItems().get(2), cartItem3);
        
    }
    
}
