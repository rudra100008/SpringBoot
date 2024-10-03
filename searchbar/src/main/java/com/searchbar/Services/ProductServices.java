package com.searchbar.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searchbar.Dao.ProductDao;
import com.searchbar.Entity.Product;

@Service
public class ProductServices {
    @Autowired
    private ProductDao productDao;
    // private static List<Product> products=new ArrayList<>();
    // static {
    // products.add(new Product(1,"laptop",100000,"electronic Device"));
    // products.add(new Product(2,"Lays",50.00,"food"));
    // products.add(new Product(3,"pressure Cooker",1000,"Kitchen utensil"));
    // }

    public List<Product> getAllProduct() {
        return this.productDao.findAll();
    }

    // function to save the products in database
    public Product saveProduct(Product product) {
        return this.productDao.save(product);
    }

}
