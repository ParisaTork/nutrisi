package dao;

import models.User;
import org.mindrot.jbcrypt.BCrypt;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import org.sql2o.data.Row;

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
            User result = con.createQuery("SELECT email, password FROM users WHERE email = :email")
                    .addParameter("email", email)
                    .executeAndFetchFirst(User.class);
//            Boolean pwMatched = BCrypt.checkpw(password, result.getPassword());
//            System.out.println("result: " + result);
//            if (result != null && pwMatched) {
//                return  result;
//            } else if (result != null && !pwMatched) {
//                error = "Incorrect password";
//            } else if (result == null) {
//                error = "Incorrect email";
//            }
//            System.out.println("error: " + error);
            if (result == null) {
                error = "Incorrect email";
            } else if (!BCrypt.checkpw(password, result.getPassword())) {
                error = "Incorrect password";
            } else {
                return result;
            }
        } catch (Sql2oException e) {
            e.printStackTrace();
        }
        return null;
    }
}
