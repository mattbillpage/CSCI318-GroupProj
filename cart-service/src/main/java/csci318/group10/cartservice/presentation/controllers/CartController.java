package csci318.group10.cartservice.presentation.controllers;


import csci318.group10.cartservice.domain.dto.CartItemDTO;
import csci318.group10.cartservice.domain.models.Cart;
import csci318.group10.cartservice.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/all")
    List<Cart> allCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/{id}")
    Cart getCart(@PathVariable int id) {
        return cartService.getCartById(id);
    }

    @PostMapping("/users/{userID}")
    public ResponseEntity<Integer> addCart(@PathVariable int userID) {
        int newCart = cartService.createCart(userID);
        return ResponseEntity.ok(newCart);
    }

    @GetMapping("/{userID}/items")
    public List<CartItemDTO> getCartItems(@PathVariable int userID) {
        return cartService.createCartItemsWithDetails(userID);
    }

    @PostMapping("/{userID}/{productID}")
    void addItemToCart(@PathVariable int userID, @PathVariable int productID) {
        cartService.addItemToCart(userID, productID);
    }
}
