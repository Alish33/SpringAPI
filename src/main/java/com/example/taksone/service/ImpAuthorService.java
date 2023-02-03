package com.example.taksone.service;

import com.example.taksone.enitiy.Author;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public interface ImpAuthorService  {

    List<Author> getAuthors();
    Author addAuthor(Author author);
    String removeAuthor(int id, Author author);
    Author editAuthor(int id);
    Author updateAuthor(int id,Author author);

    HashMap<Integer, String> avgRating();

    HashMap<String, Double> avgBookPrice();
}
