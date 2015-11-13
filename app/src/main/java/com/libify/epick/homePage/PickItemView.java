package com.libify.epick.homePage;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.libify.epick.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;

public class PickItemView extends RelativeLayout{
    @Bind(R.id.imageLeft)
    ImageView imageViewLeft;

    @Bind(R.id.imageRight)
    ImageView imageViewRight;

    @Bind(R.id.imageBottomLeft)
    ImageView imageViewBottomLeft;

    @Bind(R.id.imageBottomRight)
    ImageView imageViewBottomRight;

    @Bind(R.id.pickTitle)
    TextView title;

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
        inflate(getContext(), R.layout.pick_item_layout, null);
    }

    public void bindData(PickItemViewModel data){
        Picasso.with(getContext()).load(data.getImageUrls().get(0)).into(imageViewLeft);
        Picasso.with(getContext()).load(data.getImageUrls().get(1)).into(imageViewRight);
        Picasso.with(getContext()).load(data.getImageUrls().get(2)).into(imageViewBottomLeft);
        Picasso.with(getContext()).load(data.getImageUrls().get(3)).into(imageViewBottomRight);

        title.setText(data.getTitle());


    }

}
