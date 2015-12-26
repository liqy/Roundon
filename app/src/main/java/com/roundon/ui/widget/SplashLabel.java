package com.roundon.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.roundon.R;

/**
 * Created by liqy on 15/12/26.
 */
public class SplashLabel extends RelativeLayout {

    TextView label;
    TextView desc;

    public SplashLabel(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initAttrs(context, attrs);
    }

    public SplashLabel(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.label_splash, this);
        label = (TextView) view.findViewById(R.id.label);
        desc = (TextView) view.findViewById(R.id.desc);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SplashLabel);
        String labelStr = array.getString(R.styleable.SplashLabel_splash_label);
        String hintStr = array.getString(R.styleable.SplashLabel_splash_desc);
        label.setText(labelStr);
        if (TextUtils.isEmpty(hintStr)) {
            desc.setText(hintStr);
        }
        array.recycle();
    }

    public void setTextColor(String color) {
        label.setTextColor(Color.parseColor(color));
        desc.setTextColor(Color.parseColor(color));
    }

    public void setDesc(String str) {
        desc.setText(str);
    }
}
