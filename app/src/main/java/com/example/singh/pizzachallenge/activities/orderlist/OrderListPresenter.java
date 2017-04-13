package com.example.singh.pizzachallenge.activities.orderlist;

import android.content.Context;

import com.example.singh.pizzachallenge.helper.DatabaseHelper;
import com.example.singh.pizzachallenge.model.NewOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by singh on 12-Apr-17.
 */

public class OrderListPresenter implements OrderListContract.Presenter {
    OrderListContract.View view;
    List<NewOrder> newOrderList = new ArrayList<>();

    @Override
    public void addView(OrderListContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public void getOrders(Context context) {

        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        newOrderList.addAll(databaseHelper.getAllOrders());

        view.updateRecyclerView(newOrderList);

    }
}
