package csci318.group10.analyticsservice.shareddomain.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CartEvent {
    private int cartId;
    private List<CartItem> items;

    @JsonCreator
    public CartEvent(@JsonProperty("cartId") int cartId,
                     @JsonProperty("items") List<CartItem> items) {
        this.cartId = cartId;
        this.items = items;
    }

    // Getters
    @JsonProperty("cartId")
    public int getCartId() {
        return cartId;
    }

    @JsonProperty("items")
    public List<CartItem> getItems() {
        return items;
    }

    // Setters
    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "CartEvent{cartId=" + cartId + ", items=" + items + '}';
    }
}
