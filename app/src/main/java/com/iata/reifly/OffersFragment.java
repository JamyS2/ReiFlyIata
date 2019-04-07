package com.iata.reifly;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class OffersFragment extends Fragment {
    RecyclerView recyclerView;
    ProgressBar loader;

    OffersListAdapter adapter;
    ArrayList<Offer> list = new ArrayList<>();
    public OffersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_offers, container, false);

        recyclerView = view.findViewById(R.id.offer_recyclerview);
        loader = view.findViewById(R.id.loading);
        setupRecyclerView();
        callApi();
        return view;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new OffersListAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
        list.clear();
        adapter.notifyDataSetChanged();
    }

    private void callApi() {
       ApiInterface apiInterface =  ApiService.getClient().create(ApiInterface.class);
       apiInterface.getOffers().enqueue(new Callback<ApiResponse>() {
           @Override
           public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
               if (loader != null) {
                   loader.setVisibility(View.GONE);
                   list.addAll(response.body().offers);
                   adapter.notifyDataSetChanged();
               }
           }

           @Override
           public void onFailure(Call<ApiResponse> call, Throwable t) {
               if (loader != null) {
                   loader.setVisibility(View.GONE);
                   t.printStackTrace();
                   Toast.makeText(getContext(), "Failed to get offers", Toast.LENGTH_LONG).show();
               }
           }
       });
    }

}
