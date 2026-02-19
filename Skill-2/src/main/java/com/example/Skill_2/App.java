package com.example.Skill_2;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class App {

    public static void main(String[] args) {

        // ---------- CRUD ----------
        insertProduct("Laptop", "Gaming Laptop", 65000, 10);
        insertProduct("Mouse", "Wireless Mouse", 600, 50);
        getProductById(1);
        updateProduct(1, 70000, 8);
        deleteProduct(2);

        // ---------- HQL TASKS ----------
        // addSampleProducts(); // ⚠️ Run ONLY once

        sortByPriceAsc();
        sortByPriceDesc();
        sortByQuantityDesc();
        firstThreeProducts();
        nextThreeProducts();
        countProducts();
        countInStock();
        minMaxPrice();
        
    }

    // ======================= CRUD METHODS =======================

    public static void insertProduct(String name, String desc, double price, int qty) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.save(new Product(name, desc, price, qty));
        tx.commit();
        s.close();
        System.out.println("✔ INSERTED: " + name);
    }

    public static void getProductById(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Product p = s.get(Product.class, id);
        if (p != null)
            System.out.println("📌 Product Found: " + p.getName() + " | ₹" + p.getPrice());
        else
            System.out.println("❌ Product ID Not Found: " + id);
        s.close();
    }

    public static void updateProduct(int id, double price, int qty) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        Product p = s.get(Product.class, id);
        if (p != null) {
            p.setPrice(price);
            p.setQuantity(qty);
            s.update(p);
            tx.commit();
            System.out.println("🔄 UPDATED: " + p.getName());
        }
        s.close();
    }

    public static void deleteProduct(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        Product p = s.get(Product.class, id);
        if (p != null) {
            s.delete(p);
            tx.commit();
            System.out.println("🗑️ DELETED: " + p.getName());
        }
        s.close();
    }

    // ======================= HQL METHODS =======================

    public static void addSampleProducts() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.save(new Product("Keyboard", "Wireless Keyboard", 1500, 20));
        s.save(new Product("Monitor", "LED Monitor", 8000, 5));
        s.save(new Product("Speaker", "Bluetooth Speaker", 1200, 15));
        s.save(new Product("USB Cable", "Type-C Cable", 150, 100));
        s.save(new Product("Webcam", "HD Webcam", 2500, 8));
        s.save(new Product("Printer", "Laser Printer", 15000, 2));
        tx.commit();
        s.close();
        System.out.println(" Sample Products Added!");
    }

    public static void sortByPriceAsc() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        System.out.println("\n PRICE ASCENDING ORDER");
        List<Product> list = s.createQuery("FROM Product ORDER BY price ASC", Product.class).list();
        list.forEach(p -> System.out.println(p.getName() + " - ₹" + p.getPrice()));
        s.close();
    }

    public static void sortByPriceDesc() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        System.out.println("\n PRICE DESCENDING ORDER");
        List<Product> list = s.createQuery("FROM Product ORDER BY price DESC", Product.class).list();
        list.forEach(p -> System.out.println(p.getName() + " - ₹" + p.getPrice()));
        s.close();
    }

    public static void sortByQuantityDesc() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        System.out.println("\n QUANTITY DESCENDING ORDER");
        List<Product> list = s.createQuery("FROM Product ORDER BY quantity DESC", Product.class).list();
        list.forEach(p -> System.out.println(p.getName() + " - Qty: " + p.getQuantity()));
        s.close();
    }

    public static void firstThreeProducts() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        System.out.println("\n FIRST 3 PRODUCTS");
        List<Product> list = s.createQuery("FROM Product", Product.class)
                .setFirstResult(0).setMaxResults(3).list();
        list.forEach(p -> System.out.println(p.getName() + " - ₹" + p.getPrice()));
        s.close();
    }

    public static void nextThreeProducts() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        System.out.println("\n NEXT 3 PRODUCTS");
        List<Product> list = s.createQuery("FROM Product", Product.class)
                .setFirstResult(3).setMaxResults(3).list();
        list.forEach(p -> System.out.println(p.getName() + " - ₹" + p.getPrice()));
        s.close();
    }

    public static void countProducts() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Long c = (Long) s.createQuery("SELECT COUNT(*) FROM Product").uniqueResult();
        System.out.println("\n TOTAL PRODUCTS: " + c);
        s.close();
    }

    public static void countInStock() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Long c = (Long) s.createQuery("SELECT COUNT(*) FROM Product WHERE quantity > 0").uniqueResult();
        System.out.println(" IN STOCK PRODUCTS: " + c);
        s.close();
    }

    public static void minMaxPrice() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Object[] v = (Object[]) s.createQuery("SELECT MIN(price), MAX(price) FROM Product").uniqueResult();
        System.out.println("\n MIN PRICE: ₹" + v[0] + " | MAX PRICE: ₹" + v[1]);
        s.close();
    }

    /*public static void nameStartsWith(String x) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        System.out.println("\n NAME STARTS WITH: " + x);
        List<Product> list = s.createQuery("FROM Product WHERE name LIKE :x", Product.class)
                .setParameter("x", x + "%").list();
        list.forEach(p -> System.out.println(p.getName()));
        s.close();
    }

    public static void nameEndsWith(String x) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        System.out.println("\n NAME ENDS WITH: " + x);
        List<Product> list = s.createQuery("FROM Product WHERE name LIKE :x", Product.class)
                .setParameter("x", "%" + x).list();
        list.forEach(p -> System.out.println(p.getName()));
        s.close();
    }

    public static void nameContains(String x) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        System.out.println("\n🔍 NAME CONTAINS: " + x);
        List<Product> list = s.createQuery("FROM Product WHERE name LIKE :x", Product.class)
                .setParameter("x", "%" + x + "%").list();
        list.forEach(p -> System.out.println(p.getName()));
        s.close();
    }

    public static void nameExactLength(int len) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        System.out.println("\n🔢 NAME LENGTH = " + len);
        List<Product> list = s.createQuery("FROM Product WHERE LENGTH(name) = :l", Product.class)
                .setParameter("l", len).list();
        list.forEach(p -> System.out.println(p.getName()));
        s.close();
    }*/
}
