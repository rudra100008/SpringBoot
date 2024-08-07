package com.company.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.company.entity.Book;

@Component
public class BookServices {
	private static List<Book> list=new ArrayList<Book>();
	
	static {
	    list.add(new Book(2,"OnePiece","Echirio Oda"));
	    list.add(new Book(3,"Naruto","Masahi Kisimoto"));
	    list.add(new Book(4,"Demon slayer","Koyoharu Gotouge"));
	    list.add(new Book(5,"Jujutsu kaisen","Gege Akutami"));
	}

	public List<Book> getAllBooks(){
		return list;
	}
	//get single book
	public Book getBookByID(int id) {
		Book book;
		book=list.stream().filter(e->e.getBookId()==id).findFirst().get();
		return book;
	}
	public Book addBook(Book book){
     list.add(book);
	 return book;
	}
	// public Book deleteById(int id)
	// {
	// 	Book bookToRemove =list.stream().filter(e->e.getBookId()==id).findFirst().orElse(null);
	// 	list.remove(bookToRemove);
	// 	return bookToRemove;
	// }

	public void deleteByid(int bid)
	{
		list.removeIf(book->book.getBookId() == bid);
	}
	public Book updateByID(int id,String bookTitle,String author)
	{
		Book bookToUpdate =list.stream().filter(e->e.getBookId()==id).findFirst().orElse(null);
	
		if(bookToUpdate !=null){
			bookToUpdate.setBookId(id);
			bookToUpdate.setBookTitle(bookTitle);
			bookToUpdate.setAuthor(author);  
			return bookToUpdate;
		}else
		{
			System.out.println("Book not found");
			return null;
		}
	}
	public void update(int id,Book book)
	{
		
		list=list.stream().map(e->{
			if(e.getBookId()==id){
				e.setBookTitle(book.getBookTitle());
				e.setAuthor(book.getAuthor());
			}
			return e;
		}).collect(Collectors.toList());
		
	}
	
}
