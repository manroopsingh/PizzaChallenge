package com.example.singh.pizzachallenge.activities.neworder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.singh.pizzachallenge.R;
import com.example.singh.pizzachallenge.injection.neworder.DaggerNewOrderComponent;
import com.example.singh.pizzachallenge.model.NewOrder;
import com.example.singh.pizzachallenge.activities.orderlist.OrderListActivity;
import com.example.singh.pizzachallenge.activities.pizzalist.PizzaListActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewOrderActivity extends AppCompatActivity implements NewOrderContract.View {


    @BindView(R.id.etName)
    EditText etName;

    @BindView(R.id.etPhone)
    EditText etPhone;

    @BindView(R.id.etQuantity)
    EditText etQuantity;

    @BindView(R.id.btnOrder)
    Button btnOrder;


    @Inject
    NewOrderPresenter presenter;


    List<String> toppingList = new ArrayList<>();
    private boolean favouriteOrder = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        ButterKnife.bind(this);
        setupDaggerComponent();
        setTitle("Custom Order");
        presenter.addView(this);
    }

    private void setupDaggerComponent() {
        DaggerNewOrderComponent.create().inject(this);
    }


    @Override
    public void showError(String error) {

    }

    @Override
    public void validOrder() {

        Toast.makeText(this, "You order have saved", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(NewOrderActivity.this, OrderListActivity.class);
        startActivity(intent);


    }

    @Override
    public void invalidOrder() {

        Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show();

    }

    @OnClick(R.id.btnOrder)
    public void saveOrder() {
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        int quantity = Integer.parseInt(etQuantity.getText().toString());

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        NewOrder newOrder = new NewOrder(name, phone, toppingList, quantity, favouriteOrder, cal.getTime().toString());
        presenter.validateInput(newOrder, this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.neworder_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_my_order) {
            Intent intent = new Intent(NewOrderActivity.this, OrderListActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_top_order) {
            Intent intent = new Intent(NewOrderActivity.this, PizzaListActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onCheckboxClicked(View view) {

        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.pepperoni:
                if (checked)
                    toppingList.add("pepperoni");
                else
                    removeFromToppings("pepperoni");
                break;
            case R.id.mushrooms:
                if (checked)
                    toppingList.add("mushrooms");
                else
                    removeFromToppings("mushrooms");
                break;
            case R.id.chicken:
                if (checked)
                    toppingList.add("chicken");
                else
                    removeFromToppings("chicken");
                break;
            case R.id.beef:
                if (checked)
                    toppingList.add("beef");
                else
                    removeFromToppings("beef");
                break;
            case R.id.bacon:
                if (checked)
                    toppingList.add("bacon");
                else
                    removeFromToppings("bacon");
                break;
            case R.id.sausage:
                if (checked)
                    toppingList.add("sausage");
                else
                    removeFromToppings("sausage");
                break;
            case R.id.olives:
                if (checked)
                    toppingList.add("olives");
                else
                    removeFromToppings("olives");
                break;
            case R.id.onions:
                if (checked)
                    toppingList.add("onions");
                else
                    removeFromToppings("onions");
                break;
            case R.id.tomatoes:
                if (checked)
                    toppingList.add("tomatoes");
                else
                    removeFromToppings("tomatoes");
                break;
            case R.id.favourite:
                if (checked)
                    favouriteOrder = true;
                else
                    favouriteOrder = false;
                break;

        }

    }

    private void removeFromToppings(String topping) {

        for (String toppings : toppingList) {
            if (toppings.equals(topping))
                toppingList.remove(topping);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
