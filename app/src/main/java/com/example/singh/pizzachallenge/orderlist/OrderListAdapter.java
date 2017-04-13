package com.example.singh.pizzachallenge.orderlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.singh.pizzachallenge.R;
import com.example.singh.pizzachallenge.helper.DatabaseHelper;
import com.example.singh.pizzachallenge.model.NewOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by singh on 12-Apr-17.
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {
    List<NewOrder> orderList = new ArrayList<>();
    Context context;

    public OrderListAdapter(List<NewOrder> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPhone, tvTimestamp, tvToppings, tvQuantity;
        CheckBox cbFavourite;


        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvPhone = (TextView) itemView.findViewById(R.id.tvPhone);
            tvTimestamp = (TextView) itemView.findViewById(R.id.tvTimestamp);
            tvToppings = (TextView) itemView.findViewById(R.id.tvToppings);
            tvQuantity = (TextView) itemView.findViewById(R.id.tvQuantity);
            cbFavourite = (CheckBox) itemView.findViewById(R.id.cbFavourite);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final NewOrder newOrder = orderList.get(position);
        holder.tvName.setText(newOrder.getUsername());
        holder.tvPhone.setText(newOrder.getPhone());
        holder.tvTimestamp.setText(newOrder.getTimestamp());
        holder.tvToppings.setText(newOrder.getToppingsString());
        holder.tvQuantity.setText(String.valueOf(newOrder.getQuantity()));
        holder.cbFavourite.setChecked(newOrder.isFavourite());

        holder.cbFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((CheckBox) v).isChecked())
                    newOrder.setFavourite(true);
                else
                    newOrder.setFavourite(false);


                DatabaseHelper databaseHelper = new DatabaseHelper(context);
                databaseHelper.updateOrder(newOrder);
                Toast.makeText(v.getContext(), "Changed", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
