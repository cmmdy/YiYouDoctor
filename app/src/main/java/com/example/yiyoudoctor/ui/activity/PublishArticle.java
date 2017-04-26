package com.example.yiyoudoctor.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.yiyoudoctor.Base.BaseActivity;
import com.example.yiyoudoctor.R;
import com.example.yiyoudoctor.adapter.HFTextAdapter;
import com.example.yiyoudoctor.mvp.HFText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PublishArticle extends BaseActivity {

    @BindView(R.id.backtv)
    TextView backtv;
    @BindView(R.id.title)
    TextView title;
    private Context context;
    private List<HFText> hfTextList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.publish_article_activity;
    }

    @Override
    protected void initUI() {
        context = this;
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem deleteItem = new SwipeMenuItem(context);
                deleteItem.setBackground(new ColorDrawable(Color.LTGRAY));
                deleteItem.setWidth(dp2px(90));
                deleteItem.setTitle("删除");
                deleteItem.setTitleColor(Color.WHITE);
                deleteItem.setTitleSize(20);
                deleteItem.setIcon(android.R.drawable.ic_delete);
                menu.addMenuItem(deleteItem);
            }
        };

        final SwipeMenuListView listView = (SwipeMenuListView) findViewById(R.id.collector_listview);
        listView.setDivider(getResources().getDrawable(android.R.drawable.divider_horizontal_bright));
        listView.setMenuCreator(creator);
        initffts();
        final HFTextAdapter adapter = new HFTextAdapter(this, R.layout.home_item, hfTextList);
        listView.setAdapter(adapter);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                //index的值就是在SwipeMenu依次添加SwipeMenuItem顺序值，类似数组的下标。
                //从0开始，依次是：0、1、2、3...
                switch (index) {
                    case 0:
                        hfTextList.remove(position);
                        adapter.notifyDataSetChanged();
                        listView.setAdapter(adapter);
                        break;
                }

                // false : 当用户触发其他地方的屏幕时候，自动收起菜单。
                // true : 不改变已经打开菜单的样式，保持原样不收起。
                return false;
            }
        });



    }


    public int dp2px(float dipValue) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initToolbar() {
        title.setText("发布文章");
        backtv.setText("我的");
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @OnClick({R.id.backll, R.id.textView9})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textView9:
                finish();
                break;
            case R.id.backll:
                finish();
                break;
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
            hfTextList.add(fft1);
        }
    }
}
