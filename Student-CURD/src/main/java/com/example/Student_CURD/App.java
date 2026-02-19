package com.example.Student_CURD;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        StudentDAO dao = new StudentDAO();

        // INSERT
        Student s1 = new Student();
//        s1.setId(1);
        s1.setName("Hari");
        s1.setId(608);
        s1.setDept("cse");
        s1.setMarks(90);
        dao.saveStudent(s1);

        Student s2 = new Student();
//        s2.setId(2);
        s2.setName("Kiran");
        s2.setId(609);
        s2.setDept("ece");
        s2.setMarks(89);
        dao.saveStudent(s2);

        // UPDATE
//        Student s2 = new Student();
//        s2.setId(2);
//        s2.setName("kannan");
//        dao.updateStudent(s2);

        // DELETE
//        dao.deleteStudent(2);

        // READ (LIST)
        
        List<Student> list = dao.getAllStudents();
        for (Student s : list) {
            System.out.println(s.getId() + " " + s.getName()+" " + s.getDept()+" "+s.getMarks());
        }

        System.out.println("Operation completed");
    }
    }

