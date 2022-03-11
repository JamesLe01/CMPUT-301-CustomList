package com.example.simpleparadox.listycity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomList extends ArrayAdapter<City> {

    private ArrayList<City> cities;
    private Context context;

    public CustomList(Context context, ArrayList<City> cities){
        super(context,0, cities);
        this.cities = cities;
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        View view = convertView;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.content, parent,false);
        }

        City city = cities.get(position);

        TextView cityName = view.findViewById(R.id.city_text);
        TextView provinceName = view.findViewById(R.id.province_text);

        cityName.setText(city.getCityName());
        provinceName.setText(city.getProvinceName());

        return view;

    }

    /**
     * this function will get the size of list
     * @return size of ArrayList "cities"
     */
    public int getCount() {
        return cities.size();
    }

    /**
     * this function will add a city object into the list
     * @param city
     */
    public void addCity(City city) {
        cities.add(city);
        return;
    }

    /**
     * Search for a city in a list
     *
     * @param city The city to be search
     * @return true if the city is in the list, false otherwise
     */
    public boolean hasCity(City city) {
        for (int i = 0; i < getCount(); i++) {
            if (cities.get(i).getCityName().equals(city.getCityName()) &&
                    cities.get(i).getProvinceName().equals(city.getProvinceName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * This delete a city from a list (if exists in the list), else throw a RuntimeException
     *
     * @param city The city to be deleted
     */
    public void delete(City city) {

        if (!hasCity(city)) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getCityName().equals(city.getCityName())) {
                cities.remove(i);
                break;
            }
        }
    }
}
