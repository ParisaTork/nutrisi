package dao;

import models.Food;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oFoodDAO implements FoodDAO {

    private Sql2o sql2o;



    public Sql2oFoodDAO(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public Food findByName(String name) {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM food WHERE name = :name;")
                    .addParameter("name", name)
                    .executeAndFetchFirst(Food.class);
        } catch(Sql2oException e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Food> findByCategory(String category) {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM food WHERE category = :category;")
                    .addParameter("category", category)
                    .executeAndFetch(Food.class);
        } catch(Sql2oException e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Food> findByCalories(double calories) {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM food WHERE calories = :calories;")
                    .addParameter("calories", calories)
                    .executeAndFetch(Food.class);
        } catch(Sql2oException e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Food> findByProtein(double protein) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM food WHERE protein = :protein;")
                    .addParameter("protein", protein)
                    .executeAndFetch(Food.class);
        } catch(Sql2oException e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Food> findByFat(double fat) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM food WHERE fat = :fat;")
                    .addParameter("fat", fat)
                    .executeAndFetch(Food.class);
        } catch(Sql2oException e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Food> findByCarbs(double carbs) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM food WHERE carbs = :carbs;")
                    .addParameter("carbs", carbs)
                    .executeAndFetch(Food.class);
        } catch(Sql2oException e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Food> findByFibre(double fibre) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM food WHERE fibre = :fibre;")
                    .addParameter("fibre", fibre)
                    .executeAndFetch(Food.class);
        } catch(Sql2oException e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Food> findByTag(String tag) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM food WHERE tag = :tag;")
                    .addParameter("tag", tag)
                    .executeAndFetch(Food.class);
        } catch(Sql2oException e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Food> findByCalorieRange(String calorierange) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM food WHERE calorierange = :calorierange;")
                    .addParameter("calorierange", calorierange)
                    .executeAndFetch(Food.class);
        } catch(Sql2oException e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Food> findByProteinRange(String proteinrange) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM food WHERE proteinrange = :proteinrange;")
                    .addParameter("proteinrange", proteinrange)
                    .executeAndFetch(Food.class);
        } catch(Sql2oException e){
            System.out.println(e);
        }
        return null;
    }

}
