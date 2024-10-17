package csci318.group10.orderservice.service;

import csci318.group10.orderservice.domain.models.Order;
import csci318.group10.orderservice.domain.models.OrderStatus;
import csci318.group10.orderservice.domain.models.OrderItem;
import csci318.group10.orderservice.infrastructure.repositories.OrderRepository;
import csci318.group10.orderservice.events.OrderCreatedEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;
    private final ApplicationEventPublisher eventPublisher;
    private final DiscoveryClient discoveryClient;

    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate, ApplicationEventPublisher eventPublisher, DiscoveryClient discoveryClient) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
        this.eventPublisher = eventPublisher;
        this.discoveryClient = discoveryClient;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public Order updateOrderStatus(Long id, OrderStatus status) {
        Optional<Order> orderOpt = orderRepository.findById(id);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setStatus(status);
            return orderRepository.save(order);
        }
        return null;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public Order createOrderFromCart(Long cartId) {
        String cartServiceUrl = discoveryClient.getInstances("cart-service")
                .stream()
                .findFirst()
                .map(instance -> instance.getUri().toString())
                .orElseThrow(() -> new IllegalStateException("Cart service not available"));

        // Fetch cart from Cart service
        CartDTO cart = restTemplate.getForObject(cartServiceUrl + "/cart/" + cartId, CartDTO.class);
        if (cart == null || cart.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty or does not exist");
        }

        Order order = new Order();
        order.setCustomerId(String.valueOf(cart.getUserId()));
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> orderItems = cart.getItems().stream()
                .map(this::convertCartItemToOrderItem)
                .collect(Collectors.toList());
        order.setItems(orderItems);
        order.setTotalAmount(calculateTotalAmount(orderItems));

        Order savedOrder = orderRepository.save(order);

        // Clear the cart
        restTemplate.delete(cartServiceUrl + "/cart/" + cartId);

        // Publish OrderCreatedEvent
        eventPublisher.publishEvent(new OrderCreatedEvent(savedOrder));

        return savedOrder;
    }

    private OrderItem convertCartItemToOrderItem(CartItemDTO cartItem) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(cartItem.getProductId());
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setPrice(cartItem.getPrice());
        return orderItem;
    }

    private Double calculateTotalAmount(List<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    private static class CartDTO {
        private int userId;
        private List<CartItemDTO> items;

        public int getUserId() {
            return userId;
        }

        public List<CartItemDTO> getItems() {
            return items;
        }
    }

    private static class CartItemDTO {
        private Long productId;
        private int quantity;
        private double price;

        public Long getProductId() {
            return productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getPrice() {
            return price;
        }
    }
}