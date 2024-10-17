package csci318.group10.orderservice.domain.models;

import jakarta.persistence.*;

public enum OrderStatus {
    CREATED,
    PROCESSING,
    SHIPPED,
    DELIVERED,
    CANCELLED,
    RETURNED,
    REFUNDED
}