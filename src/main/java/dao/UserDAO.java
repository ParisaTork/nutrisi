package dao;
import models.User;

public interface UserDAO {
    User create(String name, String email, String password);
    boolean userExists(String email);
    User authenticate(String email, String password);
}
