package com.example.singh.pizzachallenge.neworder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.singh.pizzachallenge.R;
import com.example.singh.pizzachallenge.injection.neworder.DaggerNewOrderComponent;
import com.example.singh.pizzachallenge.model.NewOrder;

import java.util.ArrayList;
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
    private boolean favouriteOrder;

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
        NewOrder newOrder = new NewOrder(name, phone, toppingList, quantity, favouriteOrder);
        presenter.validateInput(newOrder);
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
            case R.id.mozzarellaCheese:
                if (checked)
                    toppingList.add("mozzarella cheese");
                else
                    removeFromToppings("mozzarella cheese");
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
}
