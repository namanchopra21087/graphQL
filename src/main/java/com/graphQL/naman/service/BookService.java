package com.graphQL.naman.service;

import com.graphQL.naman.model.Book;
import com.graphQL.naman.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class BookService {
  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @PostConstruct
  void loadSchemaHsql() throws IOException {
    loadDataIntoHSQL();
  }

  private void loadDataIntoHSQL() {
    Stream.of(
            new Book("1", "Java OOPS", "TataMcGraHill", new String[] {"JK Rowling"}, "Nov 2020"),
            new Book(
                "2", "Java Multi-Threading", "TataMcGraHill", new String[] {"Edureka"}, "Feb 2017"),
            new Book(
                "3", "From Heavens", "TataMcGraHill", new String[] {"Simon Hatch"}, "Jan 2018"),
            new Book(
                "4", "Angel Investor", "TataMcGraHill", new String[] {"Joe Mcain"}, "Apr 2019"),
            new Book(
                "5",
                "ServerLess Programming",
                "TataMcGraHill",
                new String[] {"William Street"},
                "May 2012"))
        .forEach(
            book -> {
              bookRepository.save(book);
            });
  }

  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  public Book getBook(String id) {
    return bookRepository.findById(id).orElse(null);
  }
}
