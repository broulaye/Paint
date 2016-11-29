package com.example.broulaye.paint;

import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Broulaye on 11/25/2016.
 */

public class TouchHandler implements View.OnTouchListener {
    CanvasActivity canvasActivity;
    GestureDetectorCompat gestureDetectorCompat;

    public TouchHandler(CanvasActivity canvasActivity) {
        this.canvasActivity = canvasActivity;
        gestureDetectorCompat=new GestureDetectorCompat(this.canvasActivity,new MyGestureListener());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        gestureDetectorCompat.onTouchEvent(event);
        int maskedAction = event.getActionMasked();
        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                addPath(event);
                break;
            case MotionEvent.ACTION_MOVE:
                updatePath(event);
                //addPath(event);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                //removePath(event);
                //addPath(event);
                //updatePath(event);
                break;
        }
        return true;
    }

    private void removePath(MotionEvent event) {
        for (int i = 0, size = event.getPointerCount(); i < size; i++) {
            int id = event.getPointerId(i);
            canvasActivity.removePath(id);
        }
    }

    private  void addPath(MotionEvent event) {
        for (int i = 0, size = event.getPointerCount(); i < size; i++) {
            int id = event.getPointerId(i);
            canvasActivity.addNewPath(id, event.getX(i), event.getY(i));
        }
    }

    private void updatePath(MotionEvent event) {
        //for (int i = 0, size = event.getPointerCount(); i < size; i++) {
            int id = event.getActionIndex();
            canvasActivity.updatePath(id, event.getX(), event.getY());
        //}
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            //canvasActivity.onDoubleTap();
            System.out.println("Double tap recognized");
            return super.onDoubleTap(e);
        }
        @Override
        public void onLongPress(MotionEvent e) {
            //canvasActivity.onLongPress();
            System.out.println("Long press recognized");
            super.onLongPress(e);
        }
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return super.onSingleTapUp(e);
        }
    }



}
