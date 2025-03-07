package com.example.wallpaper.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wallpaper.Adapter.BannerItem.BannerItem;
import com.example.wallpaper.DetailImageActivity;
import com.example.wallpaper.R;
import com.example.wallpaper.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_TYPE_IMAGE = 0;
    private static final int ITEM_TYPE_BANNER = 1;

    private Context context;
    private List<Object> mlistUser;

    public UserAdapter(Context context, List<Object> mlistUser) {
        this.context = context;
        this.mlistUser = mlistUser;
    }

    @Override
    public int getItemViewType(int position) {
        Object item = mlistUser.get(position);
        if (item instanceof BannerItem) {
            return 0; // Banner
        } else if (item instanceof User) {
            return 1; // Ảnh user
        }
        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        if (viewType == 0) { // Nếu là banner
            View view = inflater.inflate(R.layout.item_banner, parent, false);
            return new BannerViewHolder(view);
        } else { // Nếu là User
            View view = inflater.inflate(R.layout.item_user, parent, false);
            return new UserViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Object item = mlistUser.get(position); // Lấy item ra kiểm tra kiểu dữ liệu

        if (holder instanceof UserViewHolder && item instanceof User) {
            User user = (User) item;
            UserViewHolder userHolder = (UserViewHolder) holder;

            Glide.with(context)
                    .load(user.getImage())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.error)
                    .into(userHolder.image);

            userHolder.image.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), DetailImageActivity.class);
                intent.putExtra("image_url", user.getImage());
                v.getContext().startActivity(intent);
            });

        } else if (holder instanceof BannerViewHolder && item instanceof BannerItem) {
            BannerViewHolder bannerHolder = (BannerViewHolder) holder;
            String[] bannerUrls = {
                    "https://i.pinimg.com/736x/c1/16/c6/c116c685d5ef3d28a92285ffe24bbd14.jpg",
                    "https://i.pinimg.com/736x/f0/d2/8d/f0d28df1476d8bf3a89371f504e4137c.jpg",
                    "https://i.pinimg.com/736x/5b/65/63/5b65636476a5c3aa2ce578b91aa512d5.jpg",
                    "https://i.pinimg.com/736x/bc/02/5c/bc025c32d1ac533c02fcf9ab61a9c283.jpg"
            };
            int bannerIndex = (position / 5) % bannerUrls.length;
            Glide.with(context)
                    .load(bannerUrls[bannerIndex])
                    .into(bannerHolder.bannerImage);

            bannerHolder.bannerText.setText("WALLPAPER || APP");
        }
    }

    @Override
    public int getItemCount() {
        return mlistUser.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
        }
    }

    public static class BannerViewHolder extends RecyclerView.ViewHolder {
        private TextView bannerText;
        private ImageView bannerImage;

        @SuppressLint("WrongViewCast")
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            bannerImage = itemView.findViewById(R.id.bannerImage);
            bannerText = itemView.findViewById(R.id.bannerText);
        }
    }
}