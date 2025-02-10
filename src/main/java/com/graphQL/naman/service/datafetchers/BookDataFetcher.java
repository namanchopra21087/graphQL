package com.graphQL.naman.service.datafetchers;

import com.graphQL.naman.model.Book;
import com.graphQL.naman.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookDataFetcher implements DataFetcher<Book> {
  @Autowired BookRepository repository;

  @Override
  public Book get(DataFetchingEnvironment environment) throws Exception {
    String isn = environment.getArgument("id");
    return repository.findById(isn).get();
  }
}
