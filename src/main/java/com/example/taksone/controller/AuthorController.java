package com.example.taksone.controller;

import com.example.taksone.customException.CustomException;
import com.example.taksone.enitiy.Author;
import com.example.taksone.repository.AuthorRepository;
import com.example.taksone.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/displayAuthors")
    public List<Author> getAuthors(){
        return authorService.getAuthors();
    }

    @PostMapping("/putAuthors")
    public Author addAuthor(@RequestBody Author author){
        return authorService.addAuthor(author);
    }

    @DeleteMapping("/deleteAuthor/{id}")
    public ResponseEntity<?> removeAuthor(@PathVariable int id, @RequestBody Author author ){
        return new ResponseEntity<>(authorService.removeAuthor(id, author), HttpStatus.OK);
    }

    @GetMapping("/find_author/{id}")
    public Author editAuthor(@PathVariable int id){
        return authorService.editAuthor(id);
    }

    @PutMapping("/updateAuthor/{id}")
    public Author updateAuthor(@PathVariable("id") int id, @RequestBody Author author) throws CustomException {
        return authorService.updateAuthor(id,author);
    }

    @GetMapping("/averageRating")
    public ResponseEntity<?> fetchAvgRating () throws CustomException {
        return new ResponseEntity<>(authorService.avgRating(), HttpStatus.OK);
    }

    @GetMapping("/averagePrice")
    public ResponseEntity<?> fetchAverageBookPrice() throws CustomException {
        return new ResponseEntity<>(authorService.avgBookPrice(), HttpStatus.OK);
    }


}
