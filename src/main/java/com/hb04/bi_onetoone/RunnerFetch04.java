package com.hb04.bi_onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerFetch04 {

    public static void main(String[] args) {

        Configuration con = new Configuration().configure("hibernate.cfg.xml").
                addAnnotatedClass(Student04.class).addAnnotatedClass(Diary02.class);

        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();

        //student fetch ediyoruz bir id ile
        Student04 student = session.get(Student04.class, 1001);

        // diary fetch ediyoruz
        //Diary02 diary = session.get(Diary02.class, 101);

/*
        System.out.println(student);
        STACKOVERFLOW
        bu kod (1) student'ın toString'ini çağırıyor,
        (2)orada diary olduğundan diary'nin toString'ini çağırıyor
        (3)orada student olduğundan yine student'ın toString'ini çağırıyor.
        bu şekilde bir infinite loop'a giriyor
        her bir main method'un bir thread'i var, thread'in de bir stack memory'si var
        stack memory'ye çağırılacak methodların referanslarını koyuluyor.
        infinite loop'a girdiğinde method referansları birikiyor ve stack'im yetmez diyor

        //çözüm olarak student'ın toString'indeki diary'yi sildik.
*/


        System.out.println("---------------------");
        //System.out.println(student.getDiary());
        System.out.println("---------------------");
        //System.out.println(diary);
        System.out.println("---------------------");
        //System.out.println(diary.getStudent());
        System.out.println("---------------------");


        /* Hql ile arka planda hibernate tarafından oluşturulan sql:
         * select student04x0_.std_name as col_0_0_,
         * diary02x1_.name as col_1_0_ from
         * Student04 student04x0_ inner join Diary02 diary02x1_ on (
         * student04x0_.id=diary02x1_.std_id )
         *
         * Bizim yazdığımız sql:
         * select s.std_name,d.name from student04 s inner join diary02 d on s.id=d.std_id;
         *
         */



/*
        // SQL: SELECT s.std_name,d.name FROM student04 s INNER JOIN diary02 d ON s.id = d.std_id;
        // SQL'de d.std_id, HQL'de d.student olmasına dikkat! HQL'de referansı verdik.
		String hqlQuery1 = "SELECT s.name,d.name FROM Student04 s INNER JOIN FETCH Diary02 d ON s.id = d.student";
		List<Object[]> resultList1 = session.createQuery(hqlQuery1).getResultList();

        // 1.yol (for each ile):
		for(Object[] objects : resultList1) {
			System.out.println(Arrays.toString(objects));
		}

        // 2.yol (lambda expression ile):
		resultList1.forEach(oa->{
			System.out.println(Arrays.toString(oa));
		});
*/



/*
        // LEFT JOIN (HQL)
		String hqlQuery2 = "SELECT s.name,d.name FROM Student04 s LEFT JOIN FETCH Diary02 d ON s.id = d.student";
		List<Object[]> resultList2 = session.createQuery(hqlQuery2).getResultList();

		for(Object[] objects : resultList2) {
			System.out.println(Arrays.toString(objects));
		}
*/



/*
        // RIGHT JOIN (HQL)
		String hqlQuery3 = "SELECT s.name,d.name FROM Student04 s RIGHT JOIN FETCH Diary02 d ON s.id = d.student";
		List<Object[]> resultList3 = session.createQuery(hqlQuery3).getResultList();

		resultList3.forEach(oa->{
			System.out.println(Arrays.toString(oa));
		}
*/



/*
        // FULL JOIN (HQL)
		String hqlQuery4 = "SELECT s.name,d.name FROM Student04 s FULL JOIN FETCH Diary02 d ON s.id = d.student";
		List<Object[]> resultList4 = session.createQuery(hqlQuery4).getResultList();

		for(Object[] objects : resultList4) {
			System.out.println(Arrays.toString(objects));
		}
*/



/*
		String hqlQuery5 = "FROM Student04 s INNER JOIN FETCH s.diary";
		List<Student04> resultList5 = session.createQuery(hqlQuery5,Student04.class).getResultList();

		for (Student04 student04 : resultList5) {
            System.out.println(student04);

        }
 */



/*
        String hqlQuery6="FROM Diary02 d INNER JOIN FETCH d.student";
        List<Diary02> resultList6= session.createQuery(hqlQuery6,Diary02.class).getResultList();

        resultList6.forEach(d->System.out.println(d));
*/



        tx.commit();
        session.close();
        sf.close();
    }
}

