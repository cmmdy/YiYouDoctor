package com.example.yiyoudoctor.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yiyoudoctor.HomeActivity;
import com.example.yiyoudoctor.R;

import java.util.List;

import static com.example.yiyoudoctor.R.id.rlListView;


/**
 * Created by 夏夜晚凤 on 2017/3/5.
 */

public class MyAdapter extends ArrayAdapter<MyMessage> {

    private Context mContext;

    private int resourceId;


    public MyAdapter(Context context, int resourceId, List<MyMessage> list) {
        super(context, resourceId, list);
        this.resourceId = resourceId;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position ,View convertView, ViewGroup parent) {
        MyMessage mm = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(resourceId, parent,
                    false);
            //动态修改高度
//            RelativeLayout rlListView = (RelativeLayout) view.findViewById(rlListView);
//            rlListView.getLayoutParams().height = (int)(HomeFragment.HEIGHT/6);


            viewHolder = new ViewHolder();
            viewHolder.mmiv = (ImageView) view.findViewById(R.id.mmiv);
            viewHolder.mmiv.getLayoutParams().width = (int)(HomeActivity.HEIGHT/25);
            viewHolder.mmiv.getLayoutParams().height = viewHolder.mmiv.getLayoutParams().width;
            viewHolder.mmtv = (TextView) view.findViewById(R.id.mmtv);
//            TextPaint tp = viewHolder.mmtv.getPaint();
//            tp.setFakeBoldText(true);
            viewHolder.backiv = (ImageView) view.findViewById(R.id.backiv);
            viewHolder.backiv.getLayoutParams().height = (int)(HomeActivity.HEIGHT/45);
            viewHolder.backiv.getLayoutParams().width = viewHolder.backiv.getLayoutParams().height;

            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(mContext).load(mm.getImageId()).into(viewHolder.mmiv);
        viewHolder.mmtv.setText(mm.getText());
        Glide.with(mContext).load(mm.getBackImageId()).into(viewHolder.backiv);
        return view;
    }

    class ViewHolder {
        ImageView mmiv;

        TextView mmtv;

        ImageView backiv;
    }
}