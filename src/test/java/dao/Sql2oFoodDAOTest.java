package dao;
import models.Food;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;

public class Sql2oFoodDAOTest {

    private Sql2oFoodDAO FoodDAO;
    private Connection con;
    Food food;

    @Before
    public void setUp(){
        String connectionString = "jdbc:postgresql://localhost:5432/nutrisitest";
        Sql2o sql2o = new Sql2o(connectionString, "student", "");
        FoodDAO = new Sql2oFoodDAO(sql2o);
        con = sql2o.open();
    }

    @Test
    public void canFindByName() {
        Food walnut = new Food(1, "walnut", "nut", 654.0, 15.23, 65.21, 13.71, 6.7);
        FoodDAO.findByName("walnut");
        assertEquals(walnut, FoodDAO.findByName("walnut"));
    }

}
