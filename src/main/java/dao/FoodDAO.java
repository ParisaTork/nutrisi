package dao;

import models.Food;

import java.util.List;

public interface FoodDAO {
    Food findByName(String name);

    List<Food> findByCategory(String category);

    List<Food> findByCalories(double calories);

    List<Food> findByProtein(double protein);

    List<Food> findByFat(double fat);

    List<Food> findByCarbs(double carbs);

    List<Food> findByFibre(double fibre);

    List<Food> findByTag(String tag);

    List<Food> findByCalorieRange(String calorierange);

    List<Food> findByProteinRange(String proteinrange);
}
