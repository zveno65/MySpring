package ru.plotnikov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.plotnikov.model.User;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Transactional
@Repository("userDao")
public class UserDao implements UserRepository {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public User findByName(String name) throws SQLException {
        return (User) sessionFactory.getCurrentSession().createQuery("from User where name = :name")
                .setParameter("name", name)
                .uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public User find(Long id) throws SQLException {
        return (User) sessionFactory.getCurrentSession().createQuery("from User where id = :id")
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() throws SQLException {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    @Override
    public boolean save(User o) throws SQLException {
        sessionFactory.getCurrentSession().saveOrUpdate(o);
        return true;
    }

    @Override
    public boolean update(User o) throws SQLException {
        sessionFactory.getCurrentSession().saveOrUpdate(o);
        return true;
    }

    @Override
    public boolean delete(User o) throws SQLException {
        Session session = sessionFactory.getCurrentSession();

//        String hql = "DELETE user_role WHERE user_id = :lg";
//        Query query = sessionFactory.getCurrentSession().createQuery(hql);
//        query.setParameter("lg", o.getId());
//        int rows = query.executeUpdate();
        session.delete(o);

        return true;
    }
}

