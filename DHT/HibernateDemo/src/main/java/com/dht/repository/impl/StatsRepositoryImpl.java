/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.repository.impl;

import com.dht.hibernatedemo.HibernateUtils;
import com.dht.pojo.Category;
import com.dht.pojo.OrderDetail;
import com.dht.pojo.Product;
import java.util.List;
import com.dht.repository.StatsRepository;
import java.util.Date;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

/**
 *
 * @author admin
 */
public class StatsRepositoryImpl implements StatsRepository {

    @Override
    public List<Object[]> statsCategory() {
        try ( Session session = HibernateUtils.getFactory().openSession()) {
            CriteriaBuilder b = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

            Root rootP = q.from(Product.class);
            Root rootC = q.from(Category.class);

            q.where(b.equal(rootP.get("categoryId"), rootC.get("id")));

            q.multiselect(rootC.get("id"), rootC.get("name"), b.count(rootP.get("id")));
            q.groupBy(rootC.get("id"));

            Query query = session.createQuery(q);
            return query.getResultList();
        }
    }

    @Override
    public List<Object[]> statsRevenue(Date fromDate, Date toDate) {
        try ( Session session = HibernateUtils.getFactory().openSession()) {
            CriteriaBuilder b = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

            Root rootP = q.from(Product.class);
            Root rootD = q.from(OrderDetail.class);

            q.where(b.equal(rootD.get("productId"), rootP.get("id")));
            q.multiselect(rootP.get("id"), rootP.get("name"),
                    b.sum(b.prod(rootD.get("unitPrice"), rootD.get("num"))));
            q.groupBy(rootP.get("id"));
            
            
            Query query = session.createQuery(q);
            return query.getResultList();

        }
    }

}
