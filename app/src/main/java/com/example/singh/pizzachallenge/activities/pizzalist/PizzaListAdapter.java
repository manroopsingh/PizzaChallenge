package com.example.singh.pizzachallenge.activities.pizzalist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.singh.pizzachallenge.R;
import com.example.singh.pizzachallenge.model.PizzaOrders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by singh on 12-Apr-17.
 */

public class PizzaListAdapter extends RecyclerView.Adapter<PizzaListAdapter.ViewHolder>{


    List<PizzaOrders> pizzaOrdersList = new ArrayList<>();

    public PizzaListAdapter(List<PizzaOrders> pizzaOrdersList) {
        this.pizzaOrdersList = pizzaOrdersList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvToppings, tvOrders;


        public ViewHolder(View itemView) {
            super(itemView);
            tvToppings = (TextView) itemView.findViewById(R.id.tvPizzaToppings);
            tvOrders = (TextView) itemView.findViewById(R.id.tvPizzaOrders);

        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pizza_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        PizzaOrders pizzaOrders = pizzaOrdersList.get(position);
        holder.tvToppings.setText(pizzaOrders.getToppings());
        holder.tvOrders.setText(String.valueOf(pizzaOrders.getOrders()));



    }

    @Override
    public int getItemCount() {
        return pizzaOrdersList.size();
    }


}
