package dao;

import models.User;
import org.mindrot.jbcrypt.BCrypt;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import org.sql2o.data.Row;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Sql2oUserDAO implements UserDAO{

    private Sql2o sql2o;
    public String error;

    public  Sql2oUserDAO(Sql2o sql2o) { this.sql2o = sql2o; }

    public String getError() { return error; }

    @Override
    public User create(String name, String email, String password) {
        String hashpass = BCrypt.hashpw(password, BCrypt.gensalt());
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery("INSERT INTO users (name, email, password) VALUES (:name, :email, :password)", true)
                    .addParameter("name", name)
                    .addParameter("email", email)
                    .addParameter("password", hashpass)
                    .executeUpdate()
                    .getKey();
            return con.createQuery("SELECT id, name, email, password FROM users WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        } catch(Sql2oException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean userExists(String email) {
        try(Connection con = sql2o.open()) {
            User user = con.createQuery("SELECT email FROM users WHERE email = :email")
                    .addParameter("email", email)
                    .executeAndFetchFirst(User.class);
            return user != null;
        }
    }

    @Override
    public User authenticate(String email, String password) {
        try(Connection con = sql2o.open()) {
            User result = con.createQuery("SELECT id, name, email, password FROM users WHERE email = :email")
                    .addParameter("email", email)
                    .executeAndFetchFirst(User.class);
            if (result == null) {
                error = "Incorrect email! Please try again!";
            } else if (!BCrypt.checkpw(password, result.getPassword())) {
                error = "Incorrect password! Please try again!";
            } else {
                result.setLoggedIn();
                return result;
            }
        } catch (Sql2oException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<String> listFav(int userId) {
        try(Connection con = sql2o.open()) {
            User result = con.createQuery("SELECT favourites FROM users WHERE id = :id")
                    .addParameter("id", userId)
                    .executeAndFetchFirst(User.class);
            return result.getFav();

        } catch (Sql2oException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addFav(int userId, String foodName) {
        try (Connection con = sql2o.open()) {
            con.createQuery("UPDATE users SET favourites = array_append(favourites, :foodName) WHERE id = :id ")
                    .addParameter("id", userId)
                    .addParameter("foodName", foodName)
                    .executeUpdate();
        } catch (Sql2oException e) {
            e.printStackTrace();
        }
    }
}
