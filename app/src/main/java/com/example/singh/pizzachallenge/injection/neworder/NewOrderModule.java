package com.example.singh.pizzachallenge.injection.neworder;

import com.example.singh.pizzachallenge.neworder.NewOrderPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by singh on 12-Apr-17.
 */


@Module
public class NewOrderModule {

    @Provides
    public NewOrderPresenter provideNewOrderPresenter(){
        return new NewOrderPresenter();
    }

}
