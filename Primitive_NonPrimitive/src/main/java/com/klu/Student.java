package com.klu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Student {
   @Value("101")
   private int id;
   
   @Value("7893231071")
   private long phone;
   
   @Value("89.5")
   private float marks;
   
   @Autowired
   private Course c;
   
   public void display()
   {
	   System.out.println("Student id: "+id);
	   System.out.println("Student phone: "+phone);
	   System.out.println("Student marks: "+marks);
	   System.out.println("Course: "+ c.getCourseName());
	   
   }
   
   
}
