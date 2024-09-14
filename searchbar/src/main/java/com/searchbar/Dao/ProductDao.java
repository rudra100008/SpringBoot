package com.searchbar.Dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.searchbar.Entity.Product;

public interface ProductDao extends JpaRepository<Product,Integer>{
}
