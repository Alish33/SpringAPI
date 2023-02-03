package com.example.taksone.enitiy;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Book {
//    For making id as the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer book_id;
    private String ISBN;
    private String book_name;
    private String category;
    private Integer book_rating;
    private Integer price;

    @ManyToMany(mappedBy = "books")
    private List<Author> authors;

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getBook_rating() {
        return book_rating;
    }

    public void setBook_rating(Integer book_rating) {
        this.book_rating = book_rating;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
