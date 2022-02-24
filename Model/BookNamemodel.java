package com.Springboot_web_rest.Model;

import javax.persistence.*;

@Entity
@Table(name="bookname_table")
public class BookNamemodel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String book_name;
    @Column(insertable = false,updatable = false)

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

}
