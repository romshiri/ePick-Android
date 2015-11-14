package com.libify.epick.homePage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.libify.epick.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

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
        initView(context);
    }

    public PickItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PickItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.pick_item_layout, this, true);
        ButterKnife.bind(this);
    }

    public void bindData(PickItemViewModel data){
        Picasso.with(getContext()).load(data.getImageUrls().get(0)).error(R.drawable.download).into(imageViewLeft);
        Picasso.with(getContext()).load(data.getImageUrls().get(1)).error(R.drawable.download).into(imageViewRight);
        Picasso.with(getContext()).load(data.getImageUrls().get(2)).error(R.drawable.download).into(imageViewBottomLeft);
        Picasso.with(getContext()).load(data.getImageUrls().get(3)).error(R.drawable.download).into(imageViewBottomRight);

        title.setText(data.getTitle());


    }

}
