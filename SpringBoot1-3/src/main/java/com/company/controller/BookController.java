package com.company.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.entity.Book;
import com.company.services.BookServices;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
public class BookController {

	@Autowired
	private BookServices bookServices;
	
	//get all book handler
	@GetMapping(value = "/books")
	public ResponseEntity<List<Book>> getBooks() 
	{
		List<Book> list=this.bookServices.getAllBooks();
		if(list.size()<=0)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		 
		}
		return ResponseEntity.of(Optional.of(list)) ;
	}

	//get single book handler
	@GetMapping(value = "/books/{id}")
	public ResponseEntity<Book> getSingleBook(@PathVariable int id) 
	{
        Book book=this.bookServices.getBookByID(id);
		if(book==null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		}
		return ResponseEntity.of(Optional.of(book));
	}

	//create a new book handler
	@PostMapping(value="/books")
	public ResponseEntity<Book> addBook(@RequestBody Book book)
	{
	    Book b; 
		try {
			b=this.bookServices.addBook(book);
			 return ResponseEntity.of(Optional.of(b));
		} catch (Exception e) {
			e.getLocalizedMessage();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

    //delete a book handler with id
	@DeleteMapping("/books/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable("id") int id)
	{
		try{
			this.bookServices.deleteByid(id);
			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		}catch(Exception e){
			e.getLocalizedMessage();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	//update a existing book handler with id
	@PutMapping("books/{id}")
	public ResponseEntity<Book> updateBook( @PathVariable int id,@RequestBody Book book)
	{
		try {
			this.bookServices.update(id,book);
			return ResponseEntity.ok().body(book);
		} catch (Exception e) {
			e.getLocalizedMessage();
			 return ResponseEntity.internalServerError().build();
		}
		
	}
	@GetMapping("/books/search")
	public ResponseEntity<Book> searchMethod(@RequestParam("title") String title) {
		Book b=this.bookServices.findBookByName(title);
		return ResponseEntity.ok(b);
	}
	
	
}
