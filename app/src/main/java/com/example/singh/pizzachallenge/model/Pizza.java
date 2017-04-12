package com.example.singh.pizzachallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pizza {

    @SerializedName("toppings")
    @Expose
    private List<String> toppings = null;

    public List<String> getToppings() {
        return toppings;
    }

    public void setToppings(List<String> toppings) {
        this.toppings = toppings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pizza pizza = (Pizza) o;

        return toppings != null ? toppings.equals(pizza.toppings) : pizza.toppings == null;

    }

    @Override
    public int hashCode() {
        return toppings != null ? toppings.hashCode() : 0;
    }
}