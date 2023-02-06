package com.example.taksone.service;

import com.example.taksone.enitiy.Author;
import com.example.taksone.enitiy.Book;
import com.example.taksone.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import com.example.taksone.customException.CustomException;

import java.util.*;

@Service
public class AuthorService implements ImpAuthorService{

//    @Autowired
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author addAuthor(Author author){
        return authorRepository.save(author);
    }

    public String removeAuthor(int id, Author author){
        Author author1 = authorRepository.findById(id).get();
        authorRepository.deleteById(id);
        return "Author named "+author1.getFirst_name()+" of "+id+" is removed";
    }

    public Author editAuthor(int id){
        return authorRepository.findById(id).orElse(null);
    }

    public Author updateAuthor(int id, Author author) throws CustomException {

        Author newAuthor = null;
        Optional<Author> oldAuthor = authorRepository.findById(id);

        if(oldAuthor.isPresent()){
            author.setAuthor_id(id);
            newAuthor = authorRepository.save(author);
        }else{
            throw new CustomException("Sorry ! Author is not found.");
        }

        return newAuthor;

    }

    @Override
    public HashMap<Integer, String> avgRating() throws CustomException {
        HashMap<Integer,String> book_rating_map = new HashMap<>();
        List<Author> authors = authorRepository.findAll();

        for (int i = 0; i < authors.size(); i++) {
            double total_rating = 0;
            List<Book> books = authors.get(i).getBooks();
            for (Book book: books
                 ) {
                total_rating+=book.getBook_rating();
            }
            double average_rating_value = 0;
            try{
                average_rating_value = total_rating/books.size();
            }catch (Exception  e){
                    throw new CustomException("There is no book for such author. So no average.");
            }
            book_rating_map.put(authors.get(i).getAuthor_id(), authors.get(i).getFirst_name()+" "+average_rating_value);
        }

        return book_rating_map;
    }

    @Override
    public HashMap<String, Double> avgBookPrice() throws CustomException {

        HashMap<String, Double> avg_book_price_hash = new HashMap<>();
        HashMap<String, Double> sorted_hashmap = new HashMap<>();

        List<Author> author_list = authorRepository.findAll();


        for (int i = 0; i < author_list.size(); i++) {
            double total_book_price = 0;
            List<Book> author_book= author_list.get(i).getBooks();
            for (Book book: author_book
                 ) {
                total_book_price +=book.getPrice();
            }
            double average_book_price = 0;
            try{
                average_book_price = total_book_price/author_book.size();
            }catch(Exception ae){
                    throw new CustomException("The author has zero book");
            }


            avg_book_price_hash.put(author_list.get(i).getAuthor_id()+"", average_book_price);

        }

        List<Map.Entry<String, Double>> list = new LinkedList<>(avg_book_price_hash.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
                    public int compare(Map.Entry<String, Double> o1,
                                       Map.Entry<String, Double> o2) {
                        return (o1.getValue()).compareTo(o2.getValue());
                    }
                });

            HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
        for (Map.Entry<String,Double> a: list
             ) {
            temp.put("Average book price of author having Id "+a.getKey()+" = ", a.getValue());
        }

        return temp;
        }

}
