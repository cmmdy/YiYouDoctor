package com.example.yiyoudoctor.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yiyoudoctor.R;
import com.example.yiyoudoctor.model.Patient;

public class ordermessage extends AppCompatActivity {

    private ImageView imageView;

    private TextView name;

    private TextView age;

    private TextView sex;

    private TextView situation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }

        setContentView(R.layout.activity_ordermessage);

        imageView = (ImageView) findViewById(R.id.imageView4);
        name = (TextView) findViewById(R.id.name_tv);
        age = (TextView) findViewById(R.id.age_tv);
        sex = (TextView) findViewById(R.id.sex_tv);
        situation = (TextView) findViewById(R.id.textView);
        Button agree_button = (Button) findViewById(R.id.button_argee);
        Button refuse_button = (Button) findViewById(R.id.button_refuse);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        LinearLayout backll = (LinearLayout) findViewById(R.id.backll);
        backll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        agree_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ordermessage.this, "您已同意该预约门诊", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        refuse_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ordermessage.this, "您已拒绝该预约门诊",  Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        setSupportActionBar(toolbar);

        initPatient();
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
