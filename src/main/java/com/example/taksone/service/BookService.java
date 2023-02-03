package com.example.taksone.service;

import com.example.taksone.enitiy.Book;
import com.example.taksone.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class BookService implements ImpBookService {

    @Autowired
    public BookRepository bookRepository;

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    @Override
    public String removeBook(int id) {
        bookRepository.deleteById(id);
        return "Book of id "+id+" is removed";
    }

    @Override
    public Book find_book_by_id(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book updateBook(int id , Book book) {
        Book oldBook = bookRepository.findById(id).orElse(null);
        oldBook.setBook_name(book.getBook_name());
        oldBook.setBook_rating(book.getBook_rating());
        oldBook.setPrice(book.getPrice());
        oldBook.setISBN(book.getISBN());
        return bookRepository.save(oldBook);
    }
}
