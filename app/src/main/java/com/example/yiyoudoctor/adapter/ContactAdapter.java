package com.example.yiyoudoctor.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yiyoudoctor.R;
import com.example.yiyoudoctor.activity.HomeActivity;
import com.example.yiyoudoctor.model.Contact;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 夏夜晚凤 on 2017/3/12.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {

    private Context mContext;

    private int resourceId;


    public ContactAdapter(Context context, int resource, List<Contact> list) {
        super(context, resource, list);
        resourceId = resource;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Contact contact = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent,
                    false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(contact.getName());
        viewHolder.phone.setText(contact.getPhone());
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.phone)
        TextView phone;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
