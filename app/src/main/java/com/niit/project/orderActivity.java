package com.niit.project;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class orderActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInsatnceState){
        super.onCreate(savedInsatnceState);
        setContentView(R.layout.order);

        imageView = (ImageView) findViewById(R.id.imageView3);

        Bitmap bitmap = (Bitmap)this.getIntent().getParcelableExtra("Bitmap");
        Drawable d = new BitmapDrawable(getResources(), bitmap);
        System.out.println(d);
        imageView.setBackgroundDrawable(d);
    }
}
