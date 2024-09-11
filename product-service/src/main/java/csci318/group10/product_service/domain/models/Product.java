package csci318.group10.product_service.domain.models;

import jakarta.persistence.*;

//Product class that provides the Schema and Associations for H2 Database
@Entity
public class Product {

    @Id
    @GeneratedValue
    private int ID;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private double price;

    @Column
    private int quantity;

    public Product() {} //Empty constructor

    //Getters and Setters
    public int getID() {
        return ID;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
