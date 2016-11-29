package com.example.broulaye.paint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class CanvasActivity extends AppCompatActivity  implements View.OnClickListener{

    private TouchHandler touchhandler;
    private MyCanvas myCanvas;
    private static final int REQ_CODE_TAKE_PICTURE = 1;
    private Button RED;
    private Button BLUE;
    private Button GREEN;
    private Button UNDO;
    private Button CLEAR;
    private Button DONE;
    private int lastPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        touchhandler = new TouchHandler(this);
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        myCanvas = (MyCanvas) findViewById(R.id.myCanvas);
        myCanvas.setOnTouchListener(touchhandler);
        myCanvas.setBackground(new BitmapDrawable(getResources(),bmp));
        RED = (Button) findViewById(R.id.redBtn);
        BLUE = (Button) findViewById(R.id.blueBtn);
        GREEN = (Button) findViewById(R.id.greenBtn);
        UNDO = (Button) findViewById(R.id.undoBtn);
        CLEAR = (Button) findViewById(R.id.clearBtn);
        DONE = (Button) findViewById(R.id.doneBtn);
        RED.setOnClickListener(this);
        BLUE.setOnClickListener(this);
        GREEN.setOnClickListener(this);
        UNDO.setOnClickListener(this);
        CLEAR.setOnClickListener(this);
        DONE.setOnClickListener(this);
        lastPath = -1;
    }



    public void addNewPath(int id, float x, float y) {
        myCanvas.addPath(id, x, y);
        lastPath = id;
    }

    public void updatePath(int id, float x, float y) {
        myCanvas.updatePath(id, x, y);
        lastPath = id;
    }
    public void removePath(int id) {
        myCanvas.removePath(id);
    }

    public void onDoubleTap() {
        Random rd = new Random();
        myCanvas.setBackgroundColor(Color.rgb(rd.nextInt(255), rd.nextInt(255), rd.nextInt(255)));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.redBtn:
                myCanvas.setPaint(Color.RED);
                break;
            case R.id.blueBtn:
                myCanvas.setPaint(Color.BLUE);
                break;
            case R.id.greenBtn:
                myCanvas.setPaint(Color.GREEN);
                break;
            case R.id.undoBtn:
                if(lastPath != -1) {

                    myCanvas.undo(lastPath);
                }
                break;
            case R.id.clearBtn:
                myCanvas.clear();
                break;
            case R.id.doneBtn:

                break;
        }
    }
}
