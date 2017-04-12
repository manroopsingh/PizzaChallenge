package com.example.singh.pizzachallenge.pizzalist;

import android.content.Context;

import com.example.singh.pizzachallenge.BasePresenter;
import com.example.singh.pizzachallenge.BaseView;
import com.example.singh.pizzachallenge.model.PizzaOrders;

import java.util.List;

/**
 * Created by singh on 12-Apr-17.
 */

public interface PizzaListContract {

    interface View extends BaseView{
        void updateRecyclerView(List<PizzaOrders> pizzaOrdersList);

    }

    interface Presenter extends BasePresenter<View>{

        void getPizzaList(Context context);
        void showDialog();
    }
}
