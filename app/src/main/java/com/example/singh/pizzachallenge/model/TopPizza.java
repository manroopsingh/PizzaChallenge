package com.example.singh.pizzachallenge.model;

/**
 * Created by singh on 12-Apr-17.
 */

public class TopPizza {

    private String toppings;
    private int orders;

    public TopPizza(String toppings, int orders) {
        this.toppings = toppings;
        this.orders = orders;
    }

    public String getToppings() {
        return toppings;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }
}
