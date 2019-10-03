package dao;

import models.Food;

public interface FoodDAO {
    Food findByName(String name);

    Food findByCategory(String category);

    Food findByCalories(double calories);

    Food findByProtein(double protein);

    Food findByFat(double fat);

    Food findByCarbs(double carbs);

    Food findByFibre(double fibre);

    Food findByTag(String tag);
}
