package com.example.yiyoudoctor.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yiyoudoctor.Base.BaseActivity;
import com.example.yiyoudoctor.R;
import com.example.yiyoudoctor.model.Patient;

import static android.R.attr.id;

public class OrderActivity extends BaseActivity {

    private ImageView imageView;

    private TextView name;

    private TextView age;

    private TextView sex;

    private TextView situation;

    private Button agree_button;

    private Button refuse_button;

    private LinearLayout backll;

    private TextView backtv;

    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.order_avtivity;
    }


    @Override
    protected void initUI() {

        imageView = generateFindViewById(R.id.imageView4);
        name = generateFindViewById(R.id.name_tv);
        age = generateFindViewById(R.id.age_tv);
        sex = generateFindViewById(R.id.sex_tv);
        situation = generateFindViewById(R.id.textView);
        agree_button = generateFindViewById(R.id.button_argee);
        refuse_button = generateFindViewById(R.id.button_refuse);

        backll = (LinearLayout) findViewById(R.id.backll);
        backtv = generateFindViewById(R.id.backtv);
        title = generateFindViewById(R.id.title);
    }

    @Override
    protected void initData() {
        initPatient();
    }

    @Override
    protected void initListener() {
        backll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        agree_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderActivity.this, "您已同意该预约门诊", Toast.LENGTH_SHORT).show();
                openActivity(RecordsActivity.class);
            }
        });
        refuse_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderActivity.this, "您已拒绝该预约门诊",  Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    protected void initToolbar() {
        backtv.setText("预约");
        title.setText("预约门诊");
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    public void initPatient(){
        Patient patient = getIntent().getParcelableExtra("patient_data");
        Glide.with(this).load(patient.getImageId()).into(imageView);
        name.setText(patient.getName());
        age.setText("年龄 " + patient.getAge());
        sex.setText("性别 " + patient.getSex());
        situation.setText(patient.getSituation());
    }
}
