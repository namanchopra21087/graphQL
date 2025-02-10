package com.graphQL.naman.config;

import com.graphQL.naman.model.Book;
import com.graphQL.naman.repository.BookRepository;
import com.graphQL.naman.service.datafetchers.AllBooksDataFetcher;
import com.graphQL.naman.service.datafetchers.BookDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class GraphQLConfig {

  @Value("classpath:graphql/book.graphql")
  Resource resource;

  @Getter private GraphQL graphQL;
  private final AllBooksDataFetcher allBooksDataFetcher;
  private final BookRepository repository;
  private final BookDataFetcher bookDataFetcher;

  GraphQLConfig(
      AllBooksDataFetcher allBooksDataFetcher,
      BookRepository repository,
      BookDataFetcher bookDataFetcher) {
    this.allBooksDataFetcher = allBooksDataFetcher;
    this.repository = repository;
    this.bookDataFetcher = bookDataFetcher;
  }

  @Bean
  GraphQL loadSchema() throws IOException {
    // Load books into HSQL database
    loadDataIntoHSQL();
    File schemaFile = resource.getFile();
    TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
    RuntimeWiring wiring = buildRuntimeWiring();
    GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
    return GraphQL.newGraphQL(schema).build();
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
              repository.save(book);
            });
  }

  private RuntimeWiring buildRuntimeWiring() {
    return RuntimeWiring.newRuntimeWiring()
        .type(
            "Query",
            typeWiring ->
                typeWiring
                    .dataFetcher("allBooks", allBooksDataFetcher)
                    .dataFetcher("book", bookDataFetcher))
        .build();
  }
}
