package com.oasis.inventario.DTO;

import com.oasis.inventario.models.Products;

public class ProductsDTO {
    private Long id;
    private String name;
    private String description;
    private String type;
    private int quantity;
    private String image;
    private double price;

    public ProductsDTO(Products products) {
        this.id = products.getId();
        this.name = products.getName();
        this.description = products.getDescription();
        this.type = products.getType();
        this.quantity = products.getQuantity();
        this.image = products.getImage();
        this.price = products.getPrice();
    }

    public Long getId() {return id;}
    public String getName() {return name;}
    public String getType() {return type;}
    public int getQuantity() {return quantity;}
    public String getImage() {return image;}
    public String getDescription() {return description;}
    public double getPrice() {return price;}
}
