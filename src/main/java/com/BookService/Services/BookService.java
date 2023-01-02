package com.BookService.Services;

import java.util.List;

import com.BookService.Payloads.BookDto;



public interface BookService {
	
	
	
	BookDto createBook(BookDto book);
	
	BookDto updateBook(BookDto book,Integer bookId);
	
	BookDto getBookById(Integer bookId);
	
	List<BookDto>getAllBook();
	
	void deleteBook(Integer bookId);
}
