package com.searchbar.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private double price;
    private String category;

    public Product()
    {
        super();
    }
    public Product(int id,String name,double price,String category)
    {
        this.id=id;
        this.name=name;
        this.price=price;
        this.category=category;
    }
}
