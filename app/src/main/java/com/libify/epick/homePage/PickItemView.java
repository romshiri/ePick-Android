package com.libify.epick.homePage;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.libify.epick.R;

public class PickItemView extends RelativeLayout{
    public PickItemView(Context context) {
        super(context);
        initView();
    }

    public PickItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PickItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        inflate(getContext(), R.layout.pick_item_layout,null);
    }

}
