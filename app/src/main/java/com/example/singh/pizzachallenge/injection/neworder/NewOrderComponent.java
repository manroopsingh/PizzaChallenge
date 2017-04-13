package com.example.singh.pizzachallenge.injection.neworder;

import com.example.singh.pizzachallenge.neworder.NewOrderActivity;

import dagger.Component;

/**
 * Created by singh on 12-Apr-17.
 */

@Component(modules = NewOrderModule.class)
public interface NewOrderComponent {

    void inject(NewOrderActivity newOrderActivity);

}
