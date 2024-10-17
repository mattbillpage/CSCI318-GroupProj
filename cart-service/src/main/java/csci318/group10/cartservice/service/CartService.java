package csci318.group10.cartservice.service;

import csci318.group10.cartservice.domain.dto.CartItemDTO;
import csci318.group10.cartservice.domain.dto.ProductDetails;
import csci318.group10.cartservice.domain.models.Cart;
import csci318.group10.cartservice.domain.models.CartItem;
import csci318.group10.cartservice.infrastructure.repositories.CartRepository;
import csci318.group10.cartservice.shareddomain.events.CartEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final RestTemplate restTemplate;
    private final KafkaTemplate<String, CartEvent> kafkaTemplate;
    private static final String TOPIC = "cart-events";

    public CartService(CartRepository cartRepository, ApplicationEventPublisher eventPublisher, RestTemplate restTemplate, KafkaTemplate<String, CartEvent> kafkaTemplate) {
        this.cartRepository = cartRepository;
        this.eventPublisher = eventPublisher;
        this.restTemplate = restTemplate;
        this.kafkaTemplate = kafkaTemplate;
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

        try {
            ResponseEntity<ProductDetails> response = restTemplate.getForEntity(
                    "http://localhost:8080/product/" + cartItem.getProductId(),
                    ProductDetails.class
            );

            ProductDetails productDetails = response.getBody();

            if (productDetails != null) {
                dto.setProductName(productDetails.getName());
                dto.setProductDescription(productDetails.getDescription());
                dto.setProductPrice(productDetails.getPrice());
            }
        } catch (Exception e) {
            // Log the error
            System.err.println("Error fetching product details for product ID: " + cartItem.getProductId());
            e.printStackTrace();
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

        restTemplate.put("http://localhost:8080/product/" + productID + "/reduceQuantity/" + "1", null);

        //Publish event when cart is updated
        updateCart(existingCart.getID(), existingCart.getItems());
    }

    public double getCartTotal(int userID) {
        Cart existingCart = cartRepository.findById(userID).orElse(null);
        if (existingCart != null) {
            List<CartItemDTO> cartItems = createCartItemsWithDetails(userID);

            return cartItems.stream().mapToDouble(item -> item.getProductPrice() * item.getQuantity()).sum();
        }
        return 0;
    }

    public void updateCart(int cartId, List<CartItem> items) {
        String cartIdString = String.valueOf(cartId);
        CartEvent event = new CartEvent(cartId, items);
        kafkaTemplate.send(TOPIC, cartIdString, event);
    }
}
