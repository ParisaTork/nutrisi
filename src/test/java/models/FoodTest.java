package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FoodTest {

    Food food;

    @Before
    public void setUp() {
        food = new Food(1, "walnut", "nut", 654.0, 15.23, 65.21, 13.71, 6.7);
    }

    @Test
    public void getId() {
        assertEquals(1, food.getId());
    }

    @Test
    public void setId() {
        food.setId(2);
        assertEquals(2, food.getId());
    }

    @Test
    public void getName() {
        assertEquals("walnut", food.getName());
    }

    @Test
    public void setName() {
        food.setName("Almond");
        assertEquals("Almond", food.getName());
    }

    @Test
    public void getCategory() {
        assertEquals("nut", food.getCategory());
    }

    @Test
    public void setCategory() {
        food.setCategory("fruit");
        assertEquals("fruit", food.getCategory());
    }

    @Test
    public void getCalories() {
        assertEquals( 654.0, food.getCalories(), 0.0);
    }

    @Test
    public void setCalories() {
        food.setCalories(90.92);
        assertEquals(90.92, food.getCalories(), 0.0);
    }

    @Test
    public void getProtein() {
        assertEquals(15.23, food.getProtein(), 0.0);
    }

    @Test
    public void setProtein() {
        food.setProtein(20.21);
        assertEquals(20.21, food.getProtein(), 0.0);
    }

    @Test
    public void getFat() {
        assertEquals(65.21, food.getFat(), 0.0);
    }

    @Test
    public void setFat() {
        food.setFat(14.0);
        assertEquals(14.0, food.getFat(), 0.0);
    }

    @Test
    public void getCarbs() {
        assertEquals(13.71, food.getCarbs(), 0.0);
    }

    @Test
    public void setCarbs() {
        food.setCarbs(13.4);
        assertEquals(13.4, food.getCarbs(), 0.0);
    }

    @Test
    public void getFibre() {
        assertEquals(6.7, food.getFibre(), 0.0);
    }

    @Test
    public void setFibre() {
        food.setFibre(55.7);
        assertEquals(55.7, food.getFibre(), 0.0);
    }
}
