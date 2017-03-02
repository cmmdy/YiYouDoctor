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
import com.example.yiyoudoctor.R;

import java.util.List;


/**
 * Created by 夏夜晚凤 on 2017/3/2.
 */

public class HFTextAdapter extends ArrayAdapter<HFText> {

    private Context mContext;

    private int resourceId;

    public HFTextAdapter(Context context, int FFTResourceId, List<HFText> list) {
        super(context, FFTResourceId, list);
        resourceId = FFTResourceId;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HFText fft = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent,
                    false);
            //动态修改高度
            RelativeLayout rlListView = (RelativeLayout) view.findViewById(R.id.rlListView);
            rlListView.getLayoutParams().height = (int)(HomeFragment.HEIGHT/6);


            viewHolder = new ViewHolder();
            viewHolder.fftImage = (ImageView) view.findViewById
                    (R.id.first_fragment_text_image);
            viewHolder.fftImage.getLayoutParams().width = (int)(HomeFragment.HEIGHT/6);

            viewHolder.fftTitleText = (TextView) view.findViewById
                    (R.id.first_fragment_text_title);
            TextPaint tp = viewHolder.fftTitleText.getPaint();
            tp.setFakeBoldText(true);
            viewHolder.fftIntroductionText = (TextView) view.findViewById
                    (R.id.first_fragment_text_introduction);
            viewHolder.fftTime = (TextView) view.findViewById
                    (R.id.first_fragment_time);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(mContext).load(fft.getImageId()).into(viewHolder.fftImage);
        viewHolder.fftTitleText.setText(fft.getTextTitle());
        viewHolder.fftIntroductionText.setText(fft.getTextIntroduction());
        viewHolder.fftTime.setText(fft.getTime());
        return view;
    }

    class ViewHolder {
        ImageView fftImage;

        TextView fftTitleText;

        TextView fftIntroductionText;

        TextView fftTime;
    }
}
