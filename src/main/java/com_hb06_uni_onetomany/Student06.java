package com_hb06_uni_onetomany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Student06 {

    @Id
    private int id;
    @Column(name="student_name",nullable = false)
    private String name;

    private int grade;

    @OneToMany
    @JoinColumn // yazmazsak hibernate join table oluşturarak çalışıyor (student06_book06 isminde)
    private List<Book06> bookList=new ArrayList<>();

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


    public List<Book06> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book06> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Student06 [id=" + id + ", name=" + name + ", grade=" + grade + ", bookList=" + bookList + "]";
    }


}
