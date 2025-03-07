package com.example.wallpaper;

import android.annotation.SuppressLint;
import android.app.WallpaperColors;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.IOException;

public class DetailImageActivity extends AppCompatActivity {
    private ImageView imageView, back;
    private Button btn_home, btn_srceen;
    private String imageUrl;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.imageView);
        back = findViewById(R.id.back);
        btn_home = findViewById(R.id.btn_home);
        btn_srceen = findViewById(R.id.btn_srceen);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("image_url")) {
            imageUrl = intent.getStringExtra("image_url");
            Glide.with(this)
                    .load(imageUrl)
                    .override(736, 1308) // Kích thước chuẩn 19:9
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.error)
                    .into(imageView);
        } else {
            Toast.makeText(this, "Không có dữ liệu ảnh", Toast.LENGTH_SHORT).show();
        }

        back.setOnClickListener(v -> finish());
        btn_home.setOnClickListener(v -> setWallpaper(WallpaperManager.FLAG_SYSTEM));
        btn_srceen.setOnClickListener(v -> setWallpaper(WallpaperManager.FLAG_LOCK));
    }

    private void setWallpaper(int flag){
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        WallpaperManager wallpaperManager = WallpaperManager.getInstance(DetailImageActivity.this);
                        try{
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                                wallpaperManager.setBitmap(resource,null,true,flag);
                            }else {
                                wallpaperManager.setBitmap(resource);
                            }
                            String message = (flag == WallpaperManager.FLAG_LOCK) ?
                                "cài đặt màn hình khóa thành công!" : "cài đặt màn hình chính thành công!";
                            Toast.makeText(DetailImageActivity.this, message, Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(DetailImageActivity.this, "Lỗi khi cài đặt hình nền", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }

}
