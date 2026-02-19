package com.example.demo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Session s= HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        
        /*Students s1 =new Students();
        s1.setId(608);
        s1.setName("Hari");
        s1.setDept("cse");
        s1.setMarks(80);
        s.save(s1);
        
        
        Students s2 =new Students();
        s2.setId(371);
        s2.setName("Ktt");
        s2.setDept("cs");
        s2.setMarks(90);
        s.save(s2);
        
        
        Students s3 =new Students();
        s3.setId(106);
        s3.setName("Gopal");
        s3.setDept("ece");
        s3.setMarks(95);
        s.save(s3);
        
        System.out.println("Insert success");
        t.commit();
        s.close();*/
        
//HQL
        
        Query<Students> q1=s.createQuery("FROM Students WHERE dept=:dept",Students.class);
        q1.setParameter("dept", "cse");
        
        List<Students> list=q1.list();
        
        System.out.println("CSE dept students are:");
        
        for(Students s1: list)
          System.out.println(s1.getName());
        
      //HCQL
        Criteria c=s.createCriteria(Students.class);
        c.add(Restrictions.gt("marks", 80));
        
        List<Students> list1=c.list();
        System.out.println("students who greater than 80 marks are:");
        
        for(Students s2:list1)
          
        System.out.println(s2.getName());
        
        
        
        
        		
        
    }
}
