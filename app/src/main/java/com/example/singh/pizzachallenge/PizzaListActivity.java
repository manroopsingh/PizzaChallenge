package com.example.singh.pizzachallenge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.singh.pizzachallenge.model.Pizza;
import com.example.singh.pizzachallenge.model.TopPizza;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

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

public class PizzaListActivity extends AppCompatActivity {

    private static final String TAG = "PizaaListActivity";
    JsonReader jsonReader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_list);


//            try {
//                jsonReader = new JsonReader(new FileReader(String.valueOf(getResources().getAssets().open("pizzas.json"))));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }




        Gson gson = new Gson();
        String jsonOutput = loadJSONFromAsset();
        Type listType = new TypeToken<List<Pizza>>(){}.getType();
        List<Pizza> pizzaList = (List<Pizza>) gson.fromJson(jsonOutput, listType);


        HashMap<String,Integer> map = new HashMap<>();

        for(Pizza pizza: pizzaList){

            StringBuilder sb = new StringBuilder();
            for(int i = 0;i<pizza.getToppings().size();i++){
                sb.append(pizza.getToppings().get(i));
                if(!(i==pizza.getToppings().size()-1)) sb.append(", ");

            }

            String topValue = sb.toString();
            if(map.get(topValue)==null) map.put(topValue,1);
            else{
                int total = map.get(topValue);
                map.put(topValue,total+1);
            }

        }

        Map<String, Integer> sortedMap = sortByComparator(map, false);
        printMap(sortedMap);

        List<TopPizza> topPizzas = new ArrayList<>();

        int count = 0;
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet())
        {
            TopPizza topPizza = new TopPizza(entry.getKey(),entry.getValue());
            topPizzas.add(topPizza);

        }

        Log.d(TAG, "onCreate: " + topPizzas.size());

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("pizzas.json");
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

    private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order)
    {

        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());
        // Sorting the list based on values
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });
        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public static void printMap(Map<String, Integer> map)
    {
        for (Map.Entry<String, Integer> entry : map.entrySet())
        {
            System.out.println(entry.getKey() + ":"+ entry.getValue());
        }
    }
}
