package com.example.singh.pizzachallenge.activities.pizzalist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.singh.pizzachallenge.R;
import com.example.singh.pizzachallenge.model.Pizza;
import com.example.singh.pizzachallenge.model.PizzaOrders;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by singh on 12-Apr-17.
 */

public class PizzaListPresenter implements PizzaListContract.Presenter {

    PizzaListContract.View view;
    Context context;
    List<PizzaOrders> pizzaOrdersList = new ArrayList<>();
    List<PizzaOrders> pizzaOrdersTopList = new ArrayList<>();


    @Override
    public void addView(PizzaListContract.View view) {
        this.view = view;

    }

    @Override
    public void removeView() {

        this.view = null;
    }

    @Override
    public void getPizzaList() {
        initializePizzaList();
        for (int i = 0; i < 20; i++) {
            pizzaOrdersTopList.add(pizzaOrdersList.get(i));
        }
        view.updateRecyclerView(pizzaOrdersTopList);


    }

    @Override
    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder((Context) view);
        builder.setTitle("Show top charts")
                .setItems(R.array.order_top_array, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        filterList(which);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

    private void filterList(int which) {
        List<PizzaOrders> newPizzaTopList = new ArrayList<>();
        int chart = 0;

        switch (which) {
            case 0:
                chart = 20;
                break;
            case 1:
                chart = 50;
                break;
            case 2:
                chart = 100;
                break;
            case 3:
                chart = pizzaOrdersList.size();
                break;
        }

        for (int i = 0; i < chart; i++) {
            newPizzaTopList.add(pizzaOrdersList.get(i));
        }
        view.updateRecyclerView(newPizzaTopList);

    }


    private void initializePizzaList() {
        Gson gson = new Gson();
        String jsonOutput = loadJSONFromAsset();
        Type listType = new TypeToken<List<Pizza>>() {
        }.getType();
        List<Pizza> pizzaList = (List<Pizza>) gson.fromJson(jsonOutput, listType);

        HashMap<String, Integer> map = new HashMap<>();

        for (Pizza pizza : pizzaList) {

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pizza.getToppings().size(); i++) {
                sb.append(pizza.getToppings().get(i));
                if (!(i == pizza.getToppings().size() - 1)) sb.append(", ");

            }

            String topValue = sb.toString();
            if (map.get(topValue) == null) map.put(topValue, 1);
            else {
                int total = map.get(topValue);
                map.put(topValue, total + 1);
            }
        }

        Map<String, Integer> sortedMap = sortByComparator(map, false);

        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            PizzaOrders pizzaOrders = new PizzaOrders(entry.getKey(), entry.getValue());
            pizzaOrdersList.add(pizzaOrders);
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = ((Context)view).getAssets().open("pizzas.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order) {

        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());
        // Sorting the list based on values
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                if (order) {
                    return o1.getValue().compareTo(o2.getValue());
                } else {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
        });
        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }


}
