package csci318.group10.orderservice.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import csci318.group10.orderservice.domain.models.Order;

@Component
public class OrderEventListener {

    @EventListener
    public void handleOrderCreatedEvent(OrderCreatedEvent event) {
        Order order = event.getOrder();
        System.out.println("Order created: " + order.getId());
    }
}