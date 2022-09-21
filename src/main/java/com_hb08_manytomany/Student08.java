package com_hb08_manytomany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Student08 {

    @Id
    private int id;

    @Column(name="student_name",nullable = false)
    private String name;

    private int grade;



    @ManyToMany
    @JoinTable(
            name = "student08_book08",
            joinColumns = { @JoinColumn(name = "std_id") },
            inverseJoinColumns = { @JoinColumn(name = "book_id") }
    )
    // join column -> içinde bulunduğumuz sınıfın tablosundan
    // inverse join column -> list i tanımladığımız sınıfın tablosundan gelecek
    // ve bir join table oluşturacak
    private List<Book08> bookList=new ArrayList<>();



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }


    public List<Book08> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book08> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Student08 [id=" + id + ", name=" + name + ", grade=" + grade + ", bookList=" + bookList + "]";
    }


}

