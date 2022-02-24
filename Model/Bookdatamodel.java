package com.Springboot_web_rest.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="book_data")
public class Bookdatamodel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String book_name,author_name;
    @OneToMany
    @JoinColumn(name="book_id")
    List<Book_historymodel> book_details;

    public List<Book_historymodel> getBook_details() {
        return book_details;
    }

    public void setBook_details(List<Book_historymodel> book_details) {
        this.book_details = book_details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

}
