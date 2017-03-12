package com.example.yiyoudoctor.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yiyoudoctor.R;
import com.example.yiyoudoctor.activity.HomeActivity;
import com.example.yiyoudoctor.activity.OrderActivity;
import com.example.yiyoudoctor.activity.RecordsActivity;
import com.example.yiyoudoctor.model.Patient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 夏夜晚凤 on 2017/3/3.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<Patient> mPatientList;

    private Context mContext;

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.order_image)
        ImageView orderImage;
        @BindView(R.id.patientName)
        TextView patientName;
        @BindView(R.id.orderTime)
        TextView orderTime;
        @BindView(R.id.parentSituation)
        TextView patientSituation;
        @BindView(R.id.button_argee)
        Button agree_button;
        @BindView(R.id.button_refuse)
        Button refuse_button;
        @BindView(R.id.orderrl)
        RelativeLayout orderrl;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public OrderAdapter(List<Patient> mPatientList, Context context) {
        this.mPatientList = mPatientList;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.orderrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Patient patient = mPatientList.get(position);
                Intent intent = new Intent(mContext, OrderActivity.class);
                intent.putExtra("patient_data", patient);
                mContext.startActivity(intent);
            }
        });
        holder.agree_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "您已同意该预约门诊", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, RecordsActivity.class);
                mContext.startActivity(intent);
            }
        });
        holder.refuse_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "您已拒绝该预约门诊", Toast.LENGTH_SHORT).show();
            }
        });
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Patient patient = mPatientList.get(position);

        holder.orderImage.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 13);
        holder.orderImage.getLayoutParams().width = holder.orderImage.getLayoutParams().height;

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
