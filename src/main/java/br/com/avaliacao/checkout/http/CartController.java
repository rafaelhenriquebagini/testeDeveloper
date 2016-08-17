package br.com.avaliacao.checkout.http;

import br.com.avaliacao.checkout.db.CartDBInMemory;
import br.com.avaliacao.checkout.model.Produto;
import br.com.avaliacao.checkout.model.Cart;
import br.com.avaliacao.checkout.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartDBInMemory cartDB;

    @RequestMapping(value = "/adicionar")
    public void addToCart(String cartId, Integer q, String codeProduct, String nameProduct, String brand, Double price) {
        Produto p = new Produto(cartId, brand, cartId, price);
        CartItem item = new CartItem(p, q);
        
        Cart cart = cartDB.findOne(cartId);

        cart = addItemToCart(cart, cartId, item);
        
        cartDB.save(cart);
    }

    public Cart addItemToCart(Cart cart, String cartId, CartItem item){
        boolean found = false;
        if (cart == null) {
            cart = new Cart();
            cart.setCartId(cartId);
            cart.getItems().add(item);
        } //ATENCAO: caso o carrinho ja exista, o fluxo atende?  
        else{
            for (CartItem cartItem : cart.getItems()) {
                if(cartItem.getProduto().getCodigo().equals(item.getProduto().getCodigo())){
                    cartItem.incrementQuantity(item.getQuantity());
                    found = true;
                    break;
                }
            }
            if(!found){
                cart.getItems().add(item);
            }
        }        
        return cart;
    }

}

