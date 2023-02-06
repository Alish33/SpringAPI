package com.example.taksone.controller;

import com.example.taksone.enitiy.Book;
import com.example.taksone.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    public BookService bookService;

    @GetMapping("/books")
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @PostMapping("/addBook")
    public Book addNewBooks(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @GetMapping("/find_books/{id}")
    public Book find_book(@PathVariable int id){
        return bookService.find_book_by_id(id);
    }

    @DeleteMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable int id){
        return bookService.removeBook(id);
    }

    @PutMapping("/updateBook/{id}")
    public Book update_book(@PathVariable("id") int id,@RequestBody Book book){
        return bookService.updateBook(id , book);
    }

    @GetMapping("/book-pagination")
    public ResponseEntity<?> findBookByPage(@RequestParam("page_no") int page_no , @RequestParam("page_size") int page_size){
        return new ResponseEntity<>(bookService.findAllByPageSortByPrice(page_no , page_size), HttpStatus.OK);
    }

}
