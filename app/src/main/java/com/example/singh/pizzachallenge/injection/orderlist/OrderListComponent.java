package com.example.singh.pizzachallenge.injection.orderlist;

import com.example.singh.pizzachallenge.activities.orderlist.OrderListActivity;

import dagger.Component;

/**
 * Created by singh on 12-Apr-17.
 */

@Component(modules = OrderListModule.class)
public interface OrderListComponent {

    void inject(OrderListActivity orderListActivity);


}
