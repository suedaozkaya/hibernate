package com.hb01.annotations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class RunnerFetch01_hqlQuery {
    public static void main(String[] args) {
        Configuration con = new Configuration().
                configure("hibernate.cfg.xml").addAnnotatedClass(Student01.class);

        SessionFactory sf = con.buildSessionFactory();

        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();

/*
        //SQL Örneği
        //1.yol:
        String sqlQuery1 = "SELECT * FROM t_student01";
        List<Object[]> resultList1 = session.createSQLQuery(sqlQuery1).getResultList();

        //2.yol:
        Query query1 = session.createQuery(sqlQuery1);
        List<Object[]> resultList2 = query1.getResultList();

        for (Object[] objects : resultList1) {
            System.out.println(Arrays.toString(objects));
        }
*/



/*
		//HQL Örneği--SQL sorgusunda FROM'dan sonra sınıf ismi kullanılmalı
		//kullanılmazsa student01 is not mapped hatası alınır.

		String hqlQuery1="FROM Student01";
		List<Student01> resultList3=session.createQuery(hqlQuery1,Student01.class).getResultList();

		for (Student01 student01 : resultList3) {
			System.out.println(student01);
		}
*/



/*
		//Dönecek kaydın unique bir kayıt olduğuna emin iseniz uniqueResult() methodu kullanılabilir.

		String sqlQuery2 = "SELECT * FROM t_student01 WHERE student_name = 'John Coffee'";
		Object[] uniqueResult1 = (Object[])session.createSQLQuery(sqlQuery2).uniqueResult();

		//System.out.println(Arrays.toString(uniqueResult1));

		System.out.println(uniqueResult1[0] + ":" + uniqueResult1[1] + ":" + uniqueResult1[2]);
*/



/*
		//HQL ile unique result sorgulama:

		String hqlQuery2 = "FROM Student01 WHERE name = 'John Coffee'";
		Student01 uniqueResult2 =  session.createQuery(hqlQuery2,Student01.class).uniqueResult();
		System.out.println(uniqueResult2);
*/



/*
		//HQL ile Alias kullanma örneği:

		String hqlQuery2 = "FROM Student01 std WHERE std.name = 'John Coffee'";
		Student01 uniqueResult2 =  session.createQuery(hqlQuery2,Student01.class).uniqueResult();
		System.out.println(uniqueResult2);
*/



/*
		//HQL ile belirli değişkenleri alma:
		//grade değeri 10 olan öğrencileri getir.

		String hqlQuery3 = "SELECT s.id,s.name FROM Student01 s WHERE s.grade=10";
		List<Object[]> resultList4 = session.createQuery(hqlQuery3).getResultList();

		for(Object[] objects : resultList4) {
			System.out.println(Arrays.toString(objects));
		}
*/


        String hqlQuery4 = "FROM Student01 s ORDER BY s.id DESC";
        List<Student01> resultList5 = session.createQuery(hqlQuery4,Student01.class).getResultList();

        for(Student01 student01 : resultList5) {
            System.out.println(student01);
        }


        tx.commit();
        session.close();
        sf.close();
    }
}
