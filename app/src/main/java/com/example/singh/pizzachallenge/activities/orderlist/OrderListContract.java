package com.example.singh.pizzachallenge.activities.orderlist;

import com.example.singh.pizzachallenge.BasePresenter;
import com.example.singh.pizzachallenge.BaseView;
import com.example.singh.pizzachallenge.model.NewOrder;

import java.util.List;

/**
 * Created by singh on 12-Apr-17.
 */

public interface OrderListContract {

    interface View extends BaseView{
        void updateRecyclerView(List<NewOrder> orderList);


    }

    interface Presenter extends BasePresenter<View>{
        void getOrders();

    }

}
