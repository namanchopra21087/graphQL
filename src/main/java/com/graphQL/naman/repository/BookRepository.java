package com.graphQL.naman.repository;

import com.graphQL.naman.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {}
