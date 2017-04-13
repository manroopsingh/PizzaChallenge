package com.example.singh.pizzachallenge.activities.orderlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.singh.pizzachallenge.R;
import com.example.singh.pizzachallenge.injection.orderlist.DaggerOrderListComponent;
import com.example.singh.pizzachallenge.model.NewOrder;
import com.example.singh.pizzachallenge.activities.neworder.NewOrderActivity;
import com.example.singh.pizzachallenge.activities.pizzalist.PizzaListActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderListActivity extends AppCompatActivity implements OrderListContract.View{


    RecyclerView.LayoutManager layoutManager;
    DefaultItemAnimator defaultItemAnimator;
    OrderListAdapter orderListAdapter;
    List<NewOrder> newOrderList = new ArrayList<>();


    @BindView(R.id.rvOrderList)
    RecyclerView rvOrderList;

    @Inject
    OrderListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        ButterKnife.bind(this);
        DaggerOrderListComponent.create().inject(this);
        setTitle("My Orders");
        presenter.addView(this);
        setupRecyclerView();
        presenter.getOrders(this);

    }

    private void setupRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        defaultItemAnimator = new DefaultItemAnimator();
        orderListAdapter = new OrderListAdapter(newOrderList, this);
        rvOrderList.setLayoutManager(layoutManager);
        rvOrderList.setItemAnimator(defaultItemAnimator);
        rvOrderList.setAdapter(orderListAdapter);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void updateRecyclerView(List<NewOrder> orderList) {
        newOrderList.clear();
        newOrderList.addAll(orderList);
        orderListAdapter.notifyDataSetChanged();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(OrderListActivity.this, NewOrderActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.myorders_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_new_order) {
            Intent intent = new Intent(OrderListActivity.this, NewOrderActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_top_order) {
            Intent intent = new Intent(OrderListActivity.this, PizzaListActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.removeView();
    }
}
