package com.example.singh.pizzachallenge;

/**
 * Created by Siris on 3/13/2017.
 */

public interface BasePresenter<V extends BaseView> {
    void addView(V view);
    void removeView();
}
