package com.example.yiyoudoctor.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.example.yiyoudoctor.Base.BaseFragment;
import com.example.yiyoudoctor.activity.HomeActivity;
import com.example.yiyoudoctor.R;
import com.example.yiyoudoctor.adapter.HFTextAdapter;
import com.example.yiyoudoctor.model.HFText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class HomeFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    private String title = "首页";

    private List<HFText> fftList = new ArrayList<>();


    /**
     * ViewPager
     */
    private ViewPager viewPager;

    /**
     * 装点点的ImageView数组
     */
    private ImageView[] tips;

    /**
     * 装ImageView数组
     */
    private ImageView[] mImageViews;

    /**
     * 图片资源id
     */
    private int[] imgIdArray;

    private boolean isLooper;
    private boolean ifStop = false;
    private View viewheadlv;
    private ViewGroup group;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        viewheadlv = getLayoutInflater(null).inflate(R.layout.home_listhead, null, false);

        //初始化布局
        group = (ViewGroup) viewheadlv.findViewById(R.id.viewGroup);
        viewPager = (ViewPager) viewheadlv.findViewById(R.id.viewPager);
        //载入图片资源ID
        imgIdArray = new int[]{R.drawable.viewpager1, R.drawable.viewpager2, R.drawable.viewpager3, R.drawable.viewpager4,};
        addtips();
        addimage();

        //设置Adapter
        viewPager.setAdapter(new MyAdapter());
        //设置监听，主要是设置点点的背景
        viewPager.setOnPageChangeListener(this);
        //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
        viewPager.setCurrentItem((mImageViews.length) * 100);


        new Thread(new Runnable() {
            @Override
            public void run() {
                isLooper = true;
                while (isLooper) {
                    try {
                        Thread.sleep(2600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (getActivity() == null) {
                        return;
                    }

                    if (!ifStop) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //这里是设置当前页的下一页
                                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                            }
                        });
                    }
                }
            }
        }).start();

        HFTextAdapter FFTAdapter = new HFTextAdapter
                (getActivity(), R.layout.home_listitem, fftList);
        ListView listView = (ListView) view.findViewById(R.id.fftListView);
        listView.addHeaderView(viewheadlv);
        initffts();
        listView.setAdapter(FFTAdapter);

        FrameLayout frameLayout = (FrameLayout) viewheadlv.findViewById(R.id.headFL);
        frameLayout.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 3.5);
        FrameLayout point_layout = (FrameLayout) viewheadlv.findViewById(R.id.point_layout);
        point_layout.getLayoutParams().height = (int) (HomeActivity.gethw.getHEIGHT() / 35);

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ifStop = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        ifStop = false;
                        break;
                }
                return false;
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    public String getTitle() {
        return title;
    }

    private void addtips() {
        //将点点加入到ViewGroup中
        tips = new ImageView[imgIdArray.length];
        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(10, 10));
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.drawable.bullet_yellow);
            } else {
                tips[i].setBackgroundResource(R.drawable.bullet_white);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(50, 50);
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            group.addView(imageView, layoutParams);
        }
    }

    private void addimage() {
        //将图片装载到数组中
        mImageViews = new ImageView[imgIdArray.length];
        for (int i = 0; i < mImageViews.length; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageViews[i] = imageView;
            Glide.with(this).load(imgIdArray[i]).into(imageView);
        }
    }

    public class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
        }

        @Override
        public Object instantiateItem(View container, int position) {
            try {
                ((ViewPager) container).addView(mImageViews[position % mImageViews.length], 0);
            } catch (Exception e) {
            }
            return mImageViews[position % mImageViews.length];
        }


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int arg0) {
        setImageBackground(arg0 % mImageViews.length);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 设置选中的tip的背景
     *
     * @param selectItems
     */
    private void setImageBackground(int selectItems) {
        for (int i = 0; i < tips.length; i++) {
            if (i == selectItems) {
                tips[i].setBackgroundResource(R.drawable.bullet_yellow);
            } else {
                tips[i].setBackgroundResource(R.drawable.bullet_white);
            }
        }
    }

    //初始化ListView
    private void initffts() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        String ymd = year + "/" + month + "/" + day;
        for (int i = 0; i < 10; i++) {
            HFText fft1 = new HFText(R.drawable.viewpager1
                    , "多吃纤维食物有益减肥", "多吃纤维食物有益减肥", ymd);
            fftList.add(fft1);
        }
    }

}
