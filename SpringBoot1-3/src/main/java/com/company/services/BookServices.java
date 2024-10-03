package com.company.services;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.dao.BookDao;
import com.company.entity.Book;

@Component
public class BookServices {
	// private static List<Book> list=new ArrayList<Book>();
	
	// static {
	//     list.add(new Book(2,"OnePiece","Echirio Oda"));
	//     list.add(new Book(3,"Naruto","Masahi Kisimoto"));
	//     list.add(new Book(4,"Demon slayer","Koyoharu Gotouge"));
	//     list.add(new Book(5,"Jujutsu kaisen","Gege Akutami"));
	// }

	@Autowired
	private BookDao bookDao ;

	public List<Book> getAllBooks(){
		List<Book> list=(List<Book>) this.bookDao.findAll();
		return list;
	}
	//get single book
	public Book getBookByID(int id) {
		Book book=null;
		try{
		// book=list.stream().filter(e->e.getBookId()==id).findFirst().get();
		book = this.bookDao.findById(id);
		}catch(Exception e){
			e.getLocalizedMessage();
		}
		return book;
	}
	public Book addBook(Book book){
    //  list.add(book);
	Book result=this.bookDao.save(book);
	
	 return result;
	}
	// public Book deleteById(int id)
	// {
	// 	Book bookToRemove =list.stream().filter(e->e.getBookId()==id).findFirst().orElse(null);
	// 	list.remove(bookToRemove);
	// 	return bookToRemove;
	// }

	public void deleteByid(int bid)
	{
		// list.removeIf(book->book.getBookId() == bid);
		this.bookDao.deleteById(bid);
	}
	// public Book updateByID(int id,String bookTitle,String author)
	// {
	// 	// Book bookToUpdate =list.stream().filter(e->e.getBookId()==id).findFirst().orElse(null);
	
	// 	// if(bookToUpdate !=null){
	// 	// 	bookToUpdate.setBookId(id);
	// 	// 	bookToUpdate.setBookTitle(bookTitle);
	// 	// 	bookToUpdate.setAuthor(author);  
	// 	// 	return bookToUpdate;
	// 	// }else
	// 	// {
	// 	// 	System.out.println("Book not found");
	// 	// 	return null;
	// 	// }
	// }
	public void update(int id,Book book)
	{
		
	 book.setBookId(id);
	 this.bookDao.save(book);
		
	}
	public Book findBookByName(String title){
		Book b=this.bookDao.findByBookTitle(title);
		return b;
	}
	
}
