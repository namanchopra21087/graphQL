package com.graphQL.naman.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Book {

  @Id private String isn;
  private String title;
  private String publisher;
  private String[] authors;
  private String publishedDate;
}
