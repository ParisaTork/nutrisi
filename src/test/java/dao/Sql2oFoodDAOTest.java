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
        Sql2o sql2o = new Sql2o(connectionString, "student", "");
        FoodDAO = new Sql2oFoodDAO(sql2o);
        con = sql2o.open();
    }

    @Test
    public void canFindByName() {
        walnut = new Food(1, "walnut", "nut", 654.0, 15.23, 65.21, 13.71, 6.7, "high protein");
        Food foundNut = FoodDAO.findByName("walnut");
        assertEquals(walnut, foundNut);

    }

    @Test
    public void canFindByCategory() {
        walnut = new Food(1, "walnut", "nut", 654.0, 15.23, 65.21, 13.71, 6.7, "high protein");
        List<Food> foundNut = FoodDAO.findByCategory("nut");
        assertThat(foundNut, contains(walnut));

    }

    @Test
    public void canFindByCalories(){
        walnut = new Food(1, "walnut", "nut", 654.0, 15.23, 65.21, 13.71, 6.7, "high protein");
        List<Food> foundNut = FoodDAO.findByCalories(654.0);
        assertThat(foundNut, contains(walnut));
    }

    @Test
    public void canFindByProtein(){
        walnut = new Food(1, "walnut", "nut", 654.0, 15.23, 65.21, 13.71, 6.7, "high protein");
        List<Food> foundNut = FoodDAO.findByProtein(15.23);
        assertThat(foundNut, contains(walnut));
    }

    @Test
    public void canFindByFat(){
        walnut = new Food(1, "walnut", "nut", 654.0, 15.23, 65.21, 13.71, 6.7, "high protein");
        List<Food> foundNut = FoodDAO.findByFat(65.21);
        assertThat(foundNut, contains(walnut));
    }

    @Test
    public void canFindByCarbs(){
        walnut = new Food(1, "walnut", "nut", 654.0, 15.23, 65.21, 13.71, 6.7, "high protein");
        List<Food> foundNut = FoodDAO.findByCarbs(13.71);
        assertThat(foundNut, contains(walnut));
    }

    @Test
    public void canFindByFibre(){
        walnut = new Food(1, "walnut", "nut", 654.0, 15.23, 65.21, 13.71, 6.7, "high protein");
        List<Food> foundNut = FoodDAO.findByFibre(6.7);
        assertThat(foundNut, contains(walnut));
    }

}
