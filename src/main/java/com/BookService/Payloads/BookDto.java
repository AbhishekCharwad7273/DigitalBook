package com.BookService.Payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookDto {

	
	
	private int bookId;
	
	@NotEmpty
	@Size(min=4,message="Boook Category Minimum of 4 Charcter")
	private String bookCategory;
	
	@NotEmpty(message="book price is not valid")
	private int bookPrice;
	
	@NotEmpty
	private String bookPublisher;
	
	@NotEmpty
	private String bookActive;
	
	@NotEmpty
	private String bookContent;
}
