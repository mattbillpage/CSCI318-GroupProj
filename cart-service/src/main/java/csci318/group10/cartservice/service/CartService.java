package csci318.group10.cartservice.service;

import csci318.group10.cartservice.domain.dto.CartItemDTO;
import csci318.group10.cartservice.domain.models.Cart;
import csci318.group10.cartservice.domain.models.CartItem;
import csci318.group10.cartservice.infrastructure.repositories.CartRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final RestTemplate restTemplate;

    public CartService(CartRepository cartRepository, ApplicationEventPublisher eventPublisher, RestTemplate restTemplate) {
        this.cartRepository = cartRepository;
        this.eventPublisher = eventPublisher;
        this.restTemplate = restTemplate;
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

    public List<CartItemDTO> createCartItemsWithDetails(int id) {
        Cart existingCart = cartRepository.findById(id).orElse(null);
        if (existingCart == null) {
            throw new RuntimeException("Failure: Cart not found");
        }

        return existingCart.getItems().stream().map(this::createCartItemDTO).collect(Collectors.toList());
    }

    private CartItemDTO createCartItemDTO(CartItem cartItem) {
        CartItemDTO dto = new CartItemDTO(cartItem.getProductId(), cartItem.getQuantity());


            ProductDetails productDetails = restTemplate.getForObject(
                    "http://localhost:8080/product/" + cartItem.getProductId(), null, ProductDetails.class);

            if (productDetails != null) {
                throw new RuntimeException(productDetails.name);
                //dto.setProductName(productDetails.getName());
                //dto.setProductDescription(productDetails.getDescription());
                //dto.setProductPrice(productDetails.getPrice());
            }


        return dto;

    }

    public void addItemToCart(int userID, int productID) {
        Cart existingCart = cartRepository.findById(userID).orElse(null);
        if (existingCart != null) {
            CartItem existingItem = existingCart.getItems().stream()
                    .filter(item -> item.getProductId() == productID)
                    .findFirst()
                    .orElse(null);
            if (existingItem != null) {
                existingItem.setQuantity(existingItem.getQuantity() + 1);
            } else {
                existingCart.getItems().add(new CartItem(productID, 1));
            }
        }
        cartRepository.save(existingCart);
    }

    class ProductDetails {
        private String name;
        private String description;
        private double price;

        public ProductDetails() {
            // Default constructor
        }


        // Getters and setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }


    }
}
