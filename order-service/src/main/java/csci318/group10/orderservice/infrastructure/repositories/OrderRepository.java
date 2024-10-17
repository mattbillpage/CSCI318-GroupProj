package csci318.group10.orderservice.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import csci318.group10.orderservice.domain.models.Order;
import csci318.group10.orderservice.domain.models.OrderItem;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
