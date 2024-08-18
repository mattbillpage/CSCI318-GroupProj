package group.csci318groupproj.model;

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

    public Product() {} //Empty Constructor


    //Getters and Setters
    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
