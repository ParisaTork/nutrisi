package dao;
import models.Food;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class Sql2oFoodDAOTest {

    private Sql2oFoodDAO FoodDAO;
    private Connection con;
    Food walnut;

    @Before
    public void setUp(){
        String connectionString = "jdbc:postgresql://localhost:5432/nutrisitest";
        // Update user and pass below to your database username and password
        Sql2o sql2o = new Sql2o(connectionString, "student", "");
        FoodDAO = new Sql2oFoodDAO(sql2o);
        con = sql2o.open();
        walnut = new Food(1, "walnut", "nut", 654.0, 15.23, 65.21, 13.71, 6.7,
                "https://source.unsplash.com/FT0fNHmv4A0/400x400", "high protein", "600-699", "10-19", "10-19", "19+");
    }

    @Test
    public void canFindByName() {
        Food foundNut = FoodDAO.findByName("walnut");
        assertEquals(walnut, foundNut);

    }

    @Test
    public void canFindByCategory() {
        List<Food> foundNut = FoodDAO.findByCategory("nut");
        assertThat(foundNut, contains(walnut));

    }

    @Test
    public void canFindByCalories(){
        List<Food> foundNut = FoodDAO.findByCalories(654.0);
        assertThat(foundNut, contains(walnut));
    }

    @Test
    public void canFindByProtein(){
        List<Food> foundNut = FoodDAO.findByProtein(15.23);
        assertThat(foundNut, contains(walnut));
    }

    @Test
    public void canFindByFat(){
        List<Food> foundNut = FoodDAO.findByFat(65.21);
        assertThat(foundNut, contains(walnut));
    }

    @Test
    public void canFindByCarbs(){
        List<Food> foundNut = FoodDAO.findByCarbs(13.71);
        assertThat(foundNut, contains(walnut));
    }
}
