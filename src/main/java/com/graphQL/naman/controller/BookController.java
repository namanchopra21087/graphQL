package com.graphQL.naman.controller;

import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/book")
@RestController
public class BookController {

  private final GraphQL graphQL;

  BookController(GraphQL graphQL) {
    this.graphQL = graphQL;
  }

  @PostMapping
  public ResponseEntity<Object> getAllBooks(@RequestBody String query) {
    ExecutionResult response = graphQL.execute(query);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
