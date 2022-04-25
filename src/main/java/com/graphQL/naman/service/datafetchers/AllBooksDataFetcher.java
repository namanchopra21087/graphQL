package com.graphQL.naman.service.datafetchers;

import com.graphQL.naman.model.Book;
import com.graphQL.naman.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllBooksDataFetcher implements DataFetcher<List<Book>> {
    @Autowired
    BookRepository repository;

    @Override
    public List<Book> get(DataFetchingEnvironment environment) throws Exception {
        return repository.findAll();
    }
}
