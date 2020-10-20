package com.example.bloodbank.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.bloodbank.R;

import com.example.bloodbank.data.model.city.CityData;

import java.util.ArrayList;
import java.util.List;

public class EmptySpinnerAdapter extends BaseAdapter {

    private Context context;
    private List<CityData> citiesList = new ArrayList<>();
    private LayoutInflater inflter;
    public int selectedId = 0;
    private ListFilter listFilter = new ListFilter();

    public EmptySpinnerAdapter(Context applicationContext) {
        this.context = applicationContext;

        inflter = (LayoutInflater.from(applicationContext));
    }

    public void setBloodTypes(List<CityData> list) {
        this.citiesList = list;
    }


    @Override
    public int getCount() {
        return citiesList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner_items, null);

        TextView names = (TextView) view.findViewById(R.id.spinnerDataNameTV);

        names.setText(citiesList.get(i).getName());
        selectedId = citiesList.get(i).getId();

        return view;
    }


    public class ListFilter extends Filter {
        private Object lock = new Object();

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();
            if (citiesList == null) {
                synchronized (lock) {
                    citiesList = new ArrayList();
                }
            }

            if (prefix == null || prefix.length() == 0) {
                synchronized (lock) {
                    results.values = citiesList;
                    results.count = citiesList.size();
                }
            } else {
                final String searchStrLowerCase = prefix.toString().toLowerCase();

                ArrayList<CityData> matchValues = new ArrayList();

                for (CityData dataItem : citiesList) {
                    if (dataItem.getName().toLowerCase().startsWith(searchStrLowerCase)) {
                        matchValues.add(dataItem);
                    }
                }

                results.values = matchValues;
                results.count = matchValues.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.values != null) {
                citiesList = (ArrayList) results.values;
            } else {
                citiesList = null;
            }
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }

    }
}