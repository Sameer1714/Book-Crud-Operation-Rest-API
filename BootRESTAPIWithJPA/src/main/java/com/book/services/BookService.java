package com.book.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.book.dao.BookRepository;
import com.book.entity.Book;

@Component
public class BookService {
	
	@Autowired
	BookRepository  bookRepository;
	
	public List<Book> getAllBook(){
		List<Book> list = (List<Book>) bookRepository.findAll();
		
		return list;
	
	}
	
	public Book getBookById(int id) {
		Book book=null;
		try {
		Optional<Book> optional = bookRepository.findById(id);
		 book = optional.get();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}
	
	public Book addBook(Book b) {
		Book book = bookRepository.save(b);
		
		return book;
		
	}
	
	public void deleteById(int id) {
		bookRepository.deleteById(id);
	}
	
	public void updateBook(Book book,int id) {
		
		book.setId(id);
		
		bookRepository.save(book);
	}

}
