package com.company.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.entity.Book;
import com.company.services.BookServices;


@RestController
public class BookController {

	@Autowired
	private BookServices bookServices;
	
	//get all book handler
	@GetMapping(value = "/books")
	public List<Book> getBooks() 
	{
		return this.bookServices.getAllBooks();
	}

	//get single book handler
	@GetMapping(value = "/books/{id}")
	public Book getSingleBook(@PathVariable int id) 
	{
		
		return this.bookServices.getBookByID(id);
	}

	//create a new book handler
	@PostMapping(value="/books")
	public Book addBook(@RequestBody Book book)
	{
	    Book b =this.bookServices.addBook(book);
		return b;

	}

    //delete a book handler with id
	@DeleteMapping("/books/{id}")
	public void deleteBook(@PathVariable("id") int id)
	{
		this.bookServices.deleteByid(id);
		
	}
	//update a existing book handler with id
	@PutMapping("books/{id}")
	public Book updateBook( @PathVariable int id,@RequestBody Book book)
	{
		this.bookServices.update(id,book);
		return book;
	}
	
}
