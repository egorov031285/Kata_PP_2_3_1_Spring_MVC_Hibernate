package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();

    User findById(int id);

    void update(User user);

    void save(User user);

    User deleteById(int id);
}
