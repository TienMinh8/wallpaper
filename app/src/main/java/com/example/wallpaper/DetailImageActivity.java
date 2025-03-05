package com.example.wallpaper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.nio.Buffer;

public class DetailImageActivity extends AppCompatActivity {
    private ImageView imageView, back;
    private Button btn_home, btn_srceen;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        imageView = findViewById(R.id.imageView);
        back = findViewById(R.id.back);


        back.setOnClickListener(v -> back());

            if (intent !=null && intent.hasExtra("image_url")){
                String imageUrl = intent.getStringExtra("image_url");

                Glide.with(this)
                        .load(imageUrl)
                        .override(736,1308)
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.error)
                        .into(imageView);

            }else {
                Toast.makeText(this,"khong co du lieu anh", Toast.LENGTH_SHORT).show();
            }

    };

    private void back() {
        finish();
    }

}
