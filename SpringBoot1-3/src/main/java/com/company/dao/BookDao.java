package com.company.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.entity.Book;


public interface BookDao extends CrudRepository<Book, Integer> {
    public Book findById(int id);
    public Book  findByBookTitle(String bookTitle);
    
}
