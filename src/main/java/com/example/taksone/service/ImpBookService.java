package com.example.taksone.service;

import com.example.taksone.customException.CustomException;
import com.example.taksone.enitiy.Book;

import java.util.List;

public interface ImpBookService {

    List<Book> getBooks();
    Book addBook(Book book);

    String removeBook(int id);

    Book updateBook(int id ,Book book);

    Book find_book_by_id(int id);


    List<Book> findAllByPageSortByPrice(int page_no , int page_size) throws CustomException;

}
