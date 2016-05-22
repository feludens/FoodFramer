package com.spadatech.mobile.android.foodframer.models;

/**
 * Created by Felipe S. Pereira on 4/13/16.
 */
public class MealItem {

    // Table name
    public static final String TABLE = "MealItems";

    // Table Columns
    public static final String KEY_MEAL_ITEM_ID = "MealItemId";
    public static final String KEY_MEAL_ITEM_NAME = "MealItemName";
    public static final String KEY_MEAL_ITEM_MEAL_ID = "MealId";

    private String id;
    private String name;
    private String mealId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }
}
