package weizexu.baway.com.weizexu20170825;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
    Created by asus on 2017/8/25.
 */

public class MyView extends RelativeLayout {


    public MyView(Context context) {
        super(context);
    }
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View inflate = LayoutInflater.from(context).inflate(R.layout.zidingyi, this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        String string = typedArray.getString(R.styleable.MyView_qb);
        TextView title = findViewById(R.id.quanzi);
        title.setText(string);
    }
    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
