package com.Springboot_web_rest.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="student_table")
public class Studentmodel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer student_id;
    String name,email,password;
    @Column(insertable = false,updatable = false)
    String token;
    String profile_image;
    String role;

    @OneToMany
    @JoinColumn(name="student_id")
    List<Book_historymodel> book_history;

    @ManyToMany
    @JoinTable(
            name = "author_table",
            joinColumns = @JoinColumn(name= "student_id"),
            inverseJoinColumns = @JoinColumn(name="book_id"))
    List<BookNamemodel> bookNames;

    public List<BookNamemodel> getBookNames() {
        return bookNames;
    }

    public void setBookNames(List<BookNamemodel> bookNames) {
        this.bookNames = bookNames;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Book_historymodel> getBook_history() {
        return book_history;
    }

    public void setBook_history(List<Book_historymodel> book_history) {
        this.book_history = book_history;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

}
