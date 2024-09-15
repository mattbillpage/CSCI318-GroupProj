package csci318.group10.cartservice.service;

import csci318.group10.cartservice.domain.models.Cart;
import csci318.group10.cartservice.infrastructure.repositories.CartRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ApplicationEventPublisher eventPublisher;

    public CartService(CartRepository cartRepository, ApplicationEventPublisher eventPublisher) {
        this.cartRepository = cartRepository;
        this.eventPublisher = eventPublisher;
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getCartById(int id) {
        return cartRepository.findById(id).orElse(null);
    }

    public int createCart(int userID) {
        Cart existingCart = cartRepository.findById(userID).orElse(null);
        if (existingCart != null) {
            return existingCart.getID();
        }
        Cart newCart = new Cart(userID);
        Cart savedCart = cartRepository.save(newCart);

        return savedCart.getID();
    }


}
