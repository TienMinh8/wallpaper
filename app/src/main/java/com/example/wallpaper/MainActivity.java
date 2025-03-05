package com.example.wallpaper;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpaper.Adapter.UserAdapter;
import com.example.wallpaper.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private UserAdapter userAdapter;
    private List<User> mlistUser;

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
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new GridLayoutManager(this,2));

        mlistUser = new ArrayList<>();
        mlistUser.add(new User("https://i.pinimg.com/736x/88/ea/bf/88eabfeda1f81340ebacfe5acf2b9b35.jpg"));
        mlistUser.add(new User("https://i.pinimg.com/736x/ef/c3/ad/efc3ad6e0920602c70f84fbc56230a80.jpg"));
        mlistUser.add(new User("https://i.pinimg.com/736x/f7/b0/f8/f7b0f8c1caf003590d0fb92d7343bcb8.jpg"));
        mlistUser.add(new User("https://i.pinimg.com/736x/82/14/ff/8214ff37354dce718673064231e92d3e.jpg"));
        mlistUser.add(new User("https://i.pinimg.com/736x/a3/45/a9/a345a9498ef7630f522d22647738c729.jpg"));
        mlistUser.add(new User("https://i.pinimg.com/736x/e5/60/5d/e5605dc5ea73abc034b12a6b671b5173.jpg"));
        mlistUser.add(new User("https://i.pinimg.com/736x/ed/29/65/ed29656f06c2ddaea2d19330d30eb909.jpg"));
        mlistUser.add(new User("https://i.pinimg.com/736x/f3/8a/95/f38a95459c8fb44159ff50e50ac6dcdf.jpg"));
        mlistUser.add(new User("https://i.pinimg.com/736x/96/6f/07/966f0732da35121a9fe6ca8cca69ae66.jpg"));
        mlistUser.add(new User("https://i.pinimg.com/736x/05/03/0d/05030d43391106be726d516fe94c1648.jpg"));
        mlistUser.add(new User("https://i.pinimg.com/736x/3e/90/da/3e90dafc7e3a8cf0612789b3e355fb3f.jpg"));
        mlistUser.add(new User("https://i.pinimg.com/736x/b0/82/58/b08258cb26f180763ac7230d91e356dc.jpg"));
        mlistUser.add(new User("https://i.pinimg.com/736x/0b/2d/24/0b2d249fcce083dc5879ab1709784187.jpg"));
        mlistUser.add(new User("https://i.pinimg.com/736x/84/bb/07/84bb075efa935197192a275ca87f43a2.jpg"));

        userAdapter = new UserAdapter(this,mlistUser);
        recyclerview.setAdapter(userAdapter);

    }

}