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
import com.transparent.fleet.databinding.RowInspectionItemBinding;
import com.transparent.fleet.databinding.RowTrailerItemBinding;
import com.transparent.fleet.model.InspectionModel;
import com.transparent.fleet.model.InspectionModel;
import com.transparent.fleet.util.Constants;

import java.util.ArrayList;


public class InspectionAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<InspectionModel> arrayList;
    OnRecyclerItemClick onItemClick;

    int px4, px2;

    CardView.LayoutParams layoutParams = new CardView.LayoutParams(
            CardView.LayoutParams.MATCH_PARENT, CardView.LayoutParams.WRAP_CONTENT);

    public InspectionAdapter(Context context, ArrayList<InspectionModel> arrayList, OnRecyclerItemClick onItemClick) {
        this.context = context;
        this.arrayList = arrayList;
        this.onItemClick = onItemClick;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_inspection_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vh, int position) {
        if (vh instanceof MyViewHolder) {
            final MyViewHolder h = ((MyViewHolder) vh);
            h.binding.setInspectionModel(arrayList.get(position));
            if (arrayList.get(position).getStatus().equalsIgnoreCase("1")) {
                h.binding.txtStatus.setText(context.getString(R.string.pending));
                h.binding.txtStatus.setBackground(context.getResources().getDrawable(R.drawable.padding_round_bg));
            } else if (arrayList.get(position).getStatus().equalsIgnoreCase("2")) {
                h.binding.txtStatus.setText(context.getString(R.string.due));
                h.binding.txtStatus.setBackground(context.getResources().getDrawable(R.drawable.due_round_bg));
            } else if (arrayList.get(position).getStatus().equalsIgnoreCase("3")) {
                h.binding.txtStatus.setText(context.getString(R.string.done));
                h.binding.txtStatus.setBackground(context.getResources().getDrawable(R.drawable.done_round_bg));
            } else {
                h.binding.llStatus.setVisibility(View.GONE);
            }
            if (arrayList.get(position).getType().equalsIgnoreCase("1")) {
                h.binding.type.setText(context.getString(R.string.truck));
            } else if (arrayList.get(position).getType().equalsIgnoreCase("2")) {
                h.binding.type.setText(context.getString(R.string.trailer));
            } else {
                h.binding.type.setVisibility(View.GONE);
            }
            h.binding.executePendingBindings();

        }
        if (position == 0) {
            layoutParams.setMargins(px4, px4, px4, px2);
        } else if (position == arrayList.size() - 1) {
            layoutParams.setMargins(px4, px2, px4, px4);
        } else {
            layoutParams.setMargins(px4, px2, px4, px2);
        }


    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RowInspectionItemBinding binding;

        public MyViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);


        }

        @Override
        public void onClick(View view) {
            onItemClick.onClick(getLayoutPosition(), Constants.ROW_CLICK);


        }
    }
}

