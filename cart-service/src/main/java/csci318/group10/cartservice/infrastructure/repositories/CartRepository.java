package csci318.group10.cartservice.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import csci318.group10.cartservice.domain.models.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
