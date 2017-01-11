package e2e.components;

/**
 * Created by Anatolii_Hanziuk on 1/11/2017.
 */
public class Dish {

    private static String dishName;
    private static int dishQuantity;
    private static double dishPrice;

    public Dish(String dishName, int dishQuantity, double dishPrice) {
        this.dishName = dishName;
        this.dishQuantity = dishQuantity;
        this.dishPrice = dishPrice;
    }

    public static String getDishName() {
        return dishName;
    }

    public static void setDishName(String dishName) {
        Dish.dishName = dishName;
    }

    public static int getDishQuantity() {
        return dishQuantity;
    }

    public static void setDishQuantity(int dishQuantity) {
        Dish.dishQuantity = dishQuantity;
    }

    public static double getDishPrice() {
        return dishPrice;
    }

    public static void setDishPrice(double dishPrice) {
        Dish.dishPrice = dishPrice;
    }
}
