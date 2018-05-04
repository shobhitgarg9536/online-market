package com.shobhit.daoImpl;

import com.shobhit.dao.CategoryDao;
import com.shobhit.dto.Category;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository("categoryDAO")
@Transactional
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Boolean add(Category category) {
        try {
            sessionFactory.getCurrentSession().persist(category);
            return true;
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Category> list() {

        String selectActiveCategory = "FROM Category WHERE active = :active";
        Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
        query.setParameter("active",true);


        return query.getResultList();
    }

    @Override
    public Category get(int id) {
        return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
    }

    @Override
    public Boolean update(Category category) {
        try {
            sessionFactory.getCurrentSession().update(category);
            return true;
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean delete(Category category) {

        category.setActive(false);

        try {
            sessionFactory.getCurrentSession().update(category);
            return true;
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
