package com.example.singh.pizzachallenge.injection.pizzalist;

import com.example.singh.pizzachallenge.pizzalist.PizzaListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by singh on 12-Apr-17.
 */

@Module
public class PizzaListModule {

    @Provides
    public PizzaListPresenter providePizzaListPresenter(){
        return new PizzaListPresenter();
    }

}
