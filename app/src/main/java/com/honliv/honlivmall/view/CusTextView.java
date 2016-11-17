package com.honliv.honlivmall.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.util.DensityUtil;

import java.util.ArrayList;

/**
 * 不换行的TextView
 *
 * @author
 */
public class CusTextView extends View {

    protected float textSize = 14;
    protected float paddingLeft = 0;
    protected float paddingRight = 0;
    protected float paddingTop = 0;
    protected float paddingBottom = 5;//这里原来是0

    protected float lineSpace = 5;
    protected int lineWidth = 320;
    protected float lineHeight = 20;
    protected int lineCount;
    protected int maxLines = -1;//最大的行数

    protected String mText;
    protected ArrayList<LineParams> lineList = new ArrayList<LineParams>(0);//行的字符串索引


    protected Paint mPaint = new Paint();

    {
        mPaint.setTextSize(DensityUtil.dip2px(getContext(), textSize));
        //mPaint.setColor(Integer.parseInt("ff9f9f9f"));

        mPaint.setColor(getResources().getColor(R.color.black));//设置颜色

//		mPaint.setFakeBoldText(true);//粗体

        mPaint.setAntiAlias(true);
    }

    public CusTextView(Context context) {
        super(context);
    }

    public CusTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CusTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setText(String text) {
        this.mText = text;
        lineList.clear();
        requestLayout();
        invalidate();
    }

    public void setText(String text, int maxLines) {
        this.maxLines = maxLines;
        this.setText(text);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (TextUtils.isEmpty(mText)) {
            setMeasuredDimension(0, 0);
            return;
        }

        lineWidth = (int) (MeasureSpec.getSize(widthMeasureSpec) - paddingLeft - paddingRight);//每行的宽度
        Rect bounds = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), bounds);
        lineHeight = bounds.bottom - bounds.top;

        maxLines = -1;
        lineList.clear();
        calculate();
        lineCount = lineList.size();//总行数
        maxLines = (maxLines > 0) ? Math.min(lineCount, maxLines) : lineCount;

        int measuredHeight = 0;//总的高度
        if (maxLines > 0) {
            measuredHeight += (paddingTop + paddingBottom);//顶部与底部
            measuredHeight += (maxLines - 1) * lineSpace;//空隙的间隔
            measuredHeight += (maxLines * (lineHeight));//行高之和
        }

        int result = measuredHeight;
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = measuredHeight;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(measuredHeight, specSize);
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), result);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (TextUtils.isEmpty(mText)) {
            return;
        }

        for (int i = 0; i < lineList.size(); i++) {
            if (i == 0) {
                canvas.drawText(mText, lineList.get(i).startIndex, lineList.get(i).endIndex, paddingLeft, paddingTop + lineHeight, mPaint);
            } else {
                canvas.drawText(mText, lineList.get(i).startIndex, lineList.get(i).endIndex, paddingLeft, paddingTop + lineHeight + (lineHeight + lineSpace) * i, mPaint);
            }
        }

    }

    private void calculate() {
        int startIndex = 0;
        while (true) {
            int[] itemLine = getIndexArray(startIndex);
            lineList.add(new LineParams(itemLine[0], itemLine[1]));
            if (itemLine[1] >= mText.length()) {
                break;
            }

            startIndex = itemLine[1];
        }
    }

    private int[] getIndexArray(int startIndex) {
        int[] ret = new int[2];

        int canholdcharSize = 0;
        int singleCharWidth = (int) mPaint.measureText(mText, startIndex, startIndex + 1);//单个字符宽度
        singleCharWidth = Math.max(singleCharWidth, 1);
        canholdcharSize = lineWidth / singleCharWidth;//每行可以容纳的字数,非精确值

        if (isOutOfIndex(startIndex, canholdcharSize))//检查是否越界了
        {
            canholdcharSize = mText.length() - startIndex;
        }

        if (isFitness(startIndex, canholdcharSize, lineWidth))//如果linecharSize个字符还小于单行的长度，需要再次自加长度来判断
        {
            while (true) {
                canholdcharSize++;
                if (isOutOfIndex(startIndex, canholdcharSize)) {
                    canholdcharSize--;
                    break;
                }

                if (isFitness(startIndex, canholdcharSize, lineWidth)) {
                    continue;
                } else {
                    canholdcharSize--;
                    break;
                }
            }
        } else //需要再次自减长度来判断
        {
            while (true) {
                canholdcharSize--;
                if (isFitness(startIndex, canholdcharSize, lineWidth)) {
                    break;
                } else {
                    continue;
                }
            }
        }

        ret[0] = startIndex;
        ret[1] = (startIndex + canholdcharSize);
        return ret;
    }

    private boolean isOutOfIndex(int startIndex, int linecharSize) {
        return startIndex + linecharSize > mText.length();
    }

    private boolean isFitness(int startIndex, int canholdcharSize, int lineWidth) {
        return mPaint.measureText(mText, startIndex, startIndex + canholdcharSize) <= lineWidth;
    }

    public class LineParams {
        public int startIndex;
        public int endIndex;

        public LineParams(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        public boolean isInnerRange(int testNum) {
            return (testNum >= startIndex && testNum <= endIndex);
        }

        @Override
        public String toString() {
            return "LineParams [startIndex=" + startIndex + ", endIndex=" + endIndex + "]";
        }
    }
}
