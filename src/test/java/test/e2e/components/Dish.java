package test.e2e.components;

public class Dish {

    private String dishName;
    private int dishQuantity;
    private double dishPrice;

    public Dish(String dishName, int dishQuantity, double dishPrice) {
        this.dishName = dishName;
        this.dishQuantity = dishQuantity;
        this.dishPrice = dishPrice;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getDishQuantity() {
        return dishQuantity;
    }

    public void setDishQuantity(int dishQuantity) {
        this.dishQuantity = dishQuantity;
    }

    public double getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(double dishPrice) {
        this.dishPrice = dishPrice;
    }
}
