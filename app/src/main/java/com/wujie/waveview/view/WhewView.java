package com.wujie.waveview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wujie on 2017/11/13/013.
 */

public class WhewView extends View {

    private Paint paint;
    private int maxWidth = 255;
    // 是否运行
    private boolean isStarting = false;
    private List<String> alphaList = new ArrayList<String>();
    private List<String> startWidthList = new ArrayList<String>();
    private Context context;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    public WhewView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
        this.context =  context;
        init();
    }

    public WhewView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        this.context =  context;
        init();
    }

    public WhewView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        this.context =  context;
        init();
    }

    private void init() {
        paint = new Paint();
        // 设置博文的颜色
        paint.setColor(0x006FF1);
        maxWidth = Dp2Px(context, 80);
        alphaList.add("160");// 圆心的不透明度
        startWidthList.add("0");
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(Color.TRANSPARENT);// 颜色：完全透明
        // 依次绘制 同心圆


        for (int i = 0; i < alphaList.size(); i++) {

            //if(i == 0) {
                int alpha = Integer.parseInt(alphaList.get(i));
                // 圆半径
                int startWidth = Integer.parseInt(startWidthList.get(i));
                paint.setAlpha(alpha);
                // 这个半径决定你想要多大的扩散面积

                canvas.drawCircle(getWidth() / 2, getHeight() / 2, startWidth + Dp2Px(context,
                        20),
                        paint);
                // 同心圆扩散
                if (isStarting && alpha > 0 && startWidth < maxWidth) {
                    alphaList.set(i, (alpha - 2) + "");
                    startWidthList.set(i, (startWidth + 1) + "");
                }
            //}

        }
        if (isStarting
                && Integer
                .parseInt(startWidthList.get(startWidthList.size() - 1)) == Dp2Px(context,15)) {
            alphaList.add("160");
            startWidthList.add("0");
        }
        // 同心圆数量达到10个，删除最外层圆
        if (isStarting && startWidthList.size() == 5) {
            startWidthList.remove(0);
            alphaList.remove(0);
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        }, 10);

    }

    // 执行动画
    public void start() {
        isStarting = true;
    }

    // 停止动画
    public void stop() {
        isStarting = false;
    }

    // 判断是都在不在执行
    public boolean isStarting() {
        return isStarting;
    }

    public  int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        int x = (int) (dp * scale + 0.5f);
        return (int) (dp * scale + 0.5f);
    }

    //px转dp
    public  int Px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

}
