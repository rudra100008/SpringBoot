package com.searchbar.RestController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.searchbar.Entity.Product;
import com.searchbar.Services.ProductServices;

@RestController
public class ProductAPI {
    @Autowired
    private ProductServices productServices;

    //Handler to  get all Products from database
    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllBookHandler()
    {
        List<Product> products= this.productServices.getAllProduct();
        if (products==null || products.isEmpty()) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(products);
    }
    @PostMapping("/api/product")
    public ResponseEntity<Product> setProduct(@RequestBody Product product)
    {
        Product savedProduct =this.productServices.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }
}
