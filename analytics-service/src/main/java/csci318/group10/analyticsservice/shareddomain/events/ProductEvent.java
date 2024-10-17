package csci318.group10.analyticsservice.shareddomain.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductEvent {
    private int productId;
    private int quantity;


    @JsonCreator
    public ProductEvent(@JsonProperty("productId") int productId, @JsonProperty("quantity") int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters
    @JsonProperty("productId")
    public int getProductId() {
        return productId;
    }

    @JsonProperty("quantity")
    public int getQuantity() {
        return quantity;
    }

    // Setters
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "ProductEvent{productId=" + productId + ", quantity=" + quantity + '}';
    }
}