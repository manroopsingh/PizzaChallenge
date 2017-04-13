package com.example.singh.pizzachallenge.injection.pizzalist;

import com.example.singh.pizzachallenge.activities.pizzalist.PizzaListActivity;

import dagger.Component;

/**
 * Created by singh on 12-Apr-17.
 */

@Component(modules = PizzaListModule.class)
public interface PizzaListComponent {

    void inject(PizzaListActivity pizzaListActivity);
}
