package com.example.broulaye.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.util.HashMap;

/**
 * Created by Broulaye on 11/25/2016.
 */

public class MyCanvas extends View {
    HashMap<Integer, Path> activePaths;
    Paint pathPaint;
    Path path;
    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        activePaths = new HashMap<>();
        pathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pathPaint.setColor(Color.BLACK);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(70);
        path = new Path();

    }

    public void addPath(int id, float x, float y) {

        path.moveTo(x, y);
        activePaths.put(id, path);
        invalidate();
    }

    public void updatePath(int id, float x, float y) {
        Path path = activePaths.get(id);
        if (path != null) {
            path.lineTo(x, y);
        }
        invalidate();
    }

    public void removePath(int id) {
        if(activePaths.containsKey(id)){
            activePaths.remove(id);
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Path path : activePaths.values()) {
            canvas.drawPath(path, pathPaint);
        }
    }

    public void setPaint(int c) {
        pathPaint.setColor(c);
    }

    public void clear() {
        for(int id: activePaths.keySet()) {
            activePaths.remove(id);
        }
        path.reset();
        invalidate();
    }

    public void undo(int id) {
        if(activePaths.containsKey(id)){
            activePaths.remove(id);
        }
        path.rewind();
    }
}
