package com.example.singh.pizzachallenge.pizzalist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.singh.pizzachallenge.R;
import com.example.singh.pizzachallenge.injection.pizzalist.DaggerPizzaListComponent;
import com.example.singh.pizzachallenge.model.PizzaOrders;
import com.example.singh.pizzachallenge.neworder.NewOrderActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PizzaListActivity extends AppCompatActivity implements PizzaListContract.View {


    @BindView(R.id.rvPizzaList)
    RecyclerView rvPizzaList;

    RecyclerView.LayoutManager layoutManager;
    DefaultItemAnimator defaultItemAnimator;
    PizzaListAdapter pizzaListAdapter;
    List<PizzaOrders> pizzaOrdersTopList = new ArrayList<>();


    @Inject
    PizzaListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_list);
        ButterKnife.bind(this);
        setupDaggerComponent();
        setupRecyclerView();
        setTitle("Pizza Rankings");

        presenter.addView(this);
        presenter.getPizzaList(this);

    }

    private void setupDaggerComponent() {
        DaggerPizzaListComponent.create()
                .inject(this);
    }
    private void setupRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        defaultItemAnimator = new DefaultItemAnimator();
        rvPizzaList.setLayoutManager(layoutManager);
        rvPizzaList.setItemAnimator(defaultItemAnimator);
        pizzaListAdapter = new PizzaListAdapter(pizzaOrdersTopList);
        rvPizzaList.setAdapter(pizzaListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pizzalist_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_order) {
            Intent intent = new Intent(PizzaListActivity.this, NewOrderActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_sort) {
            presenter.showDialog();
            return true;
        }

        if (id == R.id.action_my_order) {




            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void updateRecyclerView(List<PizzaOrders> pizzaOrdersList) {

        pizzaOrdersTopList.clear();
        pizzaOrdersTopList.addAll(pizzaOrdersList);
        pizzaListAdapter.notifyDataSetChanged();
    }




}
