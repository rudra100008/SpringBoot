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
	
	@GetMapping(value = "/books")
	public List<Book> getBooks() {
		return this.bookServices.getAllBooks();
	}
	@GetMapping(value = "/books/{id}")
	public Book getSingleBook(@PathVariable int id) {
		
		return this.bookServices.getBookByID(id);
	}
	@PostMapping(value="/books")
	public Book addBook(@RequestBody Book book)
	{
	    Book b =this.bookServices.addBook(book);
		return b;

	}

	@DeleteMapping("/books/{id}")
	public Book  deleteBook(@PathVariable("id") int id){
		Book book= this.bookServices.deleteById(id);
		return book;
	}
	
	@PutMapping("books/{id}")
	public Book updateBook( @PathVariable int id,@RequestBody Book book){
		Book updatebook =this.bookServices.updateByID(id,book.getBookTitle(),book.getAuthor());
		return updatebook;
	}
	
}
