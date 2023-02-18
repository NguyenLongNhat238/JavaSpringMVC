/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.java.hibernatedemo;

import com.java.pojo.Category;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author admin
 */
public class HibernateDemo {

    public static void main(String[] args) {
        try ( Session s = HibernateUtils.getFactory().openSession()) {
            Query q = s.createQuery("From Category");
            List<Category> cates = q.getResultList();
            cates.forEach(c -> System.out.println(c.getName()));
        }
    }
}
