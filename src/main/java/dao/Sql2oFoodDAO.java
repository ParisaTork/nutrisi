package dao;

import models.Food;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import org.sql2o.Sql2oException;

public class Sql2oFoodDAO implements FoodDAO {

    private Sql2o sql2o;


    public Sql2oFoodDAO(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public Food findByName(Food name) {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT calories, protein, fat, carbs, fibre FROM food WHERE name = :name")
                    .addParameter("name", name)
                    .executeAndFetchFirst(Food.class);
        } catch(Sql2oException e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Food findByCategory(String category) {
        return null;
    }

    @Override
    public Food findByCalories(double calories) {
        return null;
    }

    @Override
    public Food findByProtein(double protein) {
        return null;
    }

    @Override
    public Food findByFat(double fat) {
        return null;
    }

    @Override
    public Food findByCarbs(double carbs) {
        return null;
    }

    @Override
    public Food findByFibre(double fibre) {
        return null;
    }

    @Override
    public Food findByTag(String tag) {
        return null;
    }
    
}
