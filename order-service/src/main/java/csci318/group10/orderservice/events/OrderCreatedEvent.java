package csci318.group10.orderservice.events;

import csci318.group10.orderservice.domain.models.Order;
import org.springframework.context.ApplicationEvent;
import csci318.group10.orderservice.domain.models.Order;

public class OrderCreatedEvent extends ApplicationEvent {
    private final Order order;

    public OrderCreatedEvent(Order order) {
        super(order);
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}