package com.example.broulaye.paint;

import android.content.Intent;
import android.graphics.Bitmap;

import android.provider.MediaStore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int REQ_CODE_TAKE_PICTURE = 1;
    private Button takePic;
    byte[] byteArray;
    private boolean startActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity = false;
        takePic = (Button) findViewById(R.id.takePicture);
        takePic.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(startActivity) {
            System.out.println("started activity");
            Intent intent = new Intent(this, CanvasActivity.class);
            intent.putExtra("image",byteArray);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_CODE_TAKE_PICTURE && resultCode == RESULT_OK){
            Bitmap bmp = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byteArray = stream.toByteArray();
            //myCanvas.setBackground(new BitmapDrawable(getResources(),bmp));
            startActivity = true;
        }
    }

    @Override
    public void onClick(View view) {
        Intent picIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(picIntent, REQ_CODE_TAKE_PICTURE);


    }


}
