package com.example.wallpaper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpaper.Adapter.BannerItem.BannerItem;
import com.example.wallpaper.Adapter.CategoryAdapter;
import com.example.wallpaper.Adapter.UserAdapter;
import com.example.wallpaper.model.Category;
import com.example.wallpaper.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerview, category;
    private UserAdapter userAdapter;
    private CategoryAdapter categoryAdapter;
    private List<Object> mlistUser;
    private ImageView setting;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setting = findViewById(R.id.setting);
        setting.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SettingActivity.class));
        });

        category = findViewById(R.id.category);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        category.setLayoutManager(linearLayoutManager);

        categoryAdapter = new CategoryAdapter(getlistCatrgory());
        category.setAdapter(categoryAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        category.addItemDecoration(itemDecoration);

        recyclerview = findViewById(R.id.recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                // Nếu là banner => chiếm hết hàng (2 cột), nếu là item ảnh => chiếm 1 cột
                return (mlistUser.get(position) instanceof BannerItem) ? 2 : 1;
            }
        });
        recyclerview.setLayoutManager(gridLayoutManager);

        mlistUser = new ArrayList<>();
        mlistUser.add(new BannerItem());
        String[] imageUrls = {
                "https://i.pinimg.com/736x/88/ea/bf/88eabfeda1f81340ebacfe5acf2b9b35.jpg",
                "https://i.pinimg.com/736x/ef/c3/ad/efc3ad6e0920602c70f84fbc56230a80.jpg",
                "https://i.pinimg.com/736x/f7/b0/f8/f7b0f8c1caf003590d0fb92d7343bcb8.jpg",
                "https://i.pinimg.com/736x/82/14/ff/8214ff37354dce718673064231e92d3e.jpg",
                "https://i.pinimg.com/736x/a3/45/a9/a345a9498ef7630f522d22647738c729.jpg",
                "https://i.pinimg.com/736x/e5/60/5d/e5605dc5ea73abc034b12a6b671b5173.jpg",
                "https://i.pinimg.com/736x/ed/29/65/ed29656f06c2ddaea2d19330d30eb909.jpg",
                "https://i.pinimg.com/736x/f3/8a/95/f38a95459c8fb44159ff50e50ac6dcdf.jpg",
                "https://i.pinimg.com/736x/39/47/94/39479482b6bae30fc66f3f9d5bc2169c.jpg",
                "https://i.pinimg.com/736x/bf/fd/5f/bffd5fee9e1a45762552514a2ddf39b6.jpg",
                "https://i.pinimg.com/736x/3e/90/da/3e90dafc7e3a8cf0612789b3e355fb3f.jpg",
                "https://i.pinimg.com/736x/b0/82/58/b08258cb26f180763ac7230d91e356dc.jpg",
                "https://i.pinimg.com/736x/0b/2d/24/0b2d249fcce083dc5879ab1709784187.jpg",
                "https://i.pinimg.com/736x/84/bb/07/84bb075efa935197192a275ca87f43a2.jpg"
        };
        for (int i = 0; i < imageUrls.length; i++) {
            mlistUser.add(new User(imageUrls[i]));
            if ((i + 1) % 4 == 0) {
                mlistUser.add(new BannerItem());
            }
        }
        userAdapter = new UserAdapter(this, mlistUser);
        recyclerview.setAdapter(userAdapter);

    }

    private List<Category> getlistCatrgory() {
        List<Category> list = new ArrayList<>();
        list.add(new Category("ALL"));
        list.add(new Category("Anime"));
        list.add(new Category("Marvel"));
        list.add(new Category("Snaker"));
        list.add(new Category("Robot"));
        return list;
    }

}