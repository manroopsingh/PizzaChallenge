package com.example.singh.pizzachallenge.injection.orderlist;

import com.example.singh.pizzachallenge.activities.orderlist.OrderListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by singh on 12-Apr-17.
 */

@Module
public class OrderListModule {

    @Provides
    public OrderListPresenter provideOrderListPresenter(){
        return new OrderListPresenter();
    }
}
