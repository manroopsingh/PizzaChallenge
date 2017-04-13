package com.example.singh.pizzachallenge.activities.neworder;

import com.example.singh.pizzachallenge.BasePresenter;
import com.example.singh.pizzachallenge.BaseView;
import com.example.singh.pizzachallenge.model.NewOrder;

/**
 * Created by singh on 12-Apr-17.
 */

public interface NewOrderContract {

    interface View extends BaseView{
        void validOrder();
        void invalidOrder();
    }

    interface Presenter extends BasePresenter<View>{

        void validateInput(NewOrder newOrder);

    }

}
