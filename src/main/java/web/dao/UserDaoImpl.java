package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<User> findAll() {
        return manager.createQuery("from User", User.class)
                .getResultList();
    }

    @Override
    public User findById(int id) {
        return manager.find(User.class, id);
    }

    @Override
    public void update(User user) {
        if (null == user) {
            throw new NullPointerException("User not found");
        }
        manager.merge(user);
        manager.flush();
    }

    @Override
    public void save(User user) {
        manager.persist(user);
    }

    @Override
    public User deleteById(int id) throws NullPointerException {
        User user = findById(id);
        if (null == user) {
            throw new NullPointerException("User not found");
        }
        manager.remove(user);
        manager.flush();
        return user;
    }
}