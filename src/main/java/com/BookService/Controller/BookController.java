package com.BookService.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BookService.Payloads.ApiResponse;
import com.BookService.Payloads.BookDto;
import com.BookService.Services.BookService;

@RestController
@RequestMapping("/api/v1/digitalbook/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/")
	public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto) {
		BookDto createBookDto = this.bookService.createBook(bookDto);

		return new ResponseEntity<>(createBookDto, HttpStatus.CREATED);

	}

	@PutMapping("/{bookId}")
	public ResponseEntity<BookDto> updateBook(@Valid @RequestBody BookDto bookDto, @PathVariable("bookId") Integer bookId) {
		BookDto updateBook = this.bookService.updateBook(bookDto, bookId);

		return ResponseEntity.ok(updateBook);

	}

	@DeleteMapping("/{bookId}")
	public ResponseEntity<?> deleteBook(@PathVariable("bookId") Integer bookId) {
		this.bookService.deleteBook(bookId);
		return new ResponseEntity(new ApiResponse("Book Deleted Successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<BookDto>>getAllBooks()
	{
		return ResponseEntity.ok(this.bookService.getAllBook());
	}
	
	@GetMapping("/{bookId}")
	public ResponseEntity<BookDto>getSingleBook(@PathVariable Integer bookId)
	{
		return ResponseEntity.ok(this.bookService.getBookById(bookId));
	}

}
