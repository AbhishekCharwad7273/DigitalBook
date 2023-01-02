package com.BookService.Services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BookService.Entities.Book;
import com.BookService.Payloads.BookDto;
import com.BookService.Repository.BookRepo;
import com.BookService.Services.BookService;
import com.BookService.Exception.*;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepo bookRepo;
	
	@Autowired
	private ModelMapper modalMapper;

	@Override
	public BookDto createBook(BookDto bookDto) {

		Book book = this.dtoToBook(bookDto);

		Book savedBook = this.bookRepo.save(book);

		return this.bookToDto(savedBook);
	}

	@Override
	public BookDto updateBook(BookDto bookDto, Integer bookId) {

		Book book = this.bookRepo.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book", "Id", bookId));

		book.setBookCategory(bookDto.getBookCategory());
		book.setBookActive(bookDto.getBookActive());
		book.setBookPrice(bookDto.getBookPrice());
		book.setBookPublisher(bookDto.getBookPublisher());
		book.setBookContent(bookDto.getBookContent());

		Book updateBook = this.bookRepo.save(book);

		BookDto bookDto1 = this.bookToDto(updateBook);

		return bookDto1;
	}

	@Override
	public BookDto getBookById(Integer bookId) {
		
		Book book = this.bookRepo.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book", "Id", bookId));
		
		
		return this.bookToDto(book);
	}

	@Override
	public List<BookDto> getAllBook() {
		
		List<Book> books = this.bookRepo.findAll();
		
		List<BookDto> bookDto = books.stream().map(book -> this.bookToDto(book)).collect(Collectors.toList());
		
		return bookDto;
		
	}

	@Override
	public void deleteBook(Integer bookId) {
		
		Book book = this.bookRepo.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book", "Id", bookId));
		
		this.bookRepo.delete(book);
		

	}

	public Book dtoToBook(BookDto bookDto) {
		Book book = this.modalMapper.map(bookDto, Book.class);
		
		/*
		 * Book book = new Book();
		book.setBookId(bookDto.getBookId());
		book.setBookActive(bookDto.getBookActive());
		book.setBookCategory(bookDto.getBookCategory());
		book.setBookPrice(bookDto.getBookPrice());
		book.setBookPublisher(bookDto.getBookPublisher());
		book.setBookContent(bookDto.getBookContent());
		*/
		return book;
	}

	public BookDto bookToDto(Book book) {
		
		BookDto bookDto=this.modalMapper.map(book, BookDto.class);
		/*
		BookDto bookDto = new BookDto();
		bookDto.setBookId(book.getBookId());
		bookDto.setBookActive(book.getBookActive());
		bookDto.setBookCategory(book.getBookCategory());
		bookDto.setBookPrice(book.getBookPrice());
		bookDto.setBookPublisher(book.getBookPublisher());
		bookDto.setBookContent(book.getBookContent());
		*/
		return bookDto;
	}
}
