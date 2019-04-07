package com.iata.reifly;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class OffersListAdapter extends RecyclerView.Adapter<OffersListAdapter.OfferViewHolder> {
    ArrayList<Offer> mList;
    Context mContext;

    OffersListAdapter(Context context, ArrayList<Offer> list) {
        mList = list;
        mContext = context;
    }
    @NonNull
    @Override
    public OfferViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new OfferViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_offer_item_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OfferViewHolder offerViewHolder, int position) {
        Offer offer = mList.get(position);
        offerViewHolder.title.setText("");
        for (Route route : offer.route) {
            offerViewHolder.title.append(route.start+" - "+route.end+"\n"+
                    route.dep_time.substring(11,16)+"-"+route.arrival_time.substring(11,16)+", "+
                    route.dep_time.substring(0,10)+"\n\n");
        }
        offerViewHolder.price.setText("$"+offer.totalprice);
        if (offer.route.size() == 1) {
            offerViewHolder.direct.setText("Direct");
            offerViewHolder.direct.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent));
        } else {
            if (offer.route.size() == 2) {
                offerViewHolder.direct.setText("1 Stop");
            } else {
                offerViewHolder.direct.setText((offer.route.size() -1)+" Stops");
            }
            offerViewHolder.direct.setTextColor(ContextCompat.getColor(mContext, R.color.red));
        }
        offerViewHolder.timings.setText("Delta Airlines, Baggage: "+offer.route.get(0).baggage_allowance);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class OfferViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView timings;
        TextView price;
        TextView direct;
        public OfferViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            timings = itemView.findViewById(R.id.timings);
            price = itemView.findViewById(R.id.price);
            direct = itemView.findViewById(R.id.direct);
        }
    }
}
