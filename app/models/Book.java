package models;

import io.ebean.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book extends Model {

    @Id
    public Integer id;
    public String title;
    public Integer price;
    public String author;

    public static Finder<Integer, Book> find = new Finder<>(Book.class);

//    public Book() {}
//
//    // Getters and Setters
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public int getPrice() {
//        return price;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    // Methods for purpose of tutorial
//
//    public Book (int id, String title, int price, String author) {
//        this.id = id;
//        this.title = title;
//        this.price = price;
//        this.author = author;
//    }
//
//    private static Set<Book> books;
//
//    static {
//        books = new HashSet<>();
//        books.add(new Book(1, "C++", 36, "ABC"));
//        books.add(new Book(2, "Java", 56, "XYZ"));
//    }
//
//    public static Set<Book> allBooks() {
//        return books;
//    }
//
//    public static Book findById(Integer id) {
//        for (Book book : books)
//            if (id.equals(book.id))
//                return book;
//
//        return null;
//    }
//
//    public static void add(Book book) {
//        books.add(book);
//    }
//
//    public static boolean remove(Book book) {
//        return books.remove(book);
//    }
}
