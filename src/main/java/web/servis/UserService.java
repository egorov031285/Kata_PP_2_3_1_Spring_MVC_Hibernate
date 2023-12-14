package web.servis;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(int id);
    void save(User user);
    void update(User user);
    User deleteById(int id);
}
