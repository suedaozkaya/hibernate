package com.hb09.fetchTypes;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerFetch09 {

	public static void main(String[] args) {
/*
		//getStudent metodunu test ettik (bu methodu aşağıda biz oluşturduk)
		//eager olduğunda çalıştı; ancak lazy olduğunda session'ı methodda
		//kapattığımız için hata verdi
		Student09 std1= getStudent(1001);
		
		 List<Book09> bookList = std1.getBookList();
		
		 for (Book09 book09 : bookList) {
			System.out.println(book09);
		}

*/


		Configuration con=new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Student09.class).addAnnotatedClass(Book09.class);
		
		SessionFactory sf= con.buildSessionFactory();
		Session session= sf.openSession();
		
		Transaction tx = session.beginTransaction();


//	    Student09 student= session.get(Student09.class,1002);
//	    session.delete(student);


		// hangi taraftan çağırırsak ona göre davranıyor
		// örneğin one -> student, many -> book iken default olarak;
		// Student09 student= session.get(Student09.class,1002); LAZY
		// Book09 book1= session.get(Book09.class,101); EAGER çalışır


		// Student09 student1= session.get(Student09.class,1002);
	/*	LAZY (student için default)
	 * select
        student09x0_.id as id1_1_0_,
        student09x0_.grade as grade2_1_0_,
        student09x0_.student_name as student_3_1_0_ 
    from
        Student09 student09x0_ 
    where
        student09x0_.id=?*/


		/* EAGER
		 * Hibernate: 
    select
        student09x0_.id as id1_1_0_,
        student09x0_.grade as grade2_1_0_,
        student09x0_.student_name as student_3_1_0_,
        booklist1_.student_id as student_3_0_1_,
        booklist1_.id as id1_0_1_,
        booklist1_.id as id1_0_2_,
        booklist1_.name as name2_0_2_,
        booklist1_.student_id as student_3_0_2_ 
    from
        Student09 student09x0_ 
    left outer join
        Book09 booklist1_ 
            on student09x0_.id=booklist1_.student_id 
    where
        student09x0_.id=?
		 */



		//	Book09 book1= session.get(Book09.class,101);
		/*
		 * Hibernate: EAGER MANY-TO-ONE BOOK FETCH:
    select
        book09x0_.id as id1_0_0_,
        book09x0_.name as name2_0_0_,
        book09x0_.student_id as student_3_0_0_,
        student09x1_.id as id1_1_1_,
        student09x1_.grade as grade2_1_1_,
        student09x1_.student_name as student_3_1_1_ 
    from
        Book09 book09x0_ 
    left outer join
        Student09 student09x1_ 
            on book09x0_.student_id=student09x1_.id 
    where
        book09x0_.id=?

*
    * Hibernate: // manuel olarak LAZY FETCH yaptığımızda:
    select
        booklist0_.student_id as student_3_0_0_,
        booklist0_.id as id1_0_0_,
        booklist0_.id as id1_0_1_,
        booklist0_.name as name2_0_1_,
        booklist0_.student_id as student_3_0_1_ 
    from
        Book09 booklist0_ 
    where
        booklist0_.student_id=?
		 */


/*
		Student09 student= session.get(Student09.class,1001);
		 
		for (Book09 book : student.getBookList()) {
			System.out.println(book);
		}
*/
		//List<Book09> bookList = student.getBookList();
		//Lazy olduğu için bu kod satırına bir query oluşturmadı bile
		 


		 //-----JOIN VS JOIN FETCH-----

		String hqlQuery1="SELECT s FROM Student09 s JOIN s.bookList";
		session.createQuery(hqlQuery1).getResultList();
		 /*
		  * Hibernate: JOIN QUERY
    select
        student09x0_.id as id1_1_,
        student09x0_.grade as grade2_1_,
        student09x0_.student_name as student_3_1_ 
    from
        Student09 student09x0_ 
    inner join
        Book09 booklist1_ 
            on student09x0_.id=booklist1_.student_id
		  */


		String hqlQuery2="SELECT s FROM Student09 s JOIN FETCH s.bookList";
		session.createQuery(hqlQuery2).getResultList();
		/*
		 * Hibernate: JOIN FETCH QUERY
    select
        student09x0_.id as id1_1_0_,
        booklist1_.id as id1_0_1_,
        student09x0_.grade as grade2_1_0_,
        student09x0_.student_name as student_3_1_0_,
        booklist1_.name as name2_0_1_,
        booklist1_.student_id as student_3_0_1_,
        booklist1_.student_id as student_3_0_0__,
        booklist1_.id as id1_0_0__ 
    from
        Student09 student09x0_ 
    inner join
        Book09 booklist1_ 
            on student09x0_.id=booklist1_.student_id
		 */

		// NOT: JOIN FETCH yapınca fetch type'ı lazy olmasına rağmen eager gibi davranıyor

		
		tx.commit();
		session.close();

/*
		//FETCH TYPE LAZY ise aşağıdaki code, session close olduktan sonra LazyInitializationException throw eder.
		 for (Book09 book : student.getBookList()) {
			System.out.println(book);
		}
*/
		
/*
		//FETCH TYPE EAGER ise aşağıdaki code, session close olduktan sonra da problemsiz çalışır
		 * çünkü student nesnesi ilişkisiyle beraber bir seferde yüklenir.
		 * (Student09 student= session.get(Student09.class,1001); bu satırda)
		 for (Book09 book : student.getBookList()) {
				System.out.println(book);
		 }
*/

        sf.close();
	}


	private static Student09 getStudent(int id) {
		Configuration con=new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Student09.class).addAnnotatedClass(Book09.class);
		
		SessionFactory sf= con.buildSessionFactory();
		Session session= sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Student09 student= session.get(Student09.class,id);
		
		
		tx.commit();
		session.close();
		sf.close();
		
		return student;
	}

}
