package com.niit.project;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.muddzdev.styleabletoast.StyleableToast;

public class orderActivity extends AppCompatActivity {

    ImageView imageView;
    Button addCart;
    @Override
    protected void onCreate(Bundle savedInsatnceState){
        super.onCreate(savedInsatnceState);
        setContentView(R.layout.order);

        imageView = (ImageView) findViewById(R.id.imageView3);
        addCart = (Button) findViewById(R.id.addcart);

        Bitmap bitmap = (Bitmap)this.getIntent().getParcelableExtra("Bitmap");
        Drawable d = new BitmapDrawable(getResources(), bitmap);
        System.out.println(d);
        //imageView.setBackgroundDrawable(d);
        imageView.setImageDrawable(d);

        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StyleableToast.makeText(getApplicationContext(), "Item added!", Toast.LENGTH_LONG, R.style.mytoast).show();
            }
        });
    }
}
