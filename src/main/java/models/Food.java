package models;

import java.util.Objects;

public class Food {

    private int id;
    private String name;
    private String category;
    private double calories;
    private double protein;
    private double fat;
    private double carbs;
    private double fibre;
    private String photo;
    private String tag;
    private String calorierange;
    private String proteinrange;

    public Food(int id, String name, String category, double calories, double protein, double fat, double carbs, double fibre, String tag, String calorierange) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
        this.fibre = fibre;
        this.tag = tag;
        this.calorierange = calorierange;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getFibre() {
        return fibre;
    }

    public void setFibre(double fibre) {
        this.fibre = fibre;
    }

    public String getPhoto() { return photo; }

    public String getTag() {return tag;}

    public String getCalorieRange() {return calorierange;}

    public String getProteinRange() {return proteinrange;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return id == food.id &&
                Double.compare(food.calories, calories) == 0 &&
                Double.compare(food.protein, protein) == 0 &&
                Double.compare(food.fat, fat) == 0 &&
                Double.compare(food.carbs, carbs) == 0 &&
                Double.compare(food.fibre, fibre) == 0 &&
                Objects.equals(name, food.name) &&
                Objects.equals(category, food.category) &&
                Objects.equals(photo, food.photo) &&
                Objects.equals(tag, food.tag) &&
                Objects.equals(calorierange, food.calorierange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, calories, protein, fat, carbs, fibre, photo, tag, calorierange);
    }
}
