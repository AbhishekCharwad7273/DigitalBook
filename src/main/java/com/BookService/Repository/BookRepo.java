package com.BookService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BookService.Entities.Book;

public interface BookRepo extends JpaRepository<Book, Integer>{

}
