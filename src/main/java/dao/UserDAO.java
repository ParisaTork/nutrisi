package dao;
import models.User;

import java.sql.Array;
import java.util.ArrayList;

public interface UserDAO {
    User create(String name, String email, String password);
    boolean userExists(String email);
    User authenticate(String email, String password);
    ArrayList<String> listFav(int userId);
    void addFav(int userId, String foodName);
}
