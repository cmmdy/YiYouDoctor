package com.example.yiyoudoctor.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yiyoudoctor.R;

import java.util.List;

/**
 * Created by 夏夜晚凤 on 2017/3/3.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<Patient> mPatientList;

    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView orderImage;

        TextView patientName;

        TextView orderTime;

        TextView patientSituation;

        public ViewHolder(View view) {
            super(view);
            orderImage = (ImageView) view.findViewById(R.id.order_image);
            patientName = (TextView) view.findViewById(R.id.patientName);
            orderTime = (TextView) view.findViewById(R.id.orderTime);
            patientSituation = (TextView) view.findViewById(R.id.parentSituation);
        }

    }

    public OrderAdapter(List<Patient> mPatientList, Context context) {
        this.mPatientList = mPatientList;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ofp_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Patient patient = mPatientList.get(position);
        Glide.with(mContext).load(patient.getImageId()).into(holder.orderImage);
        holder.patientName.setText(patient.getName());
        holder.orderTime.setText(patient.getOrderTime());
        holder.patientSituation.setText(patient.getSituation());
    }

    @Override
    public int getItemCount() {
        return mPatientList.size();
    }
}
