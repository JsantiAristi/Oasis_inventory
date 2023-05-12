package com.oasis.inventario.models;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String name;
    private String description;
    private String type;
    private int quantity;
    private String image;
    private double price;

    public Products() {
    }

    public Products(String name, String description, String type, int quantity, String image, double price) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.quantity = quantity;
        this.image = image;
        this.price = price;
    }

    public Long getId() {return id;}
    public String getName() {return name;}
    public int getQuantity() {return quantity;}
    public String getType() {return type;}
    public String getImage() {return image;}
    public String getDescription() {return description;}
    public double getPrice() {return price;}

    public void setName(String name) {this.name = name;}
    public void setQuantity(int quantity) {this.quantity = quantity;}
    public void setType(String type) {this.type = type;}
    public void setImage(String image) {this.image = image;}
    public void setDescription(String description) {this.description = description;}
    public void setPrice(double price) {this.price = price;}
}
