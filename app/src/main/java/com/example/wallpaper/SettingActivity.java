package com.example.wallpaper;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NavmenuActivity extends AppCompatActivity {

    private ImageView back;
    private RelativeLayout language, rate, share, feedback, info, privacy;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        back = findViewById(R.id.back);

        initView();
        setupListener();
        
        back.setOnClickListener(v -> finish());
    }

    private void initView() {
        language = findViewById(R.id.language);
        rate = findViewById(R.id.rate);
        share = findViewById(R.id.share);
        feedback = findViewById(R.id.feedback);
        info = findViewById(R.id.info);
        privacy = findViewById(R.id.privacy);
    }

    private void setupListener() {
        language.setOnClickListener(v -> language());
        rate.setOnClickListener(v -> rate());
        share.setOnClickListener(v -> share());
        feedback.setOnClickListener(v -> feedback());
        info.setOnClickListener(v -> info());
        privacy.setOnClickListener(v -> privacy());
    }

    private void language() {
    }

    private void rate() {
        
    }

    private void share() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "share content with ");
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    private void feedback() {
        
    }

    private void info() {
        
    }

    private void privacy() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/gampolicy2023"));
        intent.setPackage("com.android.chrome"); // Chỉ định mở bằng Google Chrome
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Nếu không có Chrome, mở bằng trình duyệt mặc định
            intent.setPackage(null);
            startActivity(intent);
        }
    }

}