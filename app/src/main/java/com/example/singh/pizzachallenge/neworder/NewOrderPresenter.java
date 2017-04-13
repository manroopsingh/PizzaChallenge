package com.example.singh.pizzachallenge.neworder;

import android.content.Context;

import com.example.singh.pizzachallenge.helper.DatabaseHelper;
import com.example.singh.pizzachallenge.model.NewOrder;

/**
 * Created by singh on 12-Apr-17.
 */

public class NewOrderPresenter implements NewOrderContract.Presenter {

    NewOrderContract.View view;
    Context context;

    @Override
    public void addView(NewOrderContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {

        this.view = null;
    }

    @Override
    public void validateInput(NewOrder newOrder, Context context) {
        this.context = context;


        if (newOrder.getToppings() == null || newOrder.getPhone() == "" || newOrder.getQuantity() == 0 || newOrder.getUsername() == "")
            view.invalidOrder();
        else {

            saveNewOrder(newOrder);
            view.validOrder();
        }

    }

    private void saveNewOrder(NewOrder newOrder) {

        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        databaseHelper.addOrder(newOrder);
        databaseHelper.close();




    }
}
