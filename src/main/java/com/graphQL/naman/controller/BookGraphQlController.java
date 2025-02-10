package com.graphQL.naman.controller;

import com.graphQL.naman.model.Book;
import com.graphQL.naman.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookGraphQlController {

  private final BookService bookService;

  BookGraphQlController(BookService bookService) {
    this.bookService = bookService;
  }

  @QueryMapping
  public List<Book> getAllBooks() {
    return bookService.getAllBooks();
  }

  @QueryMapping
  public Book getBook(@Argument String id) {
    return bookService.getBook(id);
  }
}
