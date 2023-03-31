package com.example.bikelean;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class CurvedProgressBar extends View {

    private Paint backgroundPaint;
    private Paint progressPaint;
    private RectF arcRectF;
    private float progress = 0; //

    private float maxRightLean = 0;

    private float maxLeftLean = 0;

    private int backgroundColor = Color.GRAY;
    private int progressColor = Color.BLUE;
    private int strokeWidth = 50;

    private Paint maxRightLeanPaint;

    private Paint maxLeftLeanPaint;

    public CurvedProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(backgroundColor);
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(strokeWidth);

        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setColor(progressColor);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(strokeWidth);

        arcRectF = new RectF();

        maxRightLeanPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        maxRightLeanPaint.setColor(Color.RED);
        maxRightLeanPaint.setStyle(Paint.Style.FILL);

        maxLeftLeanPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        maxLeftLeanPaint.setColor(Color.GREEN);
        maxLeftLeanPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float padding = strokeWidth / 2f;
        arcRectF.set(padding, padding, w - padding, h - padding);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(arcRectF, 180, 180, false, backgroundPaint);

        if (progress >= 0) {
            canvas.drawArc(arcRectF, 270, progress, false, progressPaint);
        } else {
            canvas.drawArc(arcRectF, 270 + progress, progress * (-1), false, progressPaint);
        }

        float maxRightLean = 270 + this.maxRightLean;
        float maxRightRadius = (getWidth() - strokeWidth) / 2f;
        float maxRightX = (float) (maxRightRadius * Math.cos(Math.toRadians(maxRightLean)));
        float maxRightY = (float) (maxRightRadius * Math.sin(Math.toRadians(maxRightLean)));
        canvas.drawCircle(maxRightX + getWidth() / 2f, maxRightY + getHeight() / 2f, 20, maxRightLeanPaint);

        float maxLeftLean = 270 + this.maxLeftLean;
        float maxLeftRadius = (getWidth() - strokeWidth) / 2f;
        float maxLeftX = (float) (maxLeftRadius * Math.cos(Math.toRadians(maxLeftLean)));
        float maxLeftY = (float) (maxLeftRadius * Math.sin(Math.toRadians(maxLeftLean)));
        canvas.drawCircle(maxLeftX + getWidth() / 2f, maxLeftY + getHeight() / 2f, 20, maxLeftLeanPaint);



    }

    public void setProgress(float progress) {
        this.progress = progress;

        

        if (progress > 0 && progress > maxRightLean) {
            maxRightLean = progress;
        } else if (progress < 0 && progress < maxLeftLean) {
            maxLeftLean = progress;
        }


        invalidate();
    }

    public void resetLean() {
        maxRightLean = 0;
        maxLeftLean = 0;
        invalidate();
    }

    public float getProgress() {
        return progress;
    }

    public float getMaxRightLean() {
        return maxRightLean;
    }

    public float getMaxLeftLean() {
        return maxLeftLean;
    }
}
