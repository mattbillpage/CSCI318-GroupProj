package csci318.group10.cartservice.presentation.controllers;


import csci318.group10.cartservice.domain.models.Cart;
import csci318.group10.cartservice.service.CartService;
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
}
