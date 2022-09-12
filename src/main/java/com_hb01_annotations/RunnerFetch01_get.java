package com_hb01_annotations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerFetch01_get {
    public static void main(String[] args) {
        Configuration con = new Configuration().
                configure("hibernate.cfg.xml").addAnnotatedClass(Student01.class);

        SessionFactory sf = con.buildSessionFactory();

        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();

        Student01 student1 = session.get(Student01.class,1001);
        Student01 student2 = session.get(Student01.class,1002);
        Student01 student3 = session.get(Student01.class,1003);

        System.out.println(student1);
        System.out.println(student2);
        System.out.println(student3);

        tx.commit();
        session.close();
        sf.close();
    }
}
