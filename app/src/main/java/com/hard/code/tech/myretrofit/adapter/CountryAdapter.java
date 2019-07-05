package com.hard.code.tech.myretrofit.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hard.code.tech.myretrofit.R;
import com.hard.code.tech.myretrofit.databinding.LayoutCountryListBinding;
import com.hard.code.tech.myretrofit.models.CountryResult;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    ArrayList<CountryResult> countryResults;

    public CountryAdapter(ArrayList<CountryResult> countryResults) {

        this.countryResults = countryResults;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       /* return new CountryViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_country_list, viewGroup, false));*/
        //data binding
        LayoutCountryListBinding countryListBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext())
                , R.layout.layout_country_list, viewGroup, false);


        return new CountryViewHolder(countryListBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder countryViewHolder, int i) {
        CountryResult countryResult = countryResults.get(i);
        countryViewHolder.onBind(countryResult);
        //countryViewHolder.txtName.setText(countryResults.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return countryResults == null ? 0 : countryResults.size();
    }


    static class CountryViewHolder extends RecyclerView.ViewHolder {
        LayoutCountryListBinding layoutCountryListBinding;
        //TextView txtName;
        private CountryResult mCountryResult;

        CountryViewHolder(@NonNull LayoutCountryListBinding itemView) {
            super(itemView.getRoot());
            layoutCountryListBinding = itemView;

            // txtName = itemView.findViewById(R.id.txtCountryName);
        }

        void onBind(CountryResult countryResult) {
            mCountryResult = countryResult;
            layoutCountryListBinding.txtCountryName.setText(mCountryResult.getName());
            //  layoutCountryListBinding.executePendingBindings();

        }
    }
}
