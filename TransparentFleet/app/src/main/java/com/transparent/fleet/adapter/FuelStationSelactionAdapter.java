package com.transparent.fleet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.transparent.fleet.R;
import com.transparent.fleet.callbackClick.OnRecyclerItemClick;
import com.transparent.fleet.databinding.RowFuelstationItemBinding;
import com.transparent.fleet.databinding.RowSubstationItemBinding;
import com.transparent.fleet.model.FuelStationModel;
import com.transparent.fleet.model.FuelStationModel;
import com.transparent.fleet.util.Constants;

import java.util.ArrayList;


public class FuelStationSelactionAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<FuelStationModel> arrayList;
    OnRecyclerItemClick onItemClick;

    int px4, px2;

    CardView.LayoutParams layoutParams = new CardView.LayoutParams(
            CardView.LayoutParams.MATCH_PARENT, CardView.LayoutParams.WRAP_CONTENT);

    public FuelStationSelactionAdapter(Context context, ArrayList<FuelStationModel> arrayList, OnRecyclerItemClick onItemClick) {
        this.context = context;
        this.arrayList = arrayList;
        this.onItemClick = onItemClick;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_fuelstation_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vh, int position) {
        if (vh instanceof MyViewHolder) {
            final MyViewHolder h = ((MyViewHolder) vh);
            h.binding.setFuelStationModel(arrayList.get(position));


            h.binding.executePendingBindings();

        }
        if (position == 0) {
            layoutParams.setMargins(px4, px4, px4, px2);
        } else if (position == arrayList.size() - 1) {
            layoutParams.setMargins(px4, px2, px4, px4);
        } else {
            layoutParams.setMargins(px4, px2, px4, px2);
        }


//        imageLoader.displayImage(arrayList.get(position).getAttachments(), vh.imageView, options);


    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RowFuelstationItemBinding binding;

        public MyViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            binding.llMain.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            onItemClick.onClick(getLayoutPosition(), Constants.ROW_CLICK);


        }
    }
}

