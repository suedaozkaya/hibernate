package com_hb01_annotations;

import javax.persistence.*;

//Bu annotationu koyduğumuz sınıf, veritabanında bir tabloya karşılık gelecek
@Entity
//Eğer oluşacak tablo ismini değiştirmek istenirse name attribute ile bir tablo ismi verilir
@Table(name = "t_student01")
public class Student01 {


    @Id //primary key oluşmasını sağlıyor.
    //@Column (name = "std_id")
    private int id;


    //lenght default=255
    //nullable default=true
    //unique default=false
    //Column annotationu zorunlu değil ancak (tablodaki ismi) customize edebilmek için gerekli
    @Column(name = "student_name",length = 100, nullable = false,unique = true)
    private String name;


    //veritabanı tablosunda grade adında bir column oluşturulmaz
    //örneğin bazı değerleri yalnızca kod içerisinde tutmak istiyoruz, veri tabanında olsun istemiyoruz
    //@Transient
    private int grade;


    //large object(lob) ile büyük boyutlu datalar tutulabilir.(image,file)
    //@Lob
    //private byte[] image;


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

    @Override
    public String toString() {
        return "Student01{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
